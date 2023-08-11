package com.example.Fournisseurservice.webController;

import com.example.Fournisseurservice.entities.Fournisseur;
import com.example.Fournisseurservice.entities.TypeFournisseur;
import com.example.Fournisseurservice.exceptions.FournisseurExistException;
import com.example.Fournisseurservice.exceptions.TypeFsrExistException;
import com.example.Fournisseurservice.repositories.FournisseurRepository;
import com.example.Fournisseurservice.repositories.TypeFsrRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class GestionController {


    private FournisseurRepository fournisseurRepository;

    private TypeFsrRepository typeFsrRepository;

    //get all fournisseurs
    @GetMapping("/fournisseurs")
    public Collection<Fournisseur> getAllFournisseurs(){
        return fournisseurRepository.findAll();
    }
    //get fournisseur by id
    @GetMapping("/fournisseurs/{id}")
    public Fournisseur getFournisseurById(@PathVariable Long id){
        return fournisseurRepository.findFournisseurById(id);
    }

    @GetMapping(path = "/searchFsr/{nom}")
    public Collection<Fournisseur> searchCategory(@PathVariable("nom")String nom) {
        return fournisseurRepository.findFournisseurByName("%"+nom+"%");

    }
    //add fournisseur
    @PostMapping("/addfournisseurs")
    public Fournisseur addFournisseur(@RequestBody Fournisseur fournisseur) throws FournisseurExistException {
        //verify if fournisseur exist and throw exception fournisseur exist
        if(fournisseurRepository.findFournisseurByNom(fournisseur.getNom())!=null){
            throw new FournisseurExistException("Fournisseur already exist");
        }
        System.out.println(fournisseur.getTypeFournisseur().getNom());
        fournisseur.setCreatedAt(new Date());
        return fournisseurRepository.save(fournisseur);
    }

    //update fournisseur
    @PutMapping("/fournisseurs/{id}")
    public Fournisseur updateFournisseur(@RequestBody Fournisseur fournisseur,@PathVariable Long id){
        Fournisseur fournisseur1 = fournisseurRepository.findFournisseurById(id);
        fournisseur1.setNom(fournisseur.getNom());
        fournisseur1.setTelephone(fournisseur.getTelephone());
        fournisseur1.setTelephoneFixe(fournisseur.getTelephoneFixe());
        fournisseur1.setEmail(fournisseur.getEmail());
        fournisseur1.setAdresse(fournisseur.getAdresse());
        fournisseur1.setVille(fournisseur.getVille());
        fournisseur1.setModifiedAt(new Date());
        return fournisseurRepository.save(fournisseur1);
    }
    //delete fournisseur
    @DeleteMapping("/fournisseurs/{id}")
    public void deleteFournisseur(@PathVariable Long id){
        fournisseurRepository.deleteById(id);
    }

    /*********************************** TYPE FOURNISSEUR *************************************/

    //get all types
    @GetMapping("/types")
    public Collection<TypeFournisseur> getAllTypes(){
        return typeFsrRepository.findAll();
    }

    //get type by id
    @GetMapping("/types/{id}")
    public TypeFournisseur getTypeById(@PathVariable Long id){
        return typeFsrRepository.findTypeFournisseurById(id);
    }

    //add type
    @PostMapping("/types")
    public TypeFournisseur addType(@RequestBody TypeFournisseur typeFournisseur) throws TypeFsrExistException {

        //verify if type exist and throw exception type exist
        if(typeFsrRepository.findTypeFournisseurByNom(typeFournisseur.getNom())!=null){
            throw new TypeFsrExistException("Type fournisseur already exist");
        }
        typeFournisseur.setCreatedAt(new Date());
        return typeFsrRepository.save(typeFournisseur);
    }

    //update type
    @PutMapping("/types/{id}")
    public TypeFournisseur updateType(@RequestBody TypeFournisseur typeFournisseur,@PathVariable Long id){
        TypeFournisseur typeFournisseur1 = typeFsrRepository.findTypeFournisseurById(id);
        typeFournisseur1.setNom(typeFournisseur.getNom());
        typeFournisseur1.setModifiedAt(new Date());
        return typeFsrRepository.save(typeFournisseur1);
    }

    //delete type
    @DeleteMapping("/types/{id}")
    public void deleteType(@PathVariable Long id){
        typeFsrRepository.deleteById(id);
    }

}
