package fr.formation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.model.Produit;
import fr.formation.repo.ProduitRepository;

@Service
public class ProduitService {
    
@Autowired 
private ProduitRepository produitRepository;

// lister tout les produits
public List<Produit> getAllProduit(){

     return Optional
                .ofNullable(this.produitRepository.findAll())
                .orElse(new ArrayList<>());
}


//creer un produit 
public Produit createProduit(Produit produit){

   return this.produitRepository.save(produit);

} 
}
