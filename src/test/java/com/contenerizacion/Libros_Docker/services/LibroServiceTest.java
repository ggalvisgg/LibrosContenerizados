package com.contenerizacion.Libros_Docker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.contenerizacion.Libros_Docker.model.Libro;
import com.contenerizacion.Libros_Docker.repository.LibroRepository;
import com.contenerizacion.Libros_Docker.service.LibroImplementation;

@ExtendWith(MockitoExtension.class)
public class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;  // Mock de la capa de acceso a datos

    @InjectMocks
    private LibroImplementation libroService; // El servicio que estamos probando

    @Test
    public void testListarLibros() {
        // Simulación del comportamiento del repositorio
        List<Libro> librosMock = Arrays.asList(
            new Libro("El Alquimista", "Paulo Coelho", "978-0-06-112241-5", 1988),
            new Libro("Cien años de soledad", "Gabriel García Márquez", "978-0-06-088328-7", 1967)
        );
        when(libroRepository.findAll()).thenReturn(librosMock);
    
        // Llamada al método que estamos probando
        List<Libro> libros = libroService.listarLibros();
    
        // Verificación
        assertNotNull(libros);
        assertEquals(2, libros.size());
        verify(libroRepository, times(1)).findAll();  // Verificamos que se llamó una vez al repositorio
    }
    

    @Test
    public void testObtenerLibroPorId() {
        Long libroId = 1L;
        
        // Crear un libro mock con todos los campos
        Libro libroMock = new Libro("El Alquimista", "Paulo Coelho", "978-0-06-112241-5", 1988);
    
        // Simulación del comportamiento del repositorio
        when(libroRepository.findById(libroId)).thenReturn(Optional.of(libroMock));
    
        // Llamada al método
        Libro libro = libroService.obtenerLibroPorId(libroId);
    
        // Verificación
        assertNotNull(libro);
        assertEquals("El Alquimista", libro.getTitulo());
        assertEquals("Paulo Coelho", libro.getAutor());
        assertEquals("978-0-06-112241-5", libro.getIsbn());
        assertEquals(1988, libro.getAnioPublicacion());
        verify(libroRepository, times(1)).findById(libroId);
    }
    

    @Test
    public void testGuardarLibro() {
        // Crear un libro mock con todos los campos
        Libro libroMock = new Libro("El Alquimista", "Paulo Coelho", "978-0-06-112241-5", 1988);
    
        // Simulación del comportamiento del repositorio
        when(libroRepository.save(libroMock)).thenReturn(libroMock);
    
        // Llamada al método
        Libro libroGuardado = libroService.guardarLibro(libroMock);
    
        // Verificación
        assertNotNull(libroGuardado);
        assertEquals("El Alquimista", libroGuardado.getTitulo());
        assertEquals("Paulo Coelho", libroGuardado.getAutor());
        assertEquals("978-0-06-112241-5", libroGuardado.getIsbn());
        assertEquals(1988, libroGuardado.getAnioPublicacion());
        verify(libroRepository, times(1)).save(libroMock);
    }
    
    @Test
    public void testActualizarLibro() {
        Long libroId = 1L;
        
        // Crear libro original y el libro con los nuevos datos
        Libro libroOriginal = new Libro("El Alquimista", "Paulo Coelho", "978-0-06-112241-5", 1988);
        Libro libroActualizado = new Libro("El Alquimista (Edición Especial)", "Paulo Coelho", "978-0-06-112241-5", 2022);
        
        // Simulación del comportamiento del repositorio
        when(libroRepository.findById(libroId)).thenReturn(Optional.of(libroOriginal));
        when(libroRepository.save(libroActualizado)).thenReturn(libroActualizado);
    
        // Llamada al método
        Libro libroResultado = libroService.actualizarLibro(libroId, libroActualizado);
    
        // Verificación
        assertNotNull(libroResultado);
        assertEquals("El Alquimista (Edición Especial)", libroResultado.getTitulo());
        assertEquals("Paulo Coelho", libroResultado.getAutor());
        assertEquals("978-0-06-112241-5", libroResultado.getIsbn());
        assertEquals(2022, libroResultado.getAnioPublicacion());
        verify(libroRepository, times(1)).findById(libroId);
        verify(libroRepository, times(1)).save(libroActualizado);
    }

    @Test
    public void testEliminarLibro() {
    Long libroId = 1L;
    
    // Crear un libro mock
    Libro libroMock = new Libro("El Alquimista", "Paulo Coelho", "978-0-06-112241-5", 1988);

    // Simulación del comportamiento del repositorio
    when(libroRepository.findById(libroId)).thenReturn(Optional.of(libroMock));

    // Llamada al método
    libroService.eliminarLibro(libroId);

    // Verificación
    verify(libroRepository, times(1)).findById(libroId);
    verify(libroRepository, times(1)).deleteById(libroId);
    }

    
}
