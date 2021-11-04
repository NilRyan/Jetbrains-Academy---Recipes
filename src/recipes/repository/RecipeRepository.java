package recipes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.model.RecipeEntity;

@Repository
public interface RecipeRepository extends CrudRepository<RecipeEntity, Long> { }
