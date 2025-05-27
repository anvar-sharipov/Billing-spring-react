package com.WEAK.telekom.controllers;

import com.WEAK.telekom.models.Etrap;
import com.WEAK.telekom.repositories.EtrapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etraps")
@CrossOrigin(origins = "*")
public class EtrapController {

    @Autowired
    private EtrapRepository etrapRepository;

    @GetMapping
    public List<Etrap> getAll() {
        return etrapRepository.findAll();
    }

    @PostMapping
    public Etrap create(@RequestBody Etrap etrap) {
        return etrapRepository.save(etrap);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (etrapRepository.existsById(id)) {
            etrapRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
        public ResponseEntity<Etrap> update(@PathVariable Long id, @RequestBody Etrap updatedEtrap) {
        return etrapRepository.findById(id)
                .map(etrap -> {
                    etrap.setEtrap(updatedEtrap.getEtrap());
                    etrapRepository.save(etrap);
                    return ResponseEntity.ok(etrap);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}












//package com.WEAK.telekom.controllers;
//
//// Импорт модели, как в Django: from .models import Etrap
//import com.WEAK.telekom.models.Etrap;
//
//// Импорт репозитория, как в Django: используем менеджер модели для запросов к БД
//import com.WEAK.telekom.repositories.EtrapRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//// @RestController ≈ Django: @api_view + возвращение JsonResponse
//@RestController
//
//// @RequestMapping("/api/etraps") ≈ Django: URL path('api/etraps/', ...)
//@RequestMapping("/api/etraps")
//
//// @CrossOrigin(origins = "*") ≈ Django: настройка CORS (django-cors-headers)
//@CrossOrigin(origins = "*")
//public class EtrapController {
//
//    // @Autowired ≈ Django: etrapRepository = Etrap.objects (инъекция зависимости)
//    @Autowired
//    private EtrapRepository etrapRepository;
//
//    // GET /api/etraps/ ≈ Django: Etrap.objects.all()
//    @GetMapping
//    public List<Etrap> getAll() {
//        return etrapRepository.findAll();
//    }
//
//    // POST /api/etraps/ ≈ Django: Etrap.objects.create()
//    @PostMapping
//    public Etrap create(@RequestBody Etrap etrap) {
//        return etrapRepository.save(etrap);
//    }
//
//    // PUT /api/etraps/{id}/ ≈ Django:
//    // instance = get_object_or_404(Etrap, id=id)
//    // instance.etrap = updated_data['etrap']
//    // instance.save()
//    @PutMapping("/{id}")
//    public ResponseEntity<Etrap> update(@PathVariable Long id, @RequestBody Etrap updatedEtrap) {
//        return etrapRepository.findById(id)
//                .map(etrap -> {
//                    etrap.setEtrap(updatedEtrap.getEtrap());
//                    etrapRepository.save(etrap);
//                    return ResponseEntity.ok(etrap);
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // DELETE /api/etraps/{id}/ ≈ Django:
//    // instance = get_object_or_404(Etrap, id=id)
//    // instance.delete()
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        if (etrapRepository.existsById(id)) {
//            etrapRepository.deleteById(id);
//            return ResponseEntity.noContent().build(); // HTTP 204 No Content
//        } else {
//            return ResponseEntity.notFound().build(); // HTTP 404 Not Found
//        }
//    }
//}

