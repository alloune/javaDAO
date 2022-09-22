package com.dnd.microdnd.web;

import com.dnd.microdnd.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Hero, Integer> {


}
