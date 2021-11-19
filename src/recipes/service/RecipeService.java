package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.dto.RecipeFormDto;
import recipes.model.RecipeEntity;
import recipes.repository.RecipeRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
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
                recipeFormDto.getDirections(),
                recipeFormDto.getCategory(),
                LocalDateTime.now()
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

    public void removeRecipe(long id) {
        if (!recipeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        recipeRepository.deleteById(id);
    }

    public void updateRecipe(long id, RecipeFormDto recipeFormDto) {
        RecipeEntity recipe = getRecipe(id);
        recipe.setDate(LocalDateTime.now());
        recipe.setName(recipeFormDto.getName());
        recipe.setCategory(recipeFormDto.getCategory());
        recipe.setDescription(recipeFormDto.getDescription());
        recipe.setIngredients(recipeFormDto.getIngredients());
        recipe.setDirections(recipeFormDto.getDirections());

        recipeRepository.save(recipe);
    }

    public List<RecipeEntity> getRecipeByCategory(String category) {
        return recipeRepository.findByCategoryIsAllIgnoreCaseOrderByDateDesc(category);
    }

    public List<RecipeEntity> getRecipeByName(String name) {
        return recipeRepository.findByNameContainsIgnoreCaseOrderByDateDesc(name);
    }

}
