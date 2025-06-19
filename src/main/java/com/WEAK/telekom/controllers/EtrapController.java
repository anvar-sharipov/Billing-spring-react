//Что такое контроллер в Spring Boot?
//В Spring Boot @RestController — это аналог views в Django. Он обрабатывает HTTP-запросы и возвращает ответы.

package com.WEAK.telekom.controllers;
import com.WEAK.telekom.dto.ApiResponse;
import com.WEAK.telekom.models.Etrap;
import com.WEAK.telekom.repositories.EtrapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



//@Autowired говорит Spring:
//"Эй, Spring, найди нужный объект (EtrapRepository) и передай его сюда автоматически."
//Это называется инъекция зависимостей (Dependency Injection).
//🧠 Параллель с Django:
//В Django ты явно создаёшь объект:
//etraps = Etrap.objects.all()
//А в Spring ты говоришь:
//@Autowired
//private EtrapRepository etrapRepository;
//И Spring сам:
//  находит класс EtrapRepository
//  создаёт его объект
//  передаёт его контроллеру

//Что такое ResponseEntity в Spring?
//ResponseEntity — это класс-обёртка, который позволяет контролировать весь HTTP-ответ от сервера к клиенту, включая:
//Тело ответа (например, данные в JSON или текст)
//HTTP статус (например, 200 OK, 404 Not Found, 201 Created и т.д.)
//HTTP заголовки (например, Content-Type, Authorization и т.д.)
//Пример:
//return ResponseEntity.status(201).body(new ApiResponse(true, "Запись создана"));
//Здесь мы возвращаем клиенту:
//HTTP статус 201 Created
//Тело ответа — объект ApiResponse
//Аналогия в Django
//В Django при написании API обычно используют HttpResponse или, если используешь Django REST Framework (DRF), то возвращают объекты Response.
//Например, в DRF:
//from rest_framework.response import Response
//from rest_framework import status
//def create(request):
//    # ...
//    return Response({"success": True, "message": "Запись создана"}, status=status.HTTP_201_CREATED)
//Здесь Response из DRF — аналог ResponseEntity в Spring.
//Ты тоже можешь указать тело и статус.
//Кратко:
//Spring ResponseEntity                                 Django REST Framework Response
//Обёртка HTTP-ответа с телом, статусом и заголовками	Обёртка HTTP-ответа с телом и статусом
//Позволяет явно управлять статусом и заголовками	    То же самое
//Используется в контроллерах для удобства	            Используется в DRF view-функциях

// Annotation:
//Отличный вопрос! Давай разберём, что такое аннотация в Java (и Spring), и как это можно сравнить с Django.
//Что такое аннотация (annotation) в Java / Spring?
//Аннотация — это специальный мета-инструмент, который добавляет дополнительную информацию к коду. По сути, это как "теги" или "маркировки" над классами, методами или полями, которые говорят фреймворку (Spring), как с этим кодом обращаться.
//Аннотации обычно начинаются с @ и используются для:
//Конфигурирования компонентов (например, пометить класс как контроллер)
//Управления поведением (например, какой HTTP метод должен вызывать метод)
//Внедрения зависимостей (автоматически подставлять нужные объекты)
//И многого другого
//Примеры аннотаций из org.springframework.web.bind.annotation.*
//@RestController — говорит Spring, что класс — это контроллер для обработки HTTP запросов, и что методы возвращают данные (обычно JSON).
//@RequestMapping — задаёт базовый URL путь для контроллера или метода.
//@GetMapping, @PostMapping, @PutMapping, @DeleteMapping — обозначают, что метод обрабатывает HTTP GET, POST, PUT, DELETE запросы соответственно.
//@PathVariable — указывает, что параметр метода берётся из части URL (например, из /users/{id}).
//@RequestBody — говорит, что параметр метода должен быть заполнен из тела HTTP запроса (например, JSON).
//Аналогия с Django
//В Django (особенно в классах представлений или в Django REST Framework) похожую роль играют декораторы и некоторые классы:
//Декораторы @api_view(['GET']), @api_view(['POST']) в DRF — аналог @GetMapping, @PostMapping
//Классы-наследники APIView, где методы get(), post() обрабатывают соответствующие HTTP методы — похожи на контроллеры с методами с аннотациями в Spring.
//В Django для URL-путей обычно используется файл urls.py, где прописывается какой view отвечает за какой путь — в Spring это часто задаётся через аннотацию @RequestMapping и похожие.
//Пример в Django:
//from rest_framework.decorators import api_view
//from rest_framework.response import Response
//@api_view(['GET'])
//def get_all_etraps(request):
//    # обработка GET запроса
//    return Response({"message": "Список этрапов"})
//Здесь @api_view(['GET']) — это как @GetMapping в Spring.
//Кратко:
//Spring Аннотации (@)	                        Django (DRF) Декораторы и классы
//@RestController — класс контроллера	        Класс-наследник APIView
//@GetMapping("/path") — метод для GET запроса	@api_view(['GET']) — функция для GET запроса
//@RequestBody — тело запроса в параметр метода	Параметр request.data в Django
//@PathVariable — часть пути URL как параметр	Параметры из URL в Django через urls.py и view
//Почему аннотации важны?
//Они позволяют сделать код:
//Более читаемым (видно сразу, что за метод и как он используется)
//Упрощают конфигурацию и связывание компонентов
//Убирают необходимость писать много шаблонного кода вручную

