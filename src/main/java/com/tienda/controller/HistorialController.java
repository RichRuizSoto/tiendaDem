package com.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistorialController {

    @GetMapping("/historial")
    public String historialListado() {
        return "historial/listado";
    }
}
