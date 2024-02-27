package source.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "language",indexes = {
		@Index(columnList = "name",name = "language_name")
})
public class Language {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Language name is required")
	@Column(nullable = false,name = "name",unique = true)
	private String name;
	
	@OneToMany(mappedBy = "language",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Question> questions;
	
	@OneToMany(mappedBy = "language",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<QuizSet> quizSets;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<QuizSet> getQuizSets() {
		return quizSets;
	}

	public void setQuizSets(List<QuizSet> quizSets) {
		this.quizSets = quizSets;
	}
	
	
}
