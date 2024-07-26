package StudioBack.example.demo.Controller;

import StudioBack.example.demo.Model.Question;
import StudioBack.example.demo.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    // Endpoint per ottenere tutte le categorie
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = questionService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // Endpoint per creare una nuova domanda
    @PostMapping("/create")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        try {
            Question createdQuestion = questionService.createQuestion(question);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint per cercare le domande
    @GetMapping("/search")
    public ResponseEntity<List<Question>> searchQuestions(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword) {
        List<Question> questions = questionService.searchQuestions(category, keyword);
        return ResponseEntity.ok(questions);
    }

    // Endpoint per aggiungere una nuova categoria
    @PostMapping("/categories")
    public ResponseEntity<Void> addCategory(@RequestBody Map<String, String> request) {
        String category = request.get("category");
        if (category != null && !category.trim().isEmpty()) {
            boolean added = questionService.addCategory(category);
            if (added) {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Endpoint per rimuovere una categoria
    @DeleteMapping("/categories")
    public ResponseEntity<Void> removeCategory(@RequestParam String category) {
        boolean removed = questionService.removeCategory(category);
        if (removed) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
