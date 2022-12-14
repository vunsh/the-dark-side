package com.nighthawk.spring_portfolio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("cardRepository")
public interface CardRepository extends MongoRepository<Card, String> {

}
