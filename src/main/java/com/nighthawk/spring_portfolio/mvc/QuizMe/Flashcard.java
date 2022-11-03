package com.nighthawk.spring_portfolio.mvc.QuizMe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

public class Flashcard implements Serializable {
    public Long id;
    public String term;
    public String definition;

    Flashcard(Long id, String term, String definition) {
        this.id = id;
        this.term = term;
        this.definition = definition;
    }
}
