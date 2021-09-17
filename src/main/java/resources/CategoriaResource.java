package resources;


import domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.CategoriaService;

import java.util.ArrayList;
import java.util.List;

@RestController//classe controladora
@RequestMapping(value="/categorias")//nome do end point rest
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    //simples requisicao GET - verbo http obtendo dados (pegando dados)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){ //anotaçao @PathVariable para o spring sabia que o id da url
        //se relaciona com o id da variavel no parametro usa se a notaçao
        //Metodo retorna um objt ResponseEntity<sem parametro> ja encapsula varias informacoes
        //de uma mensagem http, sem parametro pq pode ou nao retornar algo
        Categoria obj = categoriaService.find(id);
        return ResponseEntity.ok().body(obj);
        //metodo ok dizendo que ocorreu com sucesso tendo como corpo a categoria
    }
}
