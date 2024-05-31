package fr.formation.service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;


import fr.formation.repo.ProduitRepository;
import fr.model.Produit;


@SpringBootTest(classes = ProduitService.class)
@ContextConfiguration(classes = ValidationAutoConfiguration.class)
public class ProduitServiceTest {

    
    @Autowired
    private ProduitService produitService;

    @MockBean
    private ProduitRepository  produitRepository;

    @Test
    void shouldReturnList() {
    

    // given
    List<Produit> laListe = new ArrayList<>();
        
    laListe.add(new Produit());
    
    Mockito.when(this.produitRepository.findAll()).thenReturn(laListe);
    
    // when / then
    Assertions.assertEquals(this.produitService.getAllProduit(), laListe);

    // then
    Mockito.verify(this.produitRepository, Mockito.times(1)).findAll();
}
   
    
    
    @Test
    void shouldReturnListIfNull() {
        // given
        Mockito.when(this.produitRepository.findAll()).thenReturn(null);

        // when / then
        Assertions.assertNotNull(this.produitService.getAllProduit());

        // then
        Mockito.verify(this.produitRepository, Mockito.times(1)).findAll();
    }
    

    @Test
    void shouldSave() {
        // given
        Produit produit = this.generateProduit();
        
        // when / then
        Assertions.assertDoesNotThrow(() -> this.produitService.createProduit( produit));

        // then
        Mockito.verify(this.produitRepository, Mockito.times(1)).save(Mockito.any());
    }

     private Produit generateProduit() {
        Produit  produit = new Produit();
        
        produit.setNom("un nom");
        produit.setPrix(new BigDecimal("1"));
        
        return produit;
    }



}
