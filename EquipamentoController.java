package org.apiuniara.controllers;

import org.apiuniara.models.Equipamento;
import org.apiuniara.services.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @CrossOrigin
    @GetMapping("/equipamentos")
    public List<Equipamento> findAll() {
        return equipamentoService.findAll();
    }

    @CrossOrigin
    @PostMapping("/equipamentos")
    public Equipamento save(@RequestBody Equipamento equipamento) {
        return equipamentoService.save(equipamento);
    }

    // Atualiza método de reserva para retornar ResponseEntity e definir data de retirada
    @CrossOrigin
    @PostMapping("/equipamentos/{equipamentoId}/reservar")
    public ResponseEntity<String> reserve(@PathVariable int equipamentoId) {
        boolean success = equipamentoService.reserve(equipamentoId);
        if (success) {
            return ResponseEntity.ok("Equipamento reservado com sucesso!");
        } else {
            return ResponseEntity.status(400).body("Falha ao reservar equipamento.");
        }
    }

    // Atualiza método de liberar reserva para retornar ResponseEntity e limpar a data de retirada
    @CrossOrigin
    @PostMapping("/equipamentos/{equipamentoId}/liberar")
    public ResponseEntity<String> liberar(@PathVariable int equipamentoId) {
        boolean success = equipamentoService.liberar(equipamentoId);
        if (success) {
            return ResponseEntity.ok("Equipamento liberado com sucesso!");
        } else {
            return ResponseEntity.status(400).body("Falha ao liberar equipamento.");
        }
    }
}
