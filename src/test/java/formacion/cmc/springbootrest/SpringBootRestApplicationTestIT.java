package formacion.cmc.springbootrest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import formacion.cmc.springbootrest.api.v1.controller.AuthorController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SpringBootRestApplicationTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllAuthorsShouldReturnAuthorDTOList() throws Exception {

        mockMvc.perform(get(AuthorController.BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)));
    }
    
    @Test
    public void getAuthorShouldReturnAuthorById() throws Exception {
        
        mockMvc.perform(get(AuthorController.BASE_URL + "/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", equalTo(1)))
            .andExpect(jsonPath("$.name", equalTo("Maikel Night")))
            .andExpect(jsonPath("$.books").isArray())
            .andExpect(jsonPath("$.books", hasSize(1)))
            .andExpect(jsonPath("$.books[0].title", equalTo("Viernes 13")));
    }
    
    @Test
    public void getAuthorCouldReturnNotFoundIfAuthorIdDoesntExists() throws Exception {
        
        mockMvc.perform(get(AuthorController.BASE_URL + "/2"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.httpCode", equalTo(404)))
            .andExpect(jsonPath("$.httpMessage", equalTo("El recurso al que intenta acceder no existe")))
            .andExpect(jsonPath("$.moreInformation", equalTo("Author 2 not found")));
    }

    
    // ETC ...
}
