package ru.nanaslav.projectsmicroservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.nanaslav.projectsmicroservice.domain.model.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
    Project findProjectById(String id);
}
