package com.cursonelio.javaspringboot.cursoNelio.service;

import com.cursonelio.javaspringboot.cursoNelio.dto.Request.ClienteRequest;
import com.cursonelio.javaspringboot.cursoNelio.dto.Request.ClienteRequestNew;
import com.cursonelio.javaspringboot.cursoNelio.dto.Response.ClienteResponse;
import com.cursonelio.javaspringboot.cursoNelio.repository.EnderecoRepository;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Cliente;
import com.cursonelio.javaspringboot.cursoNelio.repository.ClienteRepository;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.Endereco;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.enuns.Perfil;
import com.cursonelio.javaspringboot.cursoNelio.repository.entity.enuns.TipoCliente;
import com.cursonelio.javaspringboot.cursoNelio.security.UserSS;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.AuthorizationException;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.ObjectNotFoundException;
import com.cursonelio.javaspringboot.cursoNelio.service.exception.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.prefix.client.profile}")
    private String prefix;

    @Transactional(readOnly = true)
    public Cliente find(Integer id) {

        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())){
            throw new AuthorizationException("Acesso Negado");
        }

        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(("" +
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())));
    }

    @Transactional(readOnly = true)
    public ClienteResponse findClienteId (Integer id){
        Optional<Cliente> cliente = repository.findById(id);
        return new ClienteResponse().toResponse(cliente.get());
    }

    @Transactional(readOnly = true)
    public List<ClienteResponse> findAll(){
        return repository.findAll().stream().map(cliente -> new ClienteResponse().toResponse(cliente)).collect(Collectors.toList());
    }

    public Cliente findByEmail(String email) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
            throw new AuthorizationException("Acesso negado");
        }

        Cliente obj = repository.findByEmail(email);
        if (obj == null) {
            throw new ObjectNotFoundException(
                    "Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Cliente.class.getName());
        }
        return obj;
    }

    @Transactional
    public Cliente create (Cliente cliente){
        repository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
    }

    public Cliente fromDTO (ClienteRequestNew clienteRequestNew){
        Cliente cli = new Cliente(null, clienteRequestNew.getNome(), clienteRequestNew.getEmail(), clienteRequestNew.getCpfOuCnpj(), TipoCliente.toEnum(clienteRequestNew.getTipoCliente()), bCryptPasswordEncoder.encode(clienteRequestNew.getSenha()));
        Endereco end = new Endereco(null, clienteRequestNew.getLogradouro(), clienteRequestNew.getNumero(), clienteRequestNew.getComplemento(), clienteRequestNew.getBairro(), clienteRequestNew.getCep(), cli, null);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(clienteRequestNew.getTelefones1());
        if (clienteRequestNew.getTelefones2()!=null){
            cli.getTelefones().add(clienteRequestNew.getTelefones2());
        }
        if (clienteRequestNew.getTelefones3()!=null){
            cli.getTelefones().add(clienteRequestNew.getTelefones3());
        }
        return cli;
    }

    @Transactional
    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    @Transactional
    public void delete(Integer id){
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Nao é possivel excluir por que há entidades relacionadas");
        }
    }

    public Page<Cliente> findPage (Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public URI uploadProfilePicture(MultipartFile multipartFile){ // metodo q salva a url no banco no cliente. USER que esta logado
        UserSS user = UserService.authenticated();
        if (user == null){
            throw new AuthorizationException("Acesso negado");
        } //
        BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile); // imagem extraida
        String fileName = prefix + user.getId() + "jpg"; // nome do arquivo personalizado de acordo com o cliente logado
        return s3Service.uploadFile(imageService.getInputStrem(jpgImage, "jpg"), fileName, "image");
    }
}
