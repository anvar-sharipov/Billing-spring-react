package com.WEAK.telekom.dto;

// Класс-обёртка для стандартного ответа API.
// Позволяет возвращать успех/ошибку, сообщение и данные.
public class ApiResponse {

    // ====================
    // === Поля класса ===
    // ====================

    private boolean success; // Указывает, успешно ли выполнен запрос
    private String message;  // Сообщение об успехе или ошибке
    private Object data;     // Дополнительные данные (может быть чем угодно)

    // ==============================
    // === Конструкторы класса ===
    // ==============================

    // Пустой (дефолтный) конструктор.
    // Он создаёт объект ApiResponse без начальной установки значений.
    // После создания можно вручную задать значения:
    // response.setSuccess(true);
    // response.setMessage("Успешно");
    // response.setData(...);
    public ApiResponse() {
    }

    // Конструктор с двумя параметрами — success и message
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Конструктор с тремя параметрами — success, message и data
    public ApiResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // ===========================================================
    // === Перегрузка конструкторов (constructor overloading) ===
    // ===========================================================
    //
    // В Python можно было бы сделать так:
    // def __init__(self, success, message, data=None)
    //
    // В Java нет параметров со значениями по умолчанию,
    // поэтому используется перегрузка — создаётся несколько
    // конструкторов с разными наборами параметров.

    // ============================
    // === Геттеры и сеттеры ===
    // ============================

    // Геттер — метод для чтения значения success
    public boolean isSuccess() {
        return success;
    }

    // Сеттер — метод для установки значения success
    public void setSuccess(boolean success) {
        this.success = success;
    }

    // Геттер для поля message
    public String getMessage() {
        return message;
    }

    // Сеттер для поля message
    public void setMessage(String message) {
        this.message = message;
    }

    // Геттер для поля data
    public Object getData() {
        return data;
    }

    // Сеттер для поля data
    public void setData(Object data) {
        this.data = data;
    }

    // =============================================================
    // === Почему использовать геттеры и сеттеры, а не public поля ===
    // =============================================================
    //
    // Прямой доступ к полям (если бы они были public) делает код
    // уязвимым: данные могут быть случайно или неправильно изменены.
    //
    // Через геттеры/сеттеры можно:
    //  - добавить проверку входных данных
    //  - скрыть реализацию (инкапсуляция)
    //  - легко изменить внутреннюю реализацию без изменения внешнего кода
    //
    // Это делает код надёжнее, масштабируемее и читаемее.

}
