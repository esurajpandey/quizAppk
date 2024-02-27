package source.models;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "questions")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Lob
	@NotBlank(message = "Question is required")
	@Column(nullable = false,name = "question",length = 1000)
	private String question;
	
	@Size(max = 6,min = 4,message = "Min 4 and max 6 options are required")
	@Column(columnDefinition = "json")
	private String[] options;
	
	@NotEmpty(message = "Answer is requireed")
	@Column(name = "answer")
	private String answer;
	
	@AssertTrue(message = "Answer must be one of the options")
    private boolean isValidAnswer() {
        return Arrays.asList(options).contains(answer);
    }
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(
		name = "language_id",
		referencedColumnName = "id"
	)
	private Language language;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
	
	
	
}
