package com.contenerizacion.Libros_Docker.services;

import com.contenerizacion.Libros_Docker.model.Libro;
import com.contenerizacion.Libros_Docker.repository.LibroRepository;
import com.contenerizacion.Libros_Docker.service.LibroService;

/*import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.contenerizacion.Libros_Docker.service.LibroService;

@SpringBootTest
class LibroServiceImplTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;

    private Libro libro;

    @BeforeEach
    void setUp() {
    libro = new Libro();
    libro.setId(1L);
    libro.setTitulo("Un Mundo Feliz");
    libro.setAutor("Aldous Huxley");
    libro.setIsbn("978-0060850524");
    libro.setAnioPublicacion(1932);
    }

    @Test
    void guardarLibro() {
        Libro libroAGuardar = new Libro();
        libroAGuardar.setTitulo("1984");
        libroAGuardar.setAutor("George Orwell");
        libroAGuardar.setIsbn("978-0451524935");
        libroAGuardar.setAnioPublicacion(1949);
    
        when(libroRepository.save(any(Libro.class))).thenReturn(libroAGuardar);
        Libro guardado = libroService.guardarLibro(libroAGuardar);
        assertNotNull(guardado);
        assertEquals("1984", guardado.getTitulo());
    }

    
    @Test
    void listarLibros() {
        when(libroRepository.findAll()).thenReturn(List.of(libro));
        List<Libro> libros = libroService.listarLibros();
        assertEquals(1, libros.size());
        assertEquals("Un Mundo Feliz", libros.get(0).getTitulo()); // Añadimos una verificación del título
    }

    @Test
        void obtenerLibroPorId() {
        when(libroRepository.findById(1L)).thenReturn(Optional.of(libro));
        Libro encontrado = libroService.obtenerLibroPorId(1L);
        assertEquals("Un Mundo Feliz", encontrado.getTitulo());
    }

    @Test
    void eliminarLibro() {
        libroService.eliminarLibro(1L);
        verify(libroRepository, times(1)).deleteById(1L);
    }
}*/

 import com.contenerizacion.Libros_Docker.model.Libro;
 import com.contenerizacion.Libros_Docker.repository.LibroRepository;
 import com.contenerizacion.Libros_Docker.service.LibroImplementation;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;
 import org.mockito.*;

 import java.util.Arrays;
 import java.util.List;
 import java.util.Optional;

 import static org.junit.jupiter.api.Assertions.*;
 import static org.mockito.Mockito.*;

 public class LibroServiceTest {

     @Mock
     private LibroRepository libroRepository;

     @InjectMocks
     private LibroImplementation libroService;

     private Libro libro1;
     private Libro libro2;

     @BeforeEach
     void setUp() {
         MockitoAnnotations.openMocks(this);
         libro1 = new Libro("Cien años de soledad", "Gabriel García Márquez", "978-0307350605", 1967);
         libro1.setId(1L);
         libro2 = new Libro("El amor en los tiempos del cólera", "Gabriel García Márquez", "978-0307389734", 1985);
         libro2.setId(2L);
     }

     @Test
     void testListarLibros() {
         when(libroRepository.findAll()).thenReturn(Arrays.asList(libro1, libro2));

         List<Libro> resultado = libroService.listarLibros();

         assertEquals(2, resultado.size());
         verify(libroRepository, times(1)).findAll();
         assertEquals("Cien años de soledad", resultado.get(0).getTitulo());
         assertEquals("El amor en los tiempos del cólera", resultado.get(1).getTitulo());
     }

     @Test
     void testObtenerLibroPorId() {
         when(libroRepository.findById(1L)).thenReturn(Optional.of(libro1));

         Libro resultado = libroService.obtenerLibroPorId(1L);

         assertNotNull(resultado);
         assertEquals("Cien años de soledad", resultado.getTitulo());
         assertEquals("Gabriel García Márquez", resultado.getAutor());
     }

     @Test
     void testGuardarLibro() {
         Libro libroAGuardar = new Libro("El Principito", "Antoine de Saint-Exupéry", "978-0156012195", 1943);
         Libro guardado = new Libro("El Principito", "Antoine de Saint-Exupéry", "978-0156012195", 1943);
         guardado.setId(3L); // Simulamos que la base de datos le asigna un ID

         when(libroRepository.save(libroAGuardar)).thenReturn(guardado);

         Libro resultado = libroService.guardarLibro(libroAGuardar);

         assertNotNull(resultado.getId());
         assertEquals("El Principito", resultado.getTitulo());
         assertEquals("Antoine de Saint-Exupéry", resultado.getAutor());
     }

     @Test
     void testEliminarLibro() {
         doNothing().when(libroRepository).deleteById(1L);

         libroService.eliminarLibro(1L);

         verify(libroRepository, times(1)).deleteById(1L);
     }
 }