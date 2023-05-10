package me.dio.sacola.Service;

import me.dio.sacola.model.entities.Item;
import me.dio.sacola.model.entities.Sacola;
import me.dio.sacola.resource.dto.ItemDTO;

public interface SacolaService {

    Item incluirItemNaSacola (ItemDTO itemDTO);
    Sacola verSacola (Long id);
    Sacola fecharSacola (Long id , int formaPagamento);
    }



