package com.contenerizacion.Libros_Docker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.contenerizacion.Libros_Docker.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}
