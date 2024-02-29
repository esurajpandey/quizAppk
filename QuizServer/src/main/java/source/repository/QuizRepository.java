package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import source.models.QuizSet;

public interface QuizRepository extends JpaRepository<QuizSet, String> {

}
