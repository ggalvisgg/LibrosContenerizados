package com.contenerizacion.Libros_Docker.service;

import java.util.List;

import com.contenerizacion.Libros_Docker.model.Libro;

public interface LibroService {
    List<Libro> listarLibros();
    Libro obtenerLibroPorId(Long id);
    Libro guardarLibro(Libro libro);
    Libro actualizarLibro(Long id, Libro libro);
    void eliminarLibro(Long id);
}
