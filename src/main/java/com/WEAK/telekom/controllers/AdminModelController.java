// eto u nas dlya uniwersalnogo wywoda wseh tablis w adminke cherez 1 file, a ne sozdawat dlya kajdoy tablisy otdelnyy file
//✅ Почему это правильно и профессионально:
//1. Универсальность (Generic)
//Ты создаёшь одну универсальную логику (GenericModelService + AdminModelController), которая работает для любых моделей, вместо дублирования одного и того же кода.
//
//2. Чистая архитектура (Separation of Concerns)
//Контроллер отвечает только за HTTP.
//
//Сервис — за бизнес-логику.
//
//UI (React) — за отображение.
//
//Это называется разделение ответственности, и это основа масштабируемых систем.
//
//3. Гибкость
//Ты можешь добавить новую модель в базу, и она автоматически будет видна в админке, без написания нового кода в React или Java.
//
//4. Экономия времени в будущем
//Когда моделей станет 10 или 100 — ты не будешь писать одно и то же вручную. 1 универсальный компонент покрывает всё.
//
//🧠 Как делают в крупных компаниях
//Примерно так же, только:
//
//Для больших проектов используют JPA Criteria API, GraphQL или Spring Data REST для автогенерации контроллеров.
//
//В админке может быть ещё больше динамики — например, UI сам считывает типы полей, валидацию и отображает правильные формы (аналог Django Admin).
//
//💡 Ты можешь ещё улучшить:
//Что	Как улучшить
//🔐 Безопасность	Добавить проверку, разрешено ли получать доступ к этой модели.
//📄 Метаданные	Сделать эндпоинт /api/models/meta/etrap, который возвращает названия колонок, типы и т.д.
//✍️ Добавление/Редактирование	Реализовать POST, PUT, DELETE в GenericModelService.
//🧪 Тесты	Добавить unit-тесты для GenericModelService.
//
//✅ ИТОГ
//Ты сейчас делаешь архитектурно правильную, масштабируемую и профессиональную админку. Такой подход:
//
//экономит время,
//
//легко расширяется,
//
//соответствует принципам KISS, DRY и SOLID.
//
//Если захочешь — могу помочь превратить это в реально мощную админ-систему уровня Django Admin, но на твоих условиях (React + Spring Boot).


//Хорошо! Разберём подробно, что делает каждая строка в этом контроллере:
//
//📦 Пакет и импорты:
//java
//Копировать
//Редактировать
//package com.WEAK.telekom.controllers;
//Указывает, что этот файл принадлежит к пакету controllers в проекте.
//
//java
//Копировать
//Редактировать
//import com.WEAK.telekom.services.GenericModelService;
//Импорт универсального сервиса, который обрабатывает любые модели (Etrap, AbonentService и т.д.).
//
//java
//Копировать
//Редактировать
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.Query;
//Импорты из JPA:
//
//EntityManager — объект для низкоуровневой работы с базой (не используется прямо в этом коде, но может использоваться в будущем).
//
//Query — для запросов (не используется здесь).
//
//java
//Копировать
//Редактировать
//import jakarta.transaction.Transactional;
//Аннотация, чтобы методы выполнялись в рамках транзакции (нужно при добавлении или изменении данных).
//
//java
//Копировать
//Редактировать
//import lombok.RequiredArgsConstructor;
//Lombok автоматически создаёт конструктор с нужными аргументами (т.е. entityManager и modelService будут внедрены автоматически).
//
//java
//Копировать
//Редактировать
//import org.springframework.web.bind.annotation.*;
//Импорт Spring-аннотаций для REST API:
//
//@RestController, @RequestMapping, @GetMapping, @PostMapping, @PathVariable, @RequestBody.
//
//java
//Копировать
//Редактировать
//import java.util.List;
//import java.util.Map;
//Используем универсальные структуры — список объектов (List) и словарь/объект (Map).
//
//📘 Сам класс:
//java
//Копировать
//Редактировать
//@RestController
//Говорит Spring: это REST-контроллер. Все методы возвращают JSON.
//
//java
//Копировать
//Редактировать
//@RequestMapping("/api/admin/models")
//Все запросы будут начинаться с этого URL.
//Например:
//
//GET /api/admin/models/etrap
//
//POST /api/admin/models/abonentservice
//
//java
//Копировать
//Редактировать
//@RequiredArgsConstructor
//Lombok сам создаёт конструктор с двумя параметрами: EntityManager и GenericModelService.
//
//💡 Поля:
//java
//Копировать
//Редактировать
//private final EntityManager entityManager;
//Позволяет вручную выполнять SQL-запросы или JPQL (но в этом коде пока не используется).
//
//java
//Копировать
//Редактировать
//private final GenericModelService modelService;
//Наш универсальный сервис, который может:
//
//Найти все записи любой таблицы.
//
//Сохранить новую запись.
//
//📥 Метод: Получить все записи
//java
//Копировать
//Редактировать
//@GetMapping("/{modelName}")
//public List<Map<String, Object>> getAll(@PathVariable String modelName) {
//    return modelService.findAll(modelName);
//}
//@GetMapping("/{modelName}") — реагирует на URL, например /api/admin/models/etrap.
//
//@PathVariable — вытаскивает modelName из URL.
//
//modelService.findAll(modelName) — вызывает универсальный метод, который возвращает список всех записей (в виде Map<String, Object> — JSON-подобная структура).
//
//🔹 Пример вывода:
//
//json
//Копировать
//Редактировать
//[
//  { "id": 1, "etrap": "Ashgabat" },
//  { "id": 2, "etrap": "Mary" }
//]
//📤 Метод: Добавить новую запись
//java
//Копировать
//Редактировать
//@PostMapping("/{modelName}")
//@Transactional
//public void add(@PathVariable String modelName, @RequestBody Map<String, Object> data) {
//    modelService.save(modelName, data);
//}
//@PostMapping("/{modelName}") — обрабатывает POST-запросы на тот же URL.
//
//@Transactional — любые изменения в БД выполняются как единая транзакция.
//
//@RequestBody Map<String, Object> data — данные из тела запроса (JSON) превращаются в Map.
//
//modelService.save(...) — сохраняет запись в базу с помощью универсального метода.
//
//🔹 Пример входящего запроса:
//
//json
//Копировать
//Редактировать
//POST /api/admin/models/etrap
//
//{
//  "etrap": "Dashoguz"
//}
//📌 Вывод:
//Элемент	Назначение
//@RestController	Делает класс REST API
//@RequestMapping	Общий префикс маршрута
//@GetMapping("/{modelName}")	Получение всех записей по имени модели
//@PostMapping("/{modelName}")	Добавление записи в любую таблицу
//GenericModelService	Универсальный сервис, не требующий создания отдельных файлов для каждой модели
//
//🔚 Хочешь — могу также подробно объяснить GenericModelService.
//Скорее всего, ты в нём тоже хочешь разобраться — там главная магия. Скажи — продолжить?


package com.WEAK.telekom.controllers;

import com.WEAK.telekom.services.GenericModelService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/models")
@RequiredArgsConstructor
public class AdminModelController {

    private final EntityManager entityManager;
    private final GenericModelService modelService;

    // Получение всех записей из таблицы
    @GetMapping("/{modelName}")
    public List<Map<String, Object>> getAll(@PathVariable String modelName) {
        return modelService.findAll(modelName);
    }

    // Добавление новой записи (будет использоваться в будущем)
    @PostMapping("/{modelName}")
    @Transactional
    public void add(@PathVariable String modelName, @RequestBody Map<String, Object> data) {
        modelService.save(modelName, data);
    }
}
