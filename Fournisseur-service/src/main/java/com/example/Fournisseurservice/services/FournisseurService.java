package com.example.Fournisseurservice.services;


import com.example.Fournisseurservice.entities.Fournisseur;
import com.example.Fournisseurservice.entities.TypeFournisseur;
import com.example.Fournisseurservice.exceptions.FournisseurExistException;
import com.example.Fournisseurservice.exceptions.TypeFsrExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface FournisseurService {

    Collection<Fournisseur> getAllFournisseurs();

    Fournisseur getFournisseurById(Long id);

    Collection<Fournisseur> searchCategory(String nom);
    Fournisseur addFournisseur(Fournisseur fournisseur) throws FournisseurExistException;
    Fournisseur updateFournisseur(Fournisseur fournisseur,Long id);
    void deleteFournisseur(Long id);
    Collection<TypeFournisseur> getAllTypes();
    TypeFournisseur getTypeById(Long id);
    TypeFournisseur addType(TypeFournisseur typeFournisseur) throws TypeFsrExistException;
    TypeFournisseur updateType(TypeFournisseur typeFournisseur,Long id);
     void deleteType(Long id);
    List<Fournisseur> getAllFsrs();

}
