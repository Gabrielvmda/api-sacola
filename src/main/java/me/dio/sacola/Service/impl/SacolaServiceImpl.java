package me.dio.sacola.Service.impl;

import lombok.RequiredArgsConstructor;
import me.dio.sacola.Service.SacolaService;
import me.dio.sacola.enumeration.FormaPagamento;
import me.dio.sacola.model.entities.Item;
import me.dio.sacola.model.entities.Restaurante;
import me.dio.sacola.model.entities.Sacola;
import me.dio.sacola.repository.ItemRepository;
import me.dio.sacola.repository.ProdutoRepository;
import me.dio.sacola.repository.SacolaRepository;
import me.dio.sacola.resource.SacolaResource;
import me.dio.sacola.resource.dto.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SacolaServiceImpl implements SacolaService {
    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;

    @Override
    public Item incluirItemNaSacola(ItemDTO itemDTO) {
        Sacola sacola = verSacola(itemDTO.getSacolaId());

        if (sacola.isFechada()) {
            throw new RuntimeException("Esta sacola está fechada!");
        }

        Item itemParaSerInserido = Item.builder() //Para criar um item -> builder é do spring para criar de acordo com os parametros da classe
                .quantidade(itemDTO.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDTO.getProdutoId()).orElseThrow(
                        () -> new RuntimeException("Esse produto não existe")
                ))
                .build();

        List<Item> itensDaSacola = sacola.getItens(); //adicionar na sacola
        if (itensDaSacola.isEmpty()) {
            itensDaSacola.add(itemParaSerInserido);
        } else {
            Restaurante restauranteAtual = itensDaSacola.get(0).getProduto().getRestaurante();
            Restaurante restauranteDoItemParaAdicionar = itemParaSerInserido.getProduto().getRestaurante();
            if (restauranteAtual.equals(restauranteDoItemParaAdicionar)) { //se os restaurantes forem iguais
                itensDaSacola.add(itemParaSerInserido);
            } else {
                throw new RuntimeException("Não é possivel adicionar produtos de restaurantes diferentes. Feche a sacola ou esvazie");
                //caso não forem iguais, exception
            }

        }
        
        List <Double> valorDosItens = new ArrayList<>();
        for (Item itemDaSacola : itensDaSacola) {
            double valorTotalItem = itemDaSacola.getProduto().getValorUnitario() * itemDaSacola.getQuantidade();
            valorDosItens.add(valorTotalItem);
        }

        double valorTotalSacola = valorDosItens.stream() //metodo para somar todos os items
                .mapToDouble(valorTotalDeCadaItem -> valorTotalDeCadaItem)
                .sum();


        sacola.setValorTotal(valorTotalSacola);
        sacolaRepository.save(sacola);
        return itemParaSerInserido;
    }

    @Override
    public Sacola verSacola(Long id) {
        return sacolaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Essa sacola não existe")

        );
    }

    @Override
    public Sacola fecharSacola(Long id, int numeroFormaPagamento) {
        Sacola sacola = verSacola(id);
        if (sacola.getItens().isEmpty()) {
            throw new RuntimeException("Inclua itens na sacola!");
        }
        FormaPagamento formapagamento = numeroFormaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;
        sacola.setFormaPagamento(formapagamento);
        sacola.setFechada(true);
        return sacolaRepository.save(sacola);
    }
}
