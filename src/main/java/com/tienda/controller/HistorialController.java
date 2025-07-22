package com.tienda.controller;

import com.tienda.domain.Usuario;
import com.tienda.domain.Historial;
import com.tienda.repositorio.UsuarioRepositorio;
import com.tienda.repositorio.HistorialRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/historial")
public class HistorialController {

    private final UsuarioRepositorio usuarioRepo;
    private final HistorialRepositorio historialRepo;

    @GetMapping
    public String historial(@RequestParam(required = false) Long usuarioId, Model model) {
        List<Historial> historial;
        if (usuarioId != null) {
            Usuario usuario = usuarioRepo.findById(usuarioId).orElse(null);
            historial = (usuario != null) ? historialRepo.findByUsuario(usuario) : List.of();
            model.addAttribute("usuarioSeleccionado", usuario);
        } else {
            historial = historialRepo.findAll();
        }
        model.addAttribute("usuarios", usuarioRepo.findAll());
        model.addAttribute("historial", historial);
        return "historial/listado";
    }
}