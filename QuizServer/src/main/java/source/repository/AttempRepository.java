package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import source.models.Attempt;

public interface AttempRepository extends JpaRepository<Attempt	, String> {

}
