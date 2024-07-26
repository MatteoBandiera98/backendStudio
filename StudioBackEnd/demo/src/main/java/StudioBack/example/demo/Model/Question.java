package StudioBack.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String text;  // Questa è la proprietà su cui vogliamo fare la ricerca
    private String answer;

    // Costruttore personalizzato
    public Question(String category, String text, String answer) {
        this.category = category;
        this.text = text;
        this.answer = answer;
    }
}
