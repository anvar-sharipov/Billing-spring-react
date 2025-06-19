// wywod w side bar imena wseh tablis
//Разберём подробно, что происходит в этом Java-контроллере ModelController, строка за строкой:
//
//📦 Пакет и импорты:
//java
//Копировать
//Редактировать
//package com.WEAK.telekom.controllers;
//Указывает, что класс находится в пакете com.WEAK.telekom.controllers.
//
//java
//Копировать
//Редактировать
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import javax.sql.DataSource;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//Импортируются аннотации Spring (например, @RestController, @GetMapping) и классы для работы с базой данных (DataSource, Connection, DatabaseMetaData, ResultSet и т.д.).
//
//🔧 Класс и поля:
//java
//Копировать
//Редактировать
//@RestController
//public class ModelController {
//Аннотация @RestController указывает, что этот класс — REST API контроллер. Он возвращает JSON-ответы.
//
//java
//Копировать
//Редактировать
//    @Autowired
//    private DataSource dataSource;
//@Autowired автоматически внедряет объект DataSource, чтобы мы могли подключаться к базе данных.
//
//DataSource — это пул соединений (connection pool), из которого берётся подключение к БД.
//
//🚀 Метод getTables:
//java
//Копировать
//Редактировать
//    @GetMapping("/api/models")
//    @CrossOrigin(origins="*")
//    public List<String> getTables() {
//Обрабатывает HTTP GET-запросы по адресу /api/models.
//
//@CrossOrigin(origins="*") — разрешает кросс-доменные запросы (CORS), чтобы фронтенд мог обращаться к бэкенду.
//
//Метод возвращает список имён всех таблиц в базе данных (тип List<String>).
//
//🔄 Основная логика:
//java
//Копировать
//Редактировать
//        List<String> tables = new ArrayList<>();
//        try (Connection connection = dataSource.getConnection()) {
//Создаётся пустой список tables, куда мы будем добавлять названия таблиц.
//
//Берём соединение с БД из DataSource.
//
//📊 Метаданные БД:
//java
//Копировать
//Редактировать
//            DatabaseMetaData metaData = connection.getMetaData();
//            String[] types = {"TABLE"};
//Из соединения получаем метаданные БД (metaData), которые содержат информацию о таблицах, колонках, индексах и т.д.
//
//Типы объектов, которые нас интересуют — только таблицы (а не представления, индексы и т.д.).
//
//📋 Получение списка таблиц:
//java
//Копировать
//Редактировать
//            ResultSet rs = metaData.getTables(connection.getCatalog(), "public", "%", types);
//getTables(...) возвращает все таблицы в схеме public (актуально для PostgreSQL).
//
//connection.getCatalog() — имя базы (обычно можно оставить null).
//
//"%" — шаблон, означает "все таблицы".
//
//types — только TABLE.
//
//📥 Добавляем имена таблиц в список:
//java
//Копировать
//Редактировать
//            while (rs.next()) {
//                String tableName = rs.getString("TABLE_NAME");
//                tables.add(tableName);
//            }
//            rs.close();
//Проходим по результатам и извлекаем имя каждой таблицы, добавляем в список tables.
//
//⚠️ Обработка ошибок:
//java
//Копировать
//Редактировать
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//Если возникает исключение (например, проблемы с подключением к БД), оно печатается в консоль.
//
//📤 Возврат результата:
//java
//Копировать
//Редактировать
//        return tables;
//    }
//}
//Возвращается список имён таблиц. На фронтенде ты его используешь, чтобы отобразить ссылки на /admin/models/{название_таблицы}.
//
//🔚 Пример результата запроса:
//Если в базе есть таблицы etrap, abonent_service, то GET-запрос на /api/models вернёт:
//
//json
//Копировать
//Редактировать
//["etrap", "abonent_service"]
//Хочешь — могу объяснить, как адаптировать под другую СУБД (например, MySQL), или как исключать системные таблицы.

package com.WEAK.telekom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ModelController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/api/models")
    @CrossOrigin(origins="*")
    public List<String> getTables() {
        List<String> tables = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();

            // Получаем все таблицы, тип — "TABLE"
            String[] types = {"TABLE"};

            // catalog и schemaPattern можешь заменить под свою СУБД или оставить null
            ResultSet rs = metaData.getTables(connection.getCatalog(), "public", "%", types);

            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                tables.add(tableName);
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tables;
    }
}
