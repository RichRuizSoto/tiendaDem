package com.tienda.controller;

import com.tienda.domain.Historial;
import com.tienda.domain.Producto;
import com.tienda.domain.Usuario;
import com.tienda.repositorio.HistorialRepositorio;
import com.tienda.repositorio.ProductoRepositorio;
import com.tienda.repositorio.UsuarioRepositorio;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CarritoController {

    private final HistorialRepositorio historialRepo;
    private final UsuarioRepositorio usuarioRepo;
    private final ProductoRepositorio productoRepo;

    @GetMapping("/carrito")
    public String mostrarCarrito(HttpSession session, Model model) {
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
        model.addAttribute("carrito", carrito);

        double total = 0.0;
        if (carrito != null) {
            for (Producto producto : carrito) {
                total += producto.getPrecio();
            }
        }
        model.addAttribute("total", total);

        List<Usuario> usuarios = usuarioRepo.findAll();
        model.addAttribute("usuarios", usuarios);

        return "carrito/listado";
    }

    
    @GetMapping("/carrito/agregar/{id}")
    public String agregarProductoCarrito(@PathVariable Long id, HttpSession session) {
        Producto producto = productoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        carrito.add(producto);
        session.setAttribute("carrito", carrito);

        return "redirect:/carrito";
    }

    @PostMapping("/carrito/confirmar")
public String confirmarCompra(@RequestParam("usuarioId") Long usuarioId, HttpSession session) {
    Usuario usuario = usuarioRepo.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");

    if (carrito != null && !carrito.isEmpty()) {
        for (Producto producto : carrito) {
            
            Producto productoGestionado = productoRepo.findById(producto.getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado en BD con id: " + producto.getId()));

            Historial historial = new Historial();
            historial.setUsuario(usuario);
            historial.setProducto(productoGestionado); 
            historial.setFechaCompra(new Date());
            historial.setEstado("Confirmado");
            historialRepo.save(historial);
        }
        session.removeAttribute("carrito");
    }

    return "redirect:/historial";
}

    @PostMapping("/carrito/cancelar")
    public String cancelarCarrito(HttpSession session) {
        session.removeAttribute("carrito");
        return "redirect:/carrito";
    }

    @PostMapping("/carrito/eliminar/{index}")
    public String eliminarProductoCarrito(@PathVariable int index, HttpSession session) {
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
        if (carrito != null && index >= 0 && index < carrito.size()) {
            carrito.remove(index);
            session.setAttribute("carrito", carrito);
        }
        return "redirect:/carrito";
    }
}