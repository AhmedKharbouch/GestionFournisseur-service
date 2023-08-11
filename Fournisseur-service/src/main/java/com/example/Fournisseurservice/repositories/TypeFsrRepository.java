package com.example.Fournisseurservice.repositories;

import com.example.Fournisseurservice.entities.Fournisseur;
import com.example.Fournisseurservice.entities.TypeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;


public interface TypeFsrRepository extends JpaRepository<TypeFournisseur,Long> {

    TypeFournisseur findTypeFournisseurById(Long id);
    TypeFournisseur findTypeFournisseurByNom(String nom);


}
