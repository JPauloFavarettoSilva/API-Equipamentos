package org.apiuniara.services;

import org.apiuniara.models.Equipamento;
import org.apiuniara.repositories.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    // Retorna a lista de todos os equipamentos
    public List<Equipamento> findAll() {
        return equipamentoRepository.findAll();
    }

    // Salva um novo equipamento
    public Equipamento save(Equipamento equipamento) {
        return equipamentoRepository.save(equipamento);
    }

    // Método para reservar o equipamento e definir a data de retirada
    public void reserve(int id) {
        Equipamento equipamento = equipamentoRepository.findById(id).get();
        equipamento.setDisponivel(false);
        equipamento.setDataRetirada(LocalDateTime.now()); // Define a data e hora da retirada
        equipamentoRepository.save(equipamento);
    }

    // Método para liberar o equipamento e limpar a data de retirada
    public void liberar(int id) {
        Equipamento equipamento = equipamentoRepository.findById(id).get();
        equipamento.setDisponivel(true);
        equipamento.setDataRetirada(null); // Limpa a data de retirada
        equipamentoRepository.save(equipamento);
    }
}

