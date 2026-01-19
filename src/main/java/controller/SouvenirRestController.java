package controller;

import Service.SouvenirsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.SouvenirFromDTO;
import lombok.RequiredArgsConstructor;
import model.Souvenir;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.impl.JsonSchemaValidator;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("rest/souvenir")
@RequiredArgsConstructor
public class SouvenirRestController {

    private final SouvenirsService souvenirsService;
    private final JsonSchemaValidator jsonSchemaValidator;
    private final ObjectMapper mapper;

    // READ all
    @GetMapping(produces = {"application/json"})
    public List<Souvenir> getAllBooks(@RequestParam(required = false) Integer id) {
        if(id !=null) {
            return Collections.singletonList(souvenirsService.getSouvenirs().get(id));
        }
        return souvenirsService.getSouvenirs();
    }

    // READ by ID
    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<Souvenir> getBookById(@PathVariable Integer id) {
        Souvenir byId = souvenirsService.getSouvenirs().get(id);
        return byId != null ? ResponseEntity.ok(byId) :
               ResponseEntity.notFound().build();
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Souvenir> createSouvenir(@RequestBody Souvenir souvenir) {
        souvenirsService.addSouvenir(souvenir);
        return ResponseEntity.status(HttpStatus.CREATED).body(souvenir);
    }

    // CREATE WITH VALIDATION
    @PostMapping("/validation")
    public ResponseEntity<String> createSouvenirWithValidation(@RequestBody String rawJson) throws JsonProcessingException {
        // 1. Валидация по JSON Schema
        try {
            jsonSchemaValidator.validate(rawJson);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        // 2. Десериализация в DTO
        SouvenirFromDTO souvenirFromDTO;
        try {
            souvenirFromDTO = mapper.readValue(rawJson, SouvenirFromDTO.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Не удалось преобразовать JSON в SouvenirDTO", e);
        }

        System.err.println(souvenirFromDTO);
        // 3. Логика сохранения...
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.writeValueAsString(souvenirFromDTO));
    }

    // UPDATE //
    //    @PutMapping("/{id}")
    //    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
    //        Book updatedBook = bookService.updateBook(id, bookDetails);
    //        return ResponseEntity.ok(updatedBook);
    //    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSouvenir(@PathVariable Integer id) {
        souvenirsService.getSouvenirs().remove(id);
        return ResponseEntity.noContent().build();
    }

}