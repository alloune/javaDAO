package com.dnd.microdnd.web.controller;

import com.dnd.microdnd.web.dao.CharacterDao;
import com.dnd.microdnd.model.Character;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
public class CharacterController {
 //Final ==> obligé d'initialiser dans le constructeur
    private final CharacterDao characterDao;

    public CharacterController(CharacterDao characterDao){
        this.characterDao = characterDao;

    }

    @GetMapping("/characters-list")
    public List<Character> charList(){
        return characterDao.findAll();
    }

    @GetMapping(value = "/character/{id}")
    public Character showCharacter(@PathVariable int id){
        return characterDao.findById(id);
    }
    @DeleteMapping(value = "/character/{id}")
    public Character deleteCharacter(@PathVariable int id){
        return characterDao.delete(id);
    }
    @PutMapping(value = "/character/{id}")
    public Character updateCharacter(@PathVariable int id, Character character){
        Character characterUpdated = characterDao.update(id, character);

        return characterUpdated;
    }

    @PostMapping("/character")
    public ResponseEntity<Character> saveCharacter(@RequestBody Character character){
       Character characterAdded = characterDao.save(character);
       if(Objects.isNull(characterAdded)){
           return ResponseEntity.noContent().build();
       }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(characterAdded.getId())
                .toUri();
       return ResponseEntity.created(location).build();
    }
}
