package resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController//classe controladora
@RequestMapping(value="/categorias")//nome do end point rest
public class CategoriaResource {


    //simples requisicao GET - verbo http obtendo dados (pegando dados)
    @RequestMapping(method=RequestMethod.GET)
    public String listar(){
        return "REST está funcionando";
    }
}
