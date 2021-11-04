package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.dto.RecipeFormDto;
import recipes.model.RecipeEntity;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(path = "/api/recipe")
public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping(path = "/new", produces = "application/json")
    public String addRecipe(@Valid @RequestBody RecipeFormDto recipeFormDto) {
        RecipeEntity newRecipe = recipeService.addRecipe(recipeFormDto);
        return "{ \"id\": " + newRecipe.getId() + " }";
    }

    @GetMapping("/{id}")
    public RecipeFormDto getRecipe(@PathVariable long id) {
        RecipeEntity recipeEntity = recipeService.getRecipe(id);
        RecipeFormDto recipe = new RecipeFormDto(
                recipeEntity.getName(),
                recipeEntity.getDescription(),
                recipeEntity.getIngredients(),
                recipeEntity.getDirections()
        );
        return recipe;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRecipe(@PathVariable long id) {
        recipeService.removeRecipe(id);
    }
}
