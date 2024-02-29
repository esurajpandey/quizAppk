package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import source.models.Question;

public interface QuestionRepository extends JpaRepository<Question, String> {

}
