package com.WEAK.telekom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WEAK.telekom.models.Etrap;

@Repository

/*
 * 📌 Комментарий для понимания:
 *
 * JpaRepository — это мощный интерфейс из Spring, который позволяет не писать SQL вручную.
 * Всё, что тебе нужно — это просто создать интерфейс и указать, с какой сущностью он работает.
 * Spring автоматически сгенерирует реализацию в момент запуска программы.
 *
 * 🧠 Это экономит массу времени при работе с базой данных и упрощает архитектуру проекта.
 */
public interface EtrapRepository extends JpaRepository<Etrap, Long> {

    /*
     * 🧠 Простыми словами:
     * "Создаю публичный интерфейс EtrapRepository, который наследует возможности JpaRepository
     * для работы с сущностью Etrap, у которой id типа Long."
     *
     * ✅ Благодаря этому ты получаешь бесплатно (без написания SQL):
     * - findAll()         — получить все этрапы
     * - findById(1L)      — найти этрап по ID
     * - save(etrap)       — сохранить или обновить этрап
     * - deleteById(1L)    — удалить этрап по ID
     * - и десятки других удобных методов от Spring Data JPA
     */
}
