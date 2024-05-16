/**
 * Created on 26/04/2024
 */
package ru.nanaslav.tasksmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nanaslav.tasksmicroservice.domain.model.Task;
import ru.nanaslav.tasksmicroservice.proxy.UsersProxy;

@Service
public class TaskService {
    @Autowired
    UsersProxy usersProxy;

    public Task createTask(String title, String token) {
        Task task = new Task(title);
        task.setAuthor(usersProxy.getCurrentUsername(token).getBody());
        return task;
    }

}
