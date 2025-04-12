package com.contenerizacion.Libros_Docker.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.contenerizacion.Libros_Docker.model.Libro;
import com.contenerizacion.Libros_Docker.service.LibroImplementation;
import com.contenerizacion.Libros_Docker.service.LibroService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LibroControllerIntegrationTest{

    private MockMvc mockMvc;

    @Mock
    private LibroImplementation libroService;

    @InjectMocks
    private LibroController libroController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(libroController).build();
    }

    @Test
    void testGetAllBooks() throws Exception {
        Libro libro = new Libro();
        libro.setId(1L);
        libro.setTitulo("Cien años de soledad");
        libro.setAutor("Gabriel Garcia");
        libro.setIsbn("456789");
        libro.setAnioPublicacion(2000);

        when(libroService.listarLibros()).thenReturn(List.of(libro));

        mockMvc.perform(get("/api/libros/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Cien años de soledad"));
    }

    @Test
    void testGetBookById() throws Exception {
        Libro libro = new Libro();
        libro.setId(2L);
        libro.setTitulo("El principito");
        libro.setAutor("Marco Aurelio");
        libro.setIsbn("123456");
        libro.setAnioPublicacion(2002);

        when(libroService.obtenerLibroPorId(2L)).thenReturn(libro);

        mockMvc.perform(get("/api/libros/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("El principito"));
    }

    @Test
    void testCreateBook() throws Exception {
        Libro libro = new Libro("1984", "Orwell", "Planeta", 1949);
        when(libroService.guardarLibro(any(Libro.class))).thenReturn(libro);

        mockMvc.perform(post("/api/libros")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"titulo\":\"1984\",\"autor\":\"Orwell\",\"isbn\":\"123456\",\"anioPublicacion\":1949}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("1984"));
    }

    @Test
    void testUpdateBook() throws Exception {

        Libro libro = new Libro();
        libro.setId(2L);
        libro.setTitulo("El mundo increible de Job");
        libro.setAutor("Marco Aurelio");
        libro.setIsbn("123456");
        libro.setAnioPublicacion(2002);

        when(libroService.actualizarLibro(eq(4L) ,any(Libro.class))).thenReturn(libro);

        mockMvc.perform(put("/api/libros/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(libro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("El mundo increible de Job"));
    }

    @Test
    void testDeleteBook() throws Exception {
        doNothing().when(libroService).eliminarLibro(1L);

        mockMvc.perform(delete("/api/libros/1"))
                .andExpect(status().isNoContent());
    }
}