package com.contenerizacion.Libros_Docker.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.hasSize;

import com.contenerizacion.Libros_Docker.model.Libro;
import com.contenerizacion.Libros_Docker.service.LibroService;

@SpringBootTest
@AutoConfigureMockMvc
public class LibroControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroService libroService;  // Mockeamos el servicio para evitar interacción real con la base de datos

    @Test
    public void testListarLibros() throws Exception {
        // Crear libros de ejemplo con valores para cada campo
        List<Libro> librosMock = Arrays.asList(
            new Libro("El Quijote", "Miguel de Cervantes", "978-3-16-148410-0", 1605),
            new Libro("Cien años de soledad", "Gabriel García Márquez", "978-0-06-088328-7", 1967)
        );
        
        // Simular la respuesta del servicio
        when(libroService.listarLibros()).thenReturn(librosMock);
    
        // Realizar la solicitud GET al endpoint
        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))) // Verifica que hay dos libros
                .andExpect(jsonPath("$[0].titulo").value("El Quijote"))
                .andExpect(jsonPath("$[0].autor").value("Miguel de Cervantes"))
                .andExpect(jsonPath("$[0].isbn").value("978-3-16-148410-0"))
                .andExpect(jsonPath("$[0].anioPublicacion").value(1605))
                .andExpect(jsonPath("$[1].titulo").value("Cien años de soledad"))
                .andExpect(jsonPath("$[1].autor").value("Gabriel García Márquez"))
                .andExpect(jsonPath("$[1].isbn").value("978-0-06-088328-7"))
                .andExpect(jsonPath("$[1].anioPublicacion").value(1967));
    }
    

    @Test
    public void testObtenerLibro() throws Exception {
        Long libroId = 1L;
    
        // Crear un libro mock con todos los campos
        Libro libroMock = new Libro("El Alquimista", "Paulo Coelho", "978-0-06-112241-5", 1988);
    
        // Simulación de la respuesta del servicio
        when(libroService.obtenerLibroPorId(libroId)).thenReturn(libroMock);
    
        // Realizamos la solicitud GET al endpoint
        mockMvc.perform(get("/api/libros/{id}", libroId))
                .andExpect(status().isOk())  // Asegúrate de que el estado sea 200 (OK)
                .andExpect(jsonPath("$.titulo").value("El Alquimista"))  // Validar el campo 'titulo'
                .andExpect(jsonPath("$.autor").value("Paulo Coelho"))  // Validar el campo 'autor'
                .andExpect(jsonPath("$.isbn").value("978-0-06-112241-5"))  // Validar el campo 'isbn'
                .andExpect(jsonPath("$.anioPublicacion").value(1988));  // Validar el campo 'anioPublicacion'
    }

    @Test
    public void testGuardarLibro() throws Exception {
        // Crear un libro con datos para los campos
        Libro libroMock = new Libro("El Alquimista", "Paulo Coelho", "978-0-06-112241-5", 1988);
    
        // Simulación de la respuesta del servicio
        when(libroService.guardarLibro(any(Libro.class))).thenReturn(libroMock);
    
        // Realizar la solicitud POST al endpoint
        mockMvc.perform(post("/api/libros")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"titulo\":\"El Alquimista\",\"autor\":\"Paulo Coelho\",\"isbn\":\"978-0-06-112241-5\",\"anioPublicacion\":1988}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("El Alquimista"))
                .andExpect(jsonPath("$.autor").value("Paulo Coelho"))
                .andExpect(jsonPath("$.isbn").value("978-0-06-112241-5"))
                .andExpect(jsonPath("$.anioPublicacion").value(1988));
    }
    

    // Agregar más pruebas para PUT y DELETE si lo deseas
}
