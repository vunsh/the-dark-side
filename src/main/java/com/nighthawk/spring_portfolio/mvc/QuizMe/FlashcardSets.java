package com.nighthawk.spring_portfolio.mvc.QuizMe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

import javax.persistence.*;

@Data  // Annotations to simplify writing code (ie constructors, setters)
@NoArgsConstructor
@AllArgsConstructor
@Entity // Annotation to simplify creating an entity, which is a lightweight persistence domain object. Typically, an entity represents a table in a relational database, and each entity instance corresponds to a row in that table.

public class FlashcardSets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map <String, String> flashcards = new HashMap <>();
}