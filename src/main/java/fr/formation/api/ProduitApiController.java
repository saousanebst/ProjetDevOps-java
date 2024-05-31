package fr.formation.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.service.ProduitService;
import fr.model.Produit;

@RestController
@RequestMapping("/api/produit")
public class ProduitApiController {
    

@Autowired
private ProduitService produitservice;


@GetMapping
public List<Produit> FindAllProduit(){
    return produitservice.getAllProduit();}


@PostMapping("/ajout")
 @ResponseStatus(HttpStatus.CREATED)
public Produit InsertProduit (@RequestBody Produit produit){

    return produitservice.createProduit(produit);
}
    
}
