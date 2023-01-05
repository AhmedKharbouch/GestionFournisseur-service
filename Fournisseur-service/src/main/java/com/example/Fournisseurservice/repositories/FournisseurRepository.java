package com.example.Fournisseurservice.repositories;

import com.example.Fournisseurservice.entities.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FournisseurRepository extends JpaRepository<Fournisseur,Long> {

   Fournisseur findFournisseurById(Long id);
    Fournisseur findFournisseurByNom(String nom_Fsr);
}
