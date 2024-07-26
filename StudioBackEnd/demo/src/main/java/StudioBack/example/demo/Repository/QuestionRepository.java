package StudioBack.example.demo.Repository;

import StudioBack.example.demo.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategory(String category);
    List<Question> findByTextContainingIgnoreCase(String keyword);
    List<Question> findByCategoryAndTextContainingIgnoreCase(String category, String keyword);
}
