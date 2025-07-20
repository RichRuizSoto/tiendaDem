package com.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecuperarController {

    @GetMapping("/recuperar")
    public String recuperarListado() {
        return "recuperar/listado";
    }
}
