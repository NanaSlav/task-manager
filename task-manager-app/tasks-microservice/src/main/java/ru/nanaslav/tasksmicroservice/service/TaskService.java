/**
 * Created on 26/04/2024
 * Updated on 16/05/2024
 */
package ru.nanaslav.tasksmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nanaslav.tasksmicroservice.domain.model.*;
import ru.nanaslav.tasksmicroservice.proxy.UsersProxy;
import ru.nanaslav.tasksmicroservice.repository.TaskRepository;

import java.util.Date;

/**
 * Сервис для работы с задачами
 *
 * @author nana
 */
@Service
public class TaskService {
    // TODO  Нужно добавить проверку доступа к изменениям задачи
    @Autowired
    UsersProxy usersProxy;

    @Autowired
    TaskRepository taskRepository;

    /**
     * Создание задачи
     *
     * @param task задача
     * @param token токен пользователя
     * @return {@link Task}
     */
    public Task createTask(Task task, String token) {
        // TODO добавить проект
        task.setAuthor(usersProxy.getCurrentUsername(token).getBody());
        task.setStatus(Status.OPEN);
        if (task.getAssignee() == null) {
            task.setAssignee(task.getAuthor());
        }
        updateHistory(task, HistoryEventType.CREATION, task.getAuthor());
        return taskRepository.insert(task);
    }

    /**
     * Обновление задачи
     *
     * @param updatedTask измененная задача
     * @param token токен пользователя
     * @return {@link Task}
     */
    public Task updateTask(Task updatedTask, String token) {
        Task task = taskRepository.findTaskById(updatedTask.getId());
        // TODO исключение, если задача не найдена
        if (task != null) {
            String username = usersProxy.getCurrentUsername(token).getBody();
            updateDescription(task, updatedTask.getDescription(), username);
            updateStatus(task, updatedTask.getStatus(), username);
            changeAssignee(task, updatedTask.getAssignee(), username);
            changeDifficulty(task, updatedTask.getDifficulty(), username);
            changePriority(task, updatedTask.getPriority(), username);
            changeDeadline(task, updatedTask.getDeadline(), username);
        }
        return task;
    }

    /**
     * Изменение описания задачи
     *
     * @param taskId id задачи
     * @param description новое описание
     * @param token токен пользователя
     * @return {@link Task}
     */
    public Task updateDescription(String taskId, String description, String token) {
        Task task = taskRepository.findTaskById(taskId);
        // TODO исключение, если задача не найдена
        if (task != null) {
            updateDescription(task, description, usersProxy.getCurrentUsername(token).getBody());
        }
        return task;
    }

    /**
     * Изменение описания задачи
     *
     * @param task задача
     * @param description  новое описание
     * @param username имя пользователя
     * @return {@link Task}
     */
    private Task updateDescription(Task task, String description, String username) {
        task.setDescription(description);
        updateHistory(task, HistoryEventType.DESCRIPTION_CHANGED, username);
        taskRepository.insert(task);
        return task;
    }

    /**
     * Изменение статуса
     *
     * @param taskId id задачи
     * @param status обновленный статус
     * @param token токен пользователя
     * @return {@link Task}
     */
    public Task updateStatus(String taskId, Status status, String token) {
        Task task = taskRepository.findTaskById(taskId);
        // TODO исключение, если задача не найдена
        if (task != null) {
            updateStatus(task, status, usersProxy.getCurrentUsername(token).getBody());
        }
        return task;
    }

    /**
     * Изменение статуса
     *
     * @param task задача
     * @param status обновленный статус
     * @param username токен пользователя
     * @return {@link Task}
     */
    private Task updateStatus(Task task, Status status, String username) {
        // Если статус не изменился - ничего не меняем
        if (!task.getStatus().equals(status)) {
            task.setStatus(status);
            updateHistory(task, HistoryEventType.STATUS_CHANGED, username);
            taskRepository.insert(task);
        }
        return task;
    }

    /**
     * Добавление нового события в историю задачи
     *
     * @param task задача
     * @param historyEventType тип события
     * @param username имя пользователя
     * @return {@link Task}
     */
    private Task updateHistory(Task task, HistoryEventType historyEventType, String username) {
        HistoryEvent event = HistoryEvent.builder()
                .historyEventType(historyEventType)
                .author(username)
                .eventTime(new Date())
                .build();
        task.addHistoryEvent(event);
        return task;
    }

    /**
     * Изменение исполнителя
     *
     * @param taskId id задачи
     * @param assignee новый исполнитель
     * @param token токен пользователя
     * @return {@link Task}
     */
    public Task changeAssignee(String taskId, String assignee, String token) {
        Task task = taskRepository.findTaskById(taskId);
        // TODO исключение, если задача не найдена
        if (task != null) {
            changeAssignee(task, assignee, usersProxy.getCurrentUsername(token).getBody());
        }
        return task;
    }

