package com.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    // Muestra la página de login (GET)
    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("title", "Iniciar Sesión");
        return "login"; // Asegúrate que el archivo login.html esté en src/main/resources/templates/
    }

    // Procesa el login (POST)
    @PostMapping("/login")
    public String procesarLogin(@RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 Model model) {
        // Simulación de validación básica
        if ("admin@correo.com".equals(email) && "1234".equals(password)) {
            return "redirect:/dashboard"; // Redirige a otra página si el login es exitoso
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            model.addAttribute("title", "Iniciar Sesión");
            return "login"; // Vuelve a mostrar el login con mensaje de error
        }
    }
}
