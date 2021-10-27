package recipes.controller;

import org.springframework.web.bind.annotation.*;
import recipes.dto.RecipeFormDto;
import recipes.model.RecipeEntity;

@RestController
@RequestMapping(path = "/api/recipe")
public class RecipeController {

    RecipeEntity recipeEntity = new RecipeEntity();

    @PostMapping
    public RecipeEntity addRecipe(@RequestBody RecipeFormDto recipeFormDto) {
        recipeEntity = new RecipeEntity(
                recipeFormDto.getName(),
                recipeFormDto.getDescription(),
                recipeFormDto.getIngredients(),
                recipeFormDto.getDirections());
        return recipeEntity;
    }

    @GetMapping
    public RecipeEntity getRecipe() {
        return recipeEntity;
    }
}
