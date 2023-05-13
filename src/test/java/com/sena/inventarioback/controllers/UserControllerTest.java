package com.sena.inventarioback.controllers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import com.sena.inventarioback.dto.PersonDTO;
import com.sena.inventarioback.interfaces.IPersonService;
import com.sena.inventarioback.models.Person;
import com.sena.inventarioback.utils.response.DefaultResponse;
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    private MockMvc mockMvc;
    @Mock
    private IPersonService iPersonService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
    @Test
    void testFindById() throws Exception {
        // mock service response
        DefaultResponse<Person> response = new DefaultResponse<>();
        response.setData(List.of(new Person()));
        when(iPersonService.findById(1)).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));
        
        // create request
        MockHttpServletRequestBuilder request = get("/users/1").contentType(MediaType.APPLICATION_JSON);
        
        // perform request and assert response
        mockMvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();
    }

    @Test
    void testFindAll() throws Exception {
        DefaultResponse<Person> response = new DefaultResponse<>();
        response.setData(List.of(new Person()));
        when((iPersonService.findAllPaginationSizePageOrderBy(any(), any(), any()))).thenReturn(ResponseEntity.ok(response));


        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").exists())
                .andReturn();
    }
//

    
    @Test
    void testFindByDocumentTypeId() throws Exception {
        List<Person> persons = new ArrayList<>();
        when(iPersonService.findByDocumentTypeId(anyInt())).thenReturn(persons);
        mockMvc.perform(get("/users/document-type/{documentTypeId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    void testCreatePerson() throws Exception {
        DefaultResponse<Person> response = new DefaultResponse<>();
        response.setData(List.of(new Person()));
        when(iPersonService.save(any(PersonDTO.class), any(BindingResult.class), any())).thenReturn(ResponseEntity.ok(response));

        mockMvc.perform(post("/users")
        		.content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").exists())
                .andReturn();
    }

    @Test
    void testUpdatePerson() throws Exception {
        DefaultResponse<Person> response = new DefaultResponse<>();
        response.setData(List.of(new Person()));
        when(iPersonService.update(anyInt(), any(PersonDTO.class),  any(BindingResult.class),any())).thenReturn(ResponseEntity.ok(response));


        mockMvc.perform(put("/users/{id}", 1)
        		.content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").exists())
                .andReturn();
    }
    

    @Test
    void testLogin() throws Exception {
        when(iPersonService.login(anyString(), anyString())).thenReturn(true);

        mockMvc.perform(get("/users/login/username/{username}/password/{password}", "test", "test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("true"))
                .andReturn();
    }


}
