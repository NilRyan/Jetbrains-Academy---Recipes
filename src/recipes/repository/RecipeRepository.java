package recipes.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.model.RecipeEntity;

import java.util.List;


@Repository
public interface RecipeRepository extends CrudRepository<RecipeEntity, Long> {
    List<RecipeEntity> findByNameContainsIgnoreCaseOrderByDateDesc(String name);
    List<RecipeEntity> findByCategoryIsAllIgnoreCaseOrderByDateDesc(String category);
}
