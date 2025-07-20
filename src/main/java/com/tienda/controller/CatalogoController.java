package com.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogoController {

    @GetMapping("/catalogo")
    public String catalogoListado() {
        return "catalogo/listado";
    }
}