    /**
     * Изменение исполнителя
     *
     * @param task задача
     * @param assignee новый исполнитель
     * @param username токен пользователя
     * @return {@link Task}
     */
    private Task changeAssignee(Task task, String assignee, String username) {
        // Если исполнитель не изменился - ничего не делаем
        if (!task.getAssignee().equals(assignee)) {
            task.setAssignee(assignee);
            updateHistory(task, HistoryEventType.ASSIGNEE_CHANGED, username);
            taskRepository.insert(task);
        }
        return task;
    }

    /**
     * Добавление комментария к задаче
     *
     * @param taskId id задачи
     * @param text текст комментария
     * @param token токен пользователя
     * @return {@link Task}
     */
    public Task addComment(String taskId, String text, String token) {
        Task task = taskRepository.findTaskById(taskId);
        // TODO исключение, если задача не найдена
        if (task != null) {
            addComment(task, text, usersProxy.getCurrentUsername(token).getBody());
        }
        return task;
    }

    /**
     * Добавление комментария к задаче
     *
     * @param task задача
     * @param text текст комментария
     * @param username токен пользователя
     * @return {@link Task}
     */
    private Task addComment(Task task, String text, String username) {
        Comment comment = Comment.builder()
                .text(text)
                .author(username)
                .creationTime(new Date())
                .build();
        task.addComment(comment);
        updateHistory(task, HistoryEventType.COMMENT_ADDED, username);
        taskRepository.insert(task);
        return task;
    }

    /**
     * Изменить оценку сложности задачи
     *
     * @param taskId id задачи
     * @param difficulty оценка сложности
     * @param token токен пользователя
     * @return {@link Task}
     */
    public Task changeDifficulty(String taskId, Difficulty difficulty, String token) {
        Task task = taskRepository.findTaskById(taskId);
        // TODO исключение, если задача не найдена
        if (task != null) {
            changeDifficulty(task, difficulty, usersProxy.getCurrentUsername(token).getBody());
        }
        return task;
    }

    /**
     * Изменить оценку сложности задачи
     *
     * @param task задача
     * @param difficulty оценка сложности задачи
     * @param username токен пользователя
     * @return {@link Task}
     */
    private Task changeDifficulty(Task task, Difficulty difficulty, String username) {
        // Если сложность не изменилась - ничего не делаем
        if (!task.getDifficulty().equals(difficulty)) {
            task.setDifficulty(difficulty);
            updateHistory(task, HistoryEventType.DIFFICULTY_CHANGED, username);
            taskRepository.insert(task);
        }
        return task;
    }

    /**
     * Изменение приоритета выполнения задачи
     *
     * @param taskId id задачи
     * @param priority приоритет выполнения
     * @param token токен пользователя
     * @return {@link Task}
     */
    public Task changePriority(String taskId, Priority priority, String token) {
        Task task = taskRepository.findTaskById(taskId);
        // TODO исключение, если задача не найдена
        if (task != null) {
            changePriority(task, priority, usersProxy.getCurrentUsername(token).getBody());
        }
        return task;
    }

    /**
     * Изменение приоритета выполнения задачи
     *
     * @param task задача
     * @param priority приоритет выполнения
     * @param username токен пользователя
     * @return {@link Task}
     */
    private Task changePriority(Task task, Priority priority, String username) {
        // Если приоритет не изменился - ничего не делаем
        if (!task.getPriority().equals(priority)) {
            task.setPriority(priority);
            updateHistory(task, HistoryEventType.PRIORITY_CHANGED, username);
            taskRepository.insert(task);
        }
        return task;
    }

    /**
     * Изменение срока выполнения задачи
     *
     * @param taskId id задачи
     * @param deadline срок выполнения задачи
     * @param token токен пользователя
     * @return {@link Task}
     */
    public Task changeDeadline(String taskId, Date deadline, String token) {
        Task task = taskRepository.findTaskById(taskId);
        // TODO исключение, если задача не найдена
        if (task != null) {
            changeDeadline(task, deadline, usersProxy.getCurrentUsername(token).getBody());
        }
        return task;
    }

    /**
     * Изменение срока выполнения задачи
     *
     * @param task задача
     * @param deadline срок выполнения задачи
     * @param username токен пользователя
     * @return {@link Task}
     */
    private Task changeDeadline(Task task, Date deadline, String  username) {
        // Если срок выполнения не изменился - ничего не делаем
        if (!task.getDeadline().equals(deadline)) {
            task.setDeadline(deadline);
            updateHistory(task, HistoryEventType.DEADLINE_CHANGED, username);
            taskRepository.insert(task);
        }
        return task;
    }
}