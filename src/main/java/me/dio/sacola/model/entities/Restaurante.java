package me.dio.sacola.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@AllArgsConstructor //Gera construtor com todos os atributos
@Builder //Criar um objeto de forma simples
@Data //Gera os getters and setters e equals e hashcode
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer" , "handler"}) //trabalha em conjunto com o jsonignone lazy
@NoArgsConstructor //Construtor sem argumentos
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Produto> cardapio;
    @Embedded //os atributos de endereço apareceram em restaurante
    private Endereco endereco;
}
