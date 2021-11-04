package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.dto.RecipeFormDto;
import recipes.model.RecipeEntity;
import recipes.repository.RecipeRepository;

import java.util.Optional;

@Service
public class RecipeService {

    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public RecipeEntity addRecipe(RecipeFormDto recipeFormDto) {
        RecipeEntity newRecipe = new RecipeEntity(
                recipeFormDto.getName(),
                recipeFormDto.getDescription(),
                recipeFormDto.getIngredients(),
                recipeFormDto.getDirections()
        );
        return recipeRepository.save(newRecipe);
    }

    public RecipeEntity getRecipe(long id) {
        Optional<RecipeEntity> recipe = recipeRepository.findById(id);
        if (recipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return recipe.get();
    }
}
