package me.dio.sacola.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.dio.sacola.enumeration.FormaPagamento;

import javax.persistence.*;
import java.util.List;
@AllArgsConstructor //Gera construtor com todos os atributos
@Builder //Criar um objeto de forma simples
@Data //Gera os getters and setters e equals e hashcode
@Entity //Indica que é uma tabela no bd
@JsonIgnoreProperties({"hibernateLazyInitializer" , "handler"}) //trabalha em conjunto com o jsonignone lazy, para ignorar erros
@NoArgsConstructor //Construtor sem argumentos
public class Sacola {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne (fetch = FetchType.LAZY , optional = false)
    @JsonIgnore //Ignorar a comunicação do front com o back do json
    private Cliente cliente;
    @OneToMany (cascade = CascadeType.ALL)
    private List<Item> itens;
    private double valorTotal;

    @Enumerated
    private FormaPagamento formaPagamento;
    private boolean fechada;

}
