package me.dio.sacola.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;


@AllArgsConstructor
@Builder
@Data
@Embeddable
@NoArgsConstructor
public class Endereco {
    String cep;
    String complemento;
}