@RestController
@RequestMapping("/api/models/etrap")
@CrossOrigin(origins="*")
public class EtrapController {

    @Autowired
    private EtrapRepository etrapRepository;

//    List — это список в Java, то есть упорядоченная коллекция элементов.
//    <Etrap> — говорит, что это список именно объектов типа Etrap.
//    Так Java указывает, что внутри списка будут именно объекты класса Etrap.
    @GetMapping
    public List<Etrap> getAll() {
        return etrapRepository.findAll();
    }

//      Что такое ResponseEntity<ApiResponse>?
//      ResponseEntity — это обёртка для HTTP-ответа в Spring.
//      В ней можно указать:
//          HTTP статус (например, 200 OK, 201 Created, 404 Not Found и т.д.)
//          Заголовки ответа (headers)
//          Тело ответа (body)
//      Параметр <ApiResponse> — это тип тела ответа. В данном случае, тело будет объектом класса ApiResponse, который ты написал ранее.
//    Что делает этот метод?
//      1) @PostMapping — говорит, что этот метод обрабатывает HTTP POST-запросы на путь /api/etraps.
//      2) @RequestBody Etrap etrap — Spring автоматически берет JSON из тела запроса и преобразует его в объект Etrap.
//          (Аналог в Django: вьюшка принимает JSON и преобразует в объект модели.)
//      3) etrapRepository.save(etrap) — сохраняет новый объект в базу данных. Возвращает сохранённый объект (с ID и т.д.).
//      4) ResponseEntity.status(201).body(...) — формирует HTTP-ответ:
//          статус 201 — означает, что ресурс успешно создан;
//          тело ответа — объект ApiResponse с полями:
//              success = true
//              message = "Запись успешно создана"
//              data = сохранённый объект saved (чтобы клиент получил данные о созданной записи)
//    Аналог в Django REST Framework
//      from rest_framework.response import Response
//      from rest_framework import status
//
//      @api_view(['POST'])
//      def create_etrap(request):
//          serializer = EtrapSerializer(data=request.data)
//          if serializer.is_valid():
//              saved = serializer.save()
//              return Response({
//                  'success': True,
//                  'message': 'Запись успешно создана',
//                  'data': serializer.data,
//              }, status=status.HTTP_201_CREATED)
//          return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
//    Там тоже создаётся ответ с нужным статусом и телом.
//    В Spring это ResponseEntity<ApiResponse>, в Django — Response({...}, status=...).
//    Кратко:
//      ResponseEntity<ApiResponse> — позволяет тебе вернуть и тело, и HTTP статус, и заголовки, если нужно.
//      В теле ты возвращаешь удобный объект ApiResponse, чтобы клиент знал, что произошло и получил данные.
//      HTTP статус 201 — стандарт для успешного создания ресурса.
    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody Etrap etrap) {
        Etrap saved = etrapRepository.save(etrap);
        return ResponseEntity.status(201).body(new ApiResponse(true, "Запись успешно создана", saved));
    }

