package fr.formation.model;

import java.math.BigDecimal;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="produit")
public class Produit {
    
    @Id
    @UuidGenerator
    private String id ;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private BigDecimal prix;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public BigDecimal getPrix() {
        return prix;
    }
    public void setPrix(BigDecimal bigDecimal) {
        this.prix = bigDecimal;
    }


    
}
