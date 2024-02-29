package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import source.models.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

}
