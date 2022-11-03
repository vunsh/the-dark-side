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
		ArrayList<Flashcard> flashcards = new ArrayList<>();
		long id = 0;

		flashcards.add(new Flashcard(id++, "Accessor Method", "a method that accesses an object but does not change it, actual parameter - the expression supplied by the caller"));
		flashcards.add(new Flashcard(id++, "Binary", "the binary, or the base-2, positional numbering system represents numerical values using only two symbols, 0 and 1"));
		flashcards.add(new Flashcard(id++, "Boolean Operator", "an operator that can be applied to Boolean values. Java has 3 boolean, or logical, operators: &&, ||, and !."));
		flashcards.add(new Flashcard(id++, "Cast", "Explicitly converting a value from one type to a different type"));
		flashcards.add(new Flashcard(id++, "Class", "a programmer defined data type, may be a blueprint or template for creating an object"));
		flashcards.add(new Flashcard(id++, "Constructor", "A set of statements for initializing a newly instantiated variable"));
		flashcards.add(new Flashcard(id++, "Formal parameter", "a variable in a method definition, initialized with an actual parameter value when the method is called"));
		flashcards.add(new Flashcard(id++, "Implicit Parameter", "The object on which a method is invoked."));
		flashcards.add(new Flashcard(id++, "Instance variable", "a variable defined in a class for which every object of the class has its own value."));
		flashcards.add(new Flashcard(id++, "Loop", "a sequence of instructions that is executed repeatedly"));
		flashcards.add(new Flashcard(id++, "Object", "a value of a class type"));
		flashcards.add(new Flashcard(id++, "Object Oriented Programming", "designing a program by discovering objects, their properties, and their relationships"));
		flashcards.add(new Flashcard(id++, "Primitives", "a data type structured by Java to hold a single data item (Integer, character, floating point, true/false, long)"));
		flashcards.add(new Flashcard(id++, "Syntax", "rules that define how to form instructions in a particular programming language"));
		FlashcardSets APCSASet = new FlashcardSets();
		APCSASet.setName("APCSA Set");
		APCSASet.setFlashcards(flashcards);
		repository.save(APCSASet);
		return new ResponseEntity<>(APCSASet, HttpStatus.OK);
	}
}
