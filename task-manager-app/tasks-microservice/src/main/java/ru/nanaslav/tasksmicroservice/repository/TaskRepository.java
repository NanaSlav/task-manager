/**
 * Created on 26/04/2024
 */
package ru.nanaslav.tasksmicroservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.nanaslav.tasksmicroservice.model.Task;

public interface TaskRepository extends MongoRepository<Task, String> {
}
