package com.example.Fournisseurservice.webController;

import com.example.Fournisseurservice.entities.Fournisseur;
import com.example.Fournisseurservice.entities.TypeFournisseur;
import com.example.Fournisseurservice.exceptions.FournisseurExistException;
import com.example.Fournisseurservice.exceptions.TypeFsrExistException;
import com.example.Fournisseurservice.repositories.FournisseurRepository;
import com.example.Fournisseurservice.repositories.TypeFsrRepository;
import com.example.Fournisseurservice.services.FournisseurService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1/FSR")
public class FSRController implements FournisseurService {


    private FournisseurRepository fournisseurRepository;

    private TypeFsrRepository typeFsrRepository;


    @Override
    @GetMapping("/FSRS")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Collection<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }

    @Override
    @GetMapping("/FSR/{id}")
    public Fournisseur getFournisseurById(@PathVariable Long id) {
        return fournisseurRepository.findFournisseurById(id);
    }


    @Override
    @GetMapping(path = "/searchFsr/{nom}")
    public Collection<Fournisseur> searchCategory(@PathVariable("nom") String nom) {
        return fournisseurRepository.findFournisseurByName("%" + nom + "%");

    }

    @Override
    @PostMapping("/addFSR")
    public Fournisseur addFournisseur(@RequestBody Fournisseur fournisseur) throws FournisseurExistException {
        //verify if fournisseur exist and throw exception fournisseur exist
        if (fournisseurRepository.findFournisseurByNom(fournisseur.getNom()) != null) {
            throw new FournisseurExistException("Fournisseur already exist");
        }
        System.out.println(fournisseur.getTypeFournisseur().getNom());
        fournisseur.setCreatedAt(new Date());
        return fournisseurRepository.save(fournisseur);
    }

    @Override
    @PutMapping("/FSR/{id}")
    public Fournisseur updateFournisseur(@RequestBody Fournisseur fournisseur, @PathVariable Long id) {
        Fournisseur fsr1 = fournisseurRepository.findFournisseurById(id);
        fsr1.setNom(fournisseur.getNom());
        fsr1.setTelephone(fournisseur.getTelephone());
        fsr1.setTelephoneFixe(fournisseur.getTelephoneFixe());
        fsr1.setEmail(fournisseur.getEmail());
        fsr1.setAdresse(fournisseur.getAdresse());
        fsr1.setVille(fournisseur.getVille());
        fsr1.setModifiedAt(new Date());
        return fournisseurRepository.save(fsr1);
    }

    @Override
    @DeleteMapping("/FSR/{id}")
    public void deleteFournisseur(@PathVariable Long id) {
        fournisseurRepository.deleteById(id);
    }

    /*********************************** TYPE FOURNISSEUR *************************************/
    @Override
    @GetMapping("/types")
    public Collection<TypeFournisseur> getAllTypes() {
        return typeFsrRepository.findAll();
    }

    @Override
    @GetMapping("/types/{id}")
    public TypeFournisseur getTypeById(@PathVariable Long id) {
        return typeFsrRepository.findTypeFournisseurById(id);
    }

    @Override
    @PostMapping("/types")
    public TypeFournisseur addType(@RequestBody TypeFournisseur typeFournisseur) throws TypeFsrExistException {

        //verify if type exist and throw exception type exist
        if (typeFsrRepository.findTypeFournisseurByNom(typeFournisseur.getNom()) != null) {
            throw new TypeFsrExistException("Type fournisseur already exist");
        }
        typeFournisseur.setCreatedAt(new Date());
        return typeFsrRepository.save(typeFournisseur);
    }

    @Override
    @PutMapping("/types/{id}")
    public TypeFournisseur updateType(@RequestBody TypeFournisseur typeFournisseur, @PathVariable Long id) {
        TypeFournisseur typeFsr1 = typeFsrRepository.findTypeFournisseurById(id);
        typeFsr1.setNom(typeFournisseur.getNom());
        typeFsr1.setModifiedAt(new Date());
        return typeFsrRepository.save(typeFsr1);
    }

    @Override
    @DeleteMapping("/types/{id}")
    public void deleteType(@PathVariable Long id) {
        typeFsrRepository.deleteById(id);
    }

    @Override
    public List<Fournisseur> getAllFsrs() {
        return null;
    }


}
