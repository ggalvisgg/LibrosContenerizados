package com.contenerizacion.Libros_Docker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contenerizacion.Libros_Docker.model.Libro;
import com.contenerizacion.Libros_Docker.repository.LibroRepository;

import java.util.List;

@Service
public class LibroImplementation implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @Override
    public Libro obtenerLibroPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    @Override
    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Libro actualizarLibro(Long id, Libro libroActualizado) {
        Libro libro = obtenerLibroPorId(id);
        libro.setTitulo(libroActualizado.getTitulo());
        libro.setAutor(libroActualizado.getAutor());
        libro.setIsbn(libroActualizado.getIsbn());
        libro.setAnioPublicacion(libroActualizado.getAnioPublicacion());
        return libroRepository.save(libro);
    }

    @Override
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }
}
