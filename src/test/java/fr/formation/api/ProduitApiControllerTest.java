package fr.formation.api;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import fr.formation.repo.ProduitRepository;
import fr.formation.service.ProduitService;
import fr.model.Produit;

@WebMvcTest(ProduitApiController.class)
@WithMockUser
@ActiveProfiles("test")
public class ProduitApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProduitRepository produitRepository;

    @MockBean
    private ProduitService produitService;

    @Test
    void shouldFindAllStatusOk() throws Exception {
        //given

        // when
        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/api/produit")
        )

        // then
        .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.produitService).getAllProduit();
    }

    @Test
    void shouldAddStatusOk() throws Exception {
         // given
         Produit produit = new Produit();
         produit.setNom("stylos");
         produit.setPrix(new BigDecimal("10"));
 
        // Mockito.when(produitRepository.save(produit)).thenReturn(produit);
         Mockito.when(produitService.createProduit(ArgumentMatchers.any(Produit.class))).thenReturn(produit);

         // when
         this.mockMvc.perform(
            MockMvcRequestBuilders
                .post("/api/produit/ajout")
                .contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.json(produit))
         )

         //then  


         .andExpect(MockMvcResultMatchers.status().isCreated());
         Mockito.verify(produitService).createProduit(ArgumentMatchers.any(Produit.class));
 
         //Mockito.verify(produitRepository).save(produit);
     }


     private String json(Produit produit) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(produit);
    }
    
}
