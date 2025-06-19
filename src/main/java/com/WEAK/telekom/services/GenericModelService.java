//Отлично! Давай разберём подробно весь класс GenericModelService, строчка за строчкой — как он работает и для чего нужен.
//
//📦 Пакет и импорты
//java
//Копировать
//Редактировать
//package com.WEAK.telekom.services;
//Файл находится в папке services, то есть относится к бизнес-логике.
//
//java
//Копировать
//Редактировать
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.Query;
//JPA-инструменты:
//
//EntityManager — объект для работы с БД на низком уровне (SQL-запросы, нативные запросы и т.д.).
//
//Query — представляет запрос.
//
//java
//Копировать
//Редактировать
//import jakarta.transaction.Transactional;
//Обеспечивает работу транзакций (например, при добавлении записи).
//
//java
//Копировать
//Редактировать
//import lombok.RequiredArgsConstructor;
//Автоматически создаёт конструктор с параметром EntityManager.
//
//java
//Копировать
//Редактировать
//import org.springframework.stereotype.Service;
//Делает этот класс Spring-сервисом, который можно использовать в других компонентах с помощью @Autowired или через конструктор.
//
//java
//Копировать
//Редактировать
//import java.util.List;
//import java.util.Map;
//Структуры данных:
//
//List — список записей.
//
//Map — одна запись в виде пары "ключ-значение".
//
//📘 Класс и поле
//java
//Копировать
//Редактировать
//@Service
//@RequiredArgsConstructor
//public class GenericModelService {
//Объявление класса:
//
//@Service — Spring будет управлять этим классом (инъекции зависимостей, автосоздание и т.д.).
//
//@RequiredArgsConstructor — автоматически создаёт конструктор с EntityManager.
//
//java
//Копировать
//Редактировать
//private final EntityManager entityManager;
//Используется для выполнения SQL-запросов вручную.
//
//📥 Метод findAll(String modelName)
//java
//Копировать
//Редактировать
//public List<Map<String, Object>> findAll(String modelName) {
//    String tableName = convertToTableName(modelName);
//    Query query = entityManager.createNativeQuery("SELECT * FROM " + tableName, Map.class);
//    return query.getResultList();
//}
//🔍 Подробный разбор:
//
//modelName — например: "etrap" или "abonentservice".
//
//convertToTableName(modelName) — превращает "abonentservice" в "abonent_service" (см. ниже).
//
//entityManager.createNativeQuery(...) — выполняет сырой SQL-запрос (в этом случае: SELECT * FROM etrap).
//
//Map.class — указывает, что результат будет в виде Map<String, Object> (то есть JSON-подобная структура).
//
//getResultList() — возвращает List<Map<...>> — список всех строк таблицы в виде ключ-значение.
//
//🟩 Пример вывода:
//
//json
//Копировать
//Редактировать
//[
//  { "id": 1, "etrap": "Ashgabat" },
//  { "id": 2, "etrap": "Mary" }
//]
//📤 Метод save(...)
//java
//Копировать
//Редактировать
//@Transactional
//public void save(String modelName, Map<String, Object> data) {
//    // Простейшая вставка — нужно доработать по безопасности
//    // Также можно использовать JPA Criteria API
//    // или Spring Data JPA + Reflection
//}
//Этот метод пока пустой, но уже помечен как @Transactional, значит:
//
//Всё, что будет внутри, выполнится как одна транзакция.
//
//Если произойдёт ошибка — изменения в БД будут отменены.
//
//🔒 В будущем:
//
//Будет реализована вставка INSERT INTO ..., динамически по переданному modelName и data.
//
//Нужно будет быть осторожным — нельзя доверять клиенту, иначе возможен SQL-инъекционный взлом.
//
//Лучшее решение — использовать JPA + Reflection или безопасные prepared-запросы.
//
//🔧 Метод convertToTableName(...)
//java
//Копировать
//Редактировать
//private String convertToTableName(String modelName) {
//    // Преобразуем modelName вроде "abonentservice" в "abonent_service"
//    return modelName.replaceAll("([A-Z])", "_$1").toLowerCase();
//}
//📌 Что делает:
//
//Принимает имя модели (AbonentService) в виде строки "abonentservice".
//
//Добавляет подчёркивание перед заглавными буквами.
//
//Переводит всё в нижний регистр.
//
//🔹 Примеры:
//
//"Etrap" → "etrap"
//
//"AbonentService" → "abonent_service"
//
//"MyModelName" → "my_model_name"
//
//Это нужно, чтобы сопоставить имя модели и имя таблицы, если в базе используется стиль snake_case.
//
//📌 Вывод
//Метод	Назначение
//findAll()	Получить все записи из указанной таблицы
//save()	Добавить новую запись (ещё не реализовано)
//convertToTableName()	Преобразовать имя модели в имя таблицы (в стиле базы данных)
//
//💡 Что улучшить в будущем
//Безопасность: проверка modelName, whitelist моделей.
//
//Реализация save(): использовать PreparedStatement или Spring Data.
//
//Редактирование и удаление: добавить PUT и DELETE методы.
//
//Валидация данных: убедиться, что JSON соответствует структуре модели.
//
//Хочешь — могу помочь тебе реализовать метод save(...) или добавить редактирование/удаление.

package com.WEAK.telekom.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GenericModelService {

    private final EntityManager entityManager;

    public List<Map<String, Object>> findAll(String modelName) {
        String tableName = convertToTableName(modelName);
        Query query = entityManager.createNativeQuery("SELECT * FROM " + tableName, Map.class);
        return query.getResultList();
    }

    @Transactional
    public void save(String modelName, Map<String, Object> data) {
        // Простейшая вставка — нужно доработать по безопасности
        // Также можно использовать JPA Criteria API
        // или Spring Data JPA + Reflection
    }

    private String convertToTableName(String modelName) {
        // Преобразуем modelName вроде "abonentservice" в "abonent_service"
        return modelName.replaceAll("([A-Z])", "_$1").toLowerCase();
    }
}