//    Что делает ResponseEntity.ok(...)?
//    Это короткая запись для создания HTTP-ответа со статусом 200 OK и телом, которое ты передаёшь внутрь.
//    То есть:
//    ResponseEntity.ok(body)
//    эквивалентно:
//    ResponseEntity.status(200).body(body)

//    a new ApiResponse(true, "Запись успешно удалена")
//     это тело ответа, то есть обычный объект, который ты хочешь вернуть клиенту. А ResponseEntity.ok(...) говорит Spring-у:
//    Верни этот объект, и поставь HTTP статус 200 OK.
//    Аналог в Django REST Framework
//    return Response({
//      'success': True,
//      'message': 'Запись успешно удалена'
//    }, status=200)
//    В Django ты явно указываешь status=200, а в Spring можно использовать .ok(...) как сокращение.
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        if (etrapRepository.existsById(id)) {
            etrapRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse(true, "Запись успешно удалена"));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse(false, "Запись не найдена"));
        }
    }

//    Цель метода:
//    Обновить существующую запись Etrap по её id.
//    🔍 Аннотация @PutMapping("/{id}")
//    Это означает:
//      1) Этот метод вызывается, когда приходит HTTP PUT-запрос по адресу /api/etraps/{id}
//      2) {id} — это переменная из URL. Пример: /api/etraps/5
//    📌 Аналог в Django:
//      @api_view(['PUT'])
//      def update_etrap(request, id):
//    💡 Аргументы метода
//    public ResponseEntity<ApiResponse> update(
//      @PathVariable Long id,
//      @RequestBody Etrap updatedEtrap
//    )
//    🔹 @PathVariable Long id
//    – Значение id берётся из URL, например /api/etraps/5 → id = 5
//    📌 Django аналог:
//    def update_etrap(request, id):
//    🔹 @RequestBody Etrap updatedEtrap
//    – Весь JSON из тела запроса автоматически конвертируется в объект Etrap
//    📌 Django аналог:
//    data = request.data
//    ⚙️ Вся логика обновления
//      return etrapRepository.findById(id).map(etrap -> {
//      1) etrapRepository.findById(id) — найти запись по id
//      2) .map(etrap -> { ... }) — если запись найдена, выполняем лямбду (анонимную функцию)
//      3) etrap — это объект, который нашли в БД
//    📌 Django аналог:
//      try:
//          etrap = Etrap.objects.get(pk=id)
//      except Etrap.DoesNotExist:
//          return Response({"success": False, "message": "Не найдено"}, status=404)
//    🔁 Обновление полей
//    etrap.setEtrap(updatedEtrap.getEtrap());
//    – Обновляем поле etrap новым значением из тела запроса
//    etrapRepository.save(etrap);
//    – Сохраняем обновлённый объект в базу
//    📌 Django аналог:
//      etrap.name = data['etrap']
//      etrap.save()
//    ❌ Обработка ситуации, если запись не найдена
//      .orElseGet(() ->
//          ResponseEntity.status(404).body(new ApiResponse(false, "Запись не найдена"))
//      );
//    – Если findById(id) ничего не нашёл, возвращается ответ с HTTP 404 Not Found и сообщением
//    📌 Django аналог:
//      except Etrap.DoesNotExist:
//          return Response({"success": False, "message": "Запись не найдена"}, status=404)
//    🧠 Кратко:
//      Часть Java Spring                   	Аналог Django REST Framework
//      @PutMapping("/{id}")	                @api_view(['PUT']) + def view(id)
//      @PathVariable Long id	                def view(request, id)
//      @RequestBody Etrap updatedEtrap	        data = request.data
//      findById(id).map(...).orElseGet(...)	try: ... except DoesNotExist
//      ResponseEntity.ok(...)/status(...)	    Response(..., status=...)
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody Etrap updatedEtrap) {
        return etrapRepository.findById(id).map(etrap -> {
            etrap.setEtrap(updatedEtrap.getEtrap());
            etrapRepository.save(etrap);
            return ResponseEntity.ok(new ApiResponse(true, "Запись успешно обновлен", etrap));
        }).orElseGet(() -> ResponseEntity.status(404).body(new ApiResponse(false, "Запись не найдена")));
    }
}


