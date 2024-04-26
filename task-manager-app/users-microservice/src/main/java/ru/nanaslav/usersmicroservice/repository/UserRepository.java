package ru.nanaslav.usersmicroservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.nanaslav.usersmicroservice.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}
