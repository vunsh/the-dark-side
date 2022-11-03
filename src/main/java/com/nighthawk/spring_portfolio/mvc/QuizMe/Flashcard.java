package com.nighthawk.spring_portfolio.mvc.QuizMe;
import java.io.Serializable;

public class Flashcard implements Serializable { // implements serializable to avoid error with it
    public Long id; // defines variables for id, term, definition
    public String term;
    public String definition;

    Flashcard(Long id, String term, String definition) {
        this.id = id; // flashcards have id, term , definition
        this.term = term;
        this.definition = definition;
    }
}
