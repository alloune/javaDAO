package com.dnd.microdnd.web.controller;

import com.dnd.microdnd.web.CharacterRepository;
import com.dnd.microdnd.model.Hero;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ApiOperation(value = "Api pour le CRUD sur les personnages de notre jeu.")
@RestController
public class CharacterController {

    @Autowired
    CharacterRepository characterRepository;


    @GetMapping("/characters/")
    public List<Hero> charList(){
        return characterRepository.findAll();
    }

    @GetMapping(value = "/characters/{id}")
    public ResponseEntity showCharacter(@PathVariable int id)
    {
        //Optionnal type "hybride" --> pour retransformer à la fin -->get()
        Optional<Hero> showHero = characterRepository.findById(id);
        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(showHero.isPresent()){

           return new ResponseEntity<Hero>(showHero.get(),HttpStatus.OK);
        }
        return responseEntity;

    }
    @DeleteMapping(value = "/characters/{id}")
    public void deleteCharacter(@PathVariable int id){
        Optional<Hero> heroTodelete = characterRepository.findById(id);
        if(characterRepository.findById(id).isPresent()){
            Hero charToDelete = characterRepository.findById(id).get();
            characterRepository.delete(charToDelete);
        }

    }
    @ApiOperation(value = "On récupère l'objet puis on lui affecte les nouvelles valeurs données en input")
    @PutMapping(value = "/characters/{id}")
    public Hero updateCharacter(@PathVariable int id, @RequestBody Hero hero){
        Optional<Hero> toUpdate = characterRepository.findById(id);
        if(toUpdate.isPresent()){
            Hero finalHero = toUpdate.get();
            finalHero.save(hero);
        }
        Hero heroUpdated = characterDao.update(id, hero);
        return heroUpdated;
    }

    @PostMapping("/characters/")
    public ResponseEntity<Hero> saveCharacter(@RequestBody Hero hero){
       Hero heroAdded = characterDao.save(hero);
       if(Objects.isNull(heroAdded)){
           return ResponseEntity.noContent().build();
       }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(heroAdded.getId())
                .toUri();
       return ResponseEntity.created(location).build();
    }
}
