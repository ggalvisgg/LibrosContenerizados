package com.contenerizacion.Libros_Docker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.contenerizacion.Libros_Docker.model.Libro;
import com.contenerizacion.Libros_Docker.repository.LibroRepository;
import com.contenerizacion.Libros_Docker.service.LibroImplementation;

@ExtendWith(MockitoExtension.class)
public class LibroServiceTest {

@SpringBootTest
class LibroServiceImplTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroImplementation libroService;

    private Libro libro;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        libro = new Libro();
        libro.setId(1L);
        libro.setTitulo("1984");
        libro.setAutor("Orwell");
        libro.setIsbn("123456");
        libro.setAnioPublicacion(1949);
    }

    @Test
    void guardarLibro() {
        when(libroRepository.save(any(Libro.class))).thenReturn(libro);
        Libro guardado = libroService.guardarLibro(libro);
        assertNotNull(guardado);
        assertEquals("1984", guardado.getTitulo());
    }

    @Test
    void listarLibros() {
        when(libroRepository.findAll()).thenReturn(List.of(libro));
        List<Libro> libros = libroService.listarLibros();
        assertEquals(1, libros.size());
    }

    @Test
    void obtenerLibroPorId() {
        when(libroRepository.findById(1L)).thenReturn(Optional.of(libro));
        Libro encontrado = libroService.obtenerLibroPorId(1L);
        assertEquals("1984", encontrado.getTitulo());
    }

    @Test
    void eliminarLibro() {
        libroService.eliminarLibro(1L);
        verify(libroRepository, times(1)).deleteById(1L);
    }
}
    
}
