package service;


import domain.Pedido;
import org.springframework.stereotype.Service;
import repository.PedidoRepository;
import service.exception.ObjectNotFounfException;

import java.util.Optional;

@Service
public class PedidoService {

    private PedidoRepository pedidoRepository;

    public Pedido buscar (Integer id){
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFounfException(("" +
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())));


    }
}
