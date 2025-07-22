package com.tienda.repositorio;

import com.tienda.domain.Historial;
import com.tienda.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistorialRepositorio extends JpaRepository<Historial, Long> {
    List<Historial> findByUsuario(Usuario usuario);
}