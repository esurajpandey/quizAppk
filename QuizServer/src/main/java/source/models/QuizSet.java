package source.models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "quiz_set")
public class QuizSet {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@NotBlank(message = "Level is required")
	@Enumerated(EnumType.STRING)
	private DifficultyLevel level;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "created_at",nullable = false,updatable = false)
	private Date createdAt;
	

	//storing in second
	@Size(min = 60, message = "Set duration must be more than 1 min")
	@Column(name = "duration")
	private Integer duration;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(
		name = "language_id",
		referencedColumnName = "id"
	)
	private Language language;

	@ManyToMany(
		fetch = FetchType.LAZY,
		mappedBy = "quizSets"
	)
	private Set<Category> categories = new HashSet<>();
	
	
	@OneToMany(
		cascade = CascadeType.ALL,
		fetch = FetchType.LAZY,
		mappedBy = "quiz"
	)
	private List<Attempt> attempts;
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public DifficultyLevel getLevel() {
		return level;
	}


	public void setLevel(DifficultyLevel level) {
		this.level = level;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Integer getDuration() {
		return duration;
	}


	public void setDuration(Integer duration) {
		this.duration = duration;
	}


	public Language getLanguage() {
		return language;
	}


	public void setLanguage(Language language) {
		this.language = language;
	}


	public Set<Category> getCategories() {
		return categories;
	}


	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}


	public List<Attempt> getAttempts() {
		return attempts;
	}


	public void setAttempts(List<Attempt> attempts) {
		this.attempts = attempts;
	}
	
	
	
	
}
