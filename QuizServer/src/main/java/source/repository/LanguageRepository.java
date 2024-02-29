package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import source.models.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {

}
