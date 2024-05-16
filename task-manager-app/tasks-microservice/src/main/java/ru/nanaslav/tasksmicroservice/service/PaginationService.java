/**
 * Created on 16/05/2024
 */
package ru.nanaslav.tasksmicroservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.nanaslav.tasksmicroservice.domain.model.Task;

import java.util.List;

/**
 * Сервис для разделения данных на отдельные станицы
 *
 * @author nana
 */
@Service
public class PaginationService {

    /**
     * Получение страницы из списка
     *
     * @param tasks список задач
     * @param page номер страницы
     * @param size размер страницы
     * @return страница с задачами
     */
    public Page<Task> getPage(List<Task> tasks, int page, int size) {
        PageRequest request = PageRequest.of(page -1, size);
        int start = (int) request.getOffset();
        int end = Math.min((start + request.getPageSize()), tasks.size());
        return new PageImpl<Task>(tasks.subList(start, end), request, tasks.size());
    }
}
