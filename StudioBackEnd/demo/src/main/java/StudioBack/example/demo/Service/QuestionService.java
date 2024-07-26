package StudioBack.example.demo.Service;

import StudioBack.example.demo.Model.Question;
import StudioBack.example.demo.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    private List<String> categories = new ArrayList<>(List.of("Math", "Scienze", "storia"));

    // Metodo per ottenere tutte le categorie
    public List<String> getAllCategories() {
        return new ArrayList<>(categories);
    }

    // Metodo per aggiungere una nuova categoria
    public boolean addCategory(String category) {
        if (!categories.contains(category)) {
            categories.add(category);
            return true;
        }
        return false;
    }

    // Metodo per rimuovere una categoria
    public boolean removeCategory(String category) {
        return categories.remove(category);
    }

    // Metodo per creare una nuova domanda
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    // Metodo per cercare le domande
    public List<Question> searchQuestions(String category, String keyword) {
        if (category != null && !category.isEmpty() && keyword != null && !keyword.isEmpty()) {
            return questionRepository.findByCategoryAndTextContainingIgnoreCase(category, keyword);
        } else if (category != null && !category.isEmpty()) {
            return questionRepository.findByCategory(category);
        } else if (keyword != null && !keyword.isEmpty()) {
            return questionRepository.findByTextContainingIgnoreCase(keyword);
        } else {
            return questionRepository.findAll();
        }
    }
}
