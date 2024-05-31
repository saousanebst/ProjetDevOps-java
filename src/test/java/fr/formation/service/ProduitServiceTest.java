package fr.formation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.formation.repo.ProduitRepository;
import fr.model.Produit;

@SpringBootTest(classes = ProduitService.class)

public class ProduitServiceTest {
    @MockBean
    private ProduitService produitService;
    @MockBean
    private ProduitRepository  produitRepository;
    @Test
    void shouldReturnList() {
        // given
        List<Produit> produits = new ArrayList<>();
        Produit produit1 = new Produit();
        produit1.setNom("cahier");
        produit1.setPrix(new BigDecimal("2.0"));
        Produit produit2 = new Produit();

        produit2.setNom("livre ");
        produit2.setPrix(new BigDecimal("1.0"));

        
        produits.add(produit1);
        produits.add(produit2);


        // Mocking the service method
       // when(this.produitService.getAllProduit()).thenReturn(produits);

        // when
       // List<Produit> result = this.produitService.getAllProduit();

        // then
        //assertEquals(produits, result);

     Mockito.when(this.produitService.getAllProduit(ArgumentMatchers.any(Produit.class))).thenReturn(produits);
        
        // when / then
       Assertions.assertEquals(this.produitService.getAllProduit(), produits);

        //then
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
