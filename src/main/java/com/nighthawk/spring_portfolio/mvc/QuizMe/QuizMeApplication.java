package com.nighthawk.spring_portfolio.mvc.QuizMe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@SpringBootApplication
@RestController
public class QuizMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizMeApplication.class, args);
	}

    @Autowired
    private QuizMeJpaRepository repository;

   @GetMapping("/test")
    public ResponseEntity<List<FlashcardSets>> getFlashcard () {
        return new ResponseEntity<>( repository.findAll(), HttpStatus.OK);
		
    }

	@PostMapping("/new")
	public ResponseEntity<FlashcardSets> createFlashcard () {
		Map <String, String> flashcards = new HashMap<>();
		flashcards.put("Manifest Destiny", "The belief that God destined Americans to expand West");
		flashcards.put("Mexican American War", "1846-1848 war in Texas over its border");
		FlashcardSets APUSHSet = new FlashcardSets();
		APUSHSet.setName("APUSH Set");
		APUSHSet.setFlashcards(flashcards);
		repository.save(APUSHSet);
		return new ResponseEntity<>(APUSHSet, HttpStatus.OK);
	}
}
