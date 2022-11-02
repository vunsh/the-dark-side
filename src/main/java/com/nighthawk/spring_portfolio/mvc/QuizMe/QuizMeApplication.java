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

   @GetMapping("/test2")
    public ResponseEntity<List<FlashcardSets>> getFlashcard () {
        return new ResponseEntity<>( repository.findAll(), HttpStatus.OK);
		
    }

	@PostMapping("/new2")
	public ResponseEntity<FlashcardSets> createFlashcard () {
		Map <String, String> flashcards = new HashMap<>();
			flashcards.put("Accessor Method", "a method that accesses an object but does not change it, actual parameter - the expression supplied by the caller");
			flashcards.put("Binary", "the binary, or the base-2, positional numbering system represents numerical values using only two symbols, 0 and 1");
			flashcards.put("Boolean Operator", "an operator that can be applied to Boolean values. Java has 3 boolean, or logical, operators: &&, ||, and !.");
			flashcards.put("Cast", "Explicitly converting a value from one type to a different type");
			flashcards.put("Class", "a programmer defined data type, may be a blueprint or template for creating an object");
			flashcards.put("Constructor", "A set of statements for initializing a newly instantiated variable");
			flashcards.put("Formal parameter", "a variable in a method definition, initialized with an actual parameter value when the method is called");
			flashcards.put("Implicit Parameter", "The object on which a method is invoked.");
			flashcards.put("Instance variable", "a variable defined in a class for which every object of the class has its own value.");
			flashcards.put("Loop", "a sequence of instructions that is executed repeatedly");
			flashcards.put("Object", "a value of a class type");
			flashcards.put("Object Oriented Programming", "designing a program by discovering objects, their properties, and their relationships");
			flashcards.put("Primitives", "a data type structured by Java to hold a single data item (Integer, character, floating point, true/false, long)");
			flashcards.put("Syntax", "rules that define how to form instructions in a particular programming language");
			FlashcardSets APUSHSet = new FlashcardSets();
			APUSHSet.setName("APCSA Set");
			APUSHSet.setFlashcards(flashcards);
			repository.save(APUSHSet);
			return new ResponseEntity<>(APUSHSet, HttpStatus.OK);
	}
}