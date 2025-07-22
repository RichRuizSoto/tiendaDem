package com.tienda.controller;

import com.tienda.domain.Producto;
import com.tienda.domain.Usuario;
import com.tienda.domain.Historial;
import com.tienda.repositorio.ProductoRepositorio;
import com.tienda.repositorio.UsuarioRepositorio;
import com.tienda.repositorio.HistorialRepositorio;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList; 

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalogo")
public class CatalogoController {

    private final ProductoRepositorio productoRepo;
    private final UsuarioRepositorio usuarioRepo;
    private final HistorialRepositorio historialRepo;

    @GetMapping
    public String catalogo(Model model) {
        model.addAttribute("productos", productoRepo.findAll());
        return "catalogo/listado";
    }

    @PostMapping
    public String crearProducto(@RequestParam String nombre, @RequestParam Double precio,
                                @RequestParam Integer cantidad) {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setCantidad(cantidad);
        producto.setTotal(precio * cantidad);
        productoRepo.save(producto);
        return "redirect:/catalogo";
    }

    @PostMapping("/comprar/{productoId}")
    public String comprar(@PathVariable Long productoId, @RequestParam Long usuarioId) {
        Producto producto = productoRepo.findById(productoId).orElseThrow();
        Usuario usuario = usuarioRepo.findById(usuarioId).orElseThrow();
        Historial historial = new Historial();
        historial.setUsuario(usuario);
        historial.setProducto(producto);
        historialRepo.save(historial);
        producto.setCantidad(producto.getCantidad() - 1);
        producto.setTotal(producto.getPrecio() * producto.getCantidad());
        productoRepo.save(producto);
        return "redirect:/catalogo";
    }

    @PostMapping("/carrito/agregar/{id}")
    public String agregarAlCarrito(@PathVariable Long id, HttpSession session) {
        Producto producto = productoRepo.findById(id).orElseThrow();
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        carrito.add(producto);
        session.setAttribute("carrito", carrito);
        return "redirect:/carrito";
    }
}