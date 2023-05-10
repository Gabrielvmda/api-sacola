package me.dio.sacola.resource;

import lombok.RequiredArgsConstructor;
import me.dio.sacola.Service.SacolaService;
import me.dio.sacola.model.entities.Item;
import me.dio.sacola.model.entities.Sacola;
import me.dio.sacola.resource.dto.ItemDTO;
import org.springframework.web.bind.annotation.*;


@RestController // Indica que a classe é um endpoint (controller)
@RequestMapping("/ifood-devweek/sacolas") //Indica a URL do projeto
@RequiredArgsConstructor //Gera os construtores necessários (final)
public class SacolaResource {

    private final SacolaService sacolaService;



    @PostMapping //indica inserção no bd

    public Item incluirItemNaSacola (@RequestBody ItemDTO itemDto) { //Corpo da requisição
        return sacolaService.incluirItemNaSacola(itemDto);
    }

    @GetMapping ("/{id}")//adiciona o /id na nossa url
    public Sacola verSacola (@PathVariable ("id") Long id ) {
        return sacolaService.verSacola(id);
    }

    @PatchMapping ("/fecharSacola/{sacolaId}")//Alterar valores -> no caso atualizar de aberta para fechada
    public Sacola fecharSacola (@PathVariable ("sacolaId") Long sacolaId , @RequestParam ("formaPagamento") int formapagamento) {
        return sacolaService.fecharSacola(sacolaId, formapagamento);
    }

}
