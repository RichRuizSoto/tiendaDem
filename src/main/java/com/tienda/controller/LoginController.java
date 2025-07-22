package com.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("title", "Iniciar Sesión");
        return "login"; 
    }
}
