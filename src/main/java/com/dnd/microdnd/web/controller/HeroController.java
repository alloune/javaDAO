package com.dnd.microdnd.web.controller;

import com.dnd.microdnd.web.HeroRepository;
import com.dnd.microdnd.model.Hero;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@ApiOperation(value = "Api pour le CRUD sur les personnages de notre jeu.")
@RestController
public class HeroController {

    @Autowired
    HeroRepository heroRepository;


    @GetMapping("/characters/")
    public List<Hero> charList(){
        return heroRepository.findAll();
    }

    @GetMapping(value = "/characters/{id}")
    public ResponseEntity showCharacter(@PathVariable int id)
    {
        //Optionnal type "hybride" --> pour retransformer à la fin -->get()
        Optional<Hero> showHero = heroRepository.findById(id);
        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(showHero.isPresent()){

           return new ResponseEntity<Hero>(showHero.get(),HttpStatus.OK);
        }
        return responseEntity;

    }
    @DeleteMapping(value = "/characters/{id}")
    public void deleteCharacter(@PathVariable int id){
        Optional<Hero> heroTodelete = heroRepository.findById(id);
        if(heroTodelete.isPresent()){
            heroRepository.deleteById(id);
        }

    }
    @ApiOperation(value = "On récupère l'objet puis on lui affecte les nouvelles valeurs données en input")
    @PutMapping(value = "/characters/{id}")
    public void updateCharacter(@PathVariable int id, @RequestBody Hero hero){
        Optional<Hero> heroToUpdate = heroRepository.findById(id);
        if(heroToUpdate.isPresent()){
            Hero updatedHero = heroToUpdate.get();
            updatedHero.setName(hero.getName());
            updatedHero.setType(hero.getType());
            switch (hero.getType()){
                case "Paladin":
                    updatedHero.setHp(12);
                    break;
                case "Magicien":
                    updatedHero.setHp(6);
                    break;
                case "Voleur":
                    updatedHero.setHp(8);
                    break;
                case "Pecno":
                    updatedHero.setHp(3);
                    break;
            }


            heroRepository.save(updatedHero);

        }


    }

    @PostMapping("/characters/")
    public void saveCharacter(@RequestBody Hero hero){
       heroRepository.save(hero);
    }
}
