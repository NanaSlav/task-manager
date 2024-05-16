/**
 * Created on 26/04/2024
 */
package ru.nanaslav.tasksmicroservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.nanaslav.tasksmicroservice.domain.model.Status;
import ru.nanaslav.tasksmicroservice.domain.model.Task;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    Task findTaskById(String id);
    List<Task> findAllByAssignee(String username);
    List<Task> findAllByAuthor(String username);
    List<Task> findAllByAssigneeAndStatus(String username, Status status);
    List<Task> findAll();
    List<Task> findAllByProject(String project);

}
