package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.dto.RecipeFormDto;
import recipes.model.RecipeEntity;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
                recipeEntity.getDirections(),
                recipeEntity.getCategory(),
                recipeEntity.getDate()
        );
        return recipe;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRecipe(@PathVariable long id) {
        recipeService.removeRecipe(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRecipe(@PathVariable long id, @Valid @RequestBody RecipeFormDto recipeFormDto) {
        recipeService.updateRecipe(id, recipeFormDto);
    }

    @GetMapping("/search")
    public List<RecipeEntity> searchRecipe(@RequestParam Optional<String> category, @RequestParam Optional<String> name) {
        if (category.isPresent() && name.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (category.isEmpty() && name.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);


        List<RecipeEntity> recipeEntityList = null;
        if (category.isPresent()) {
            recipeEntityList = recipeService.getRecipeByCategory(category.get());
        }

        if (name.isPresent()) {
            recipeEntityList = recipeService.getRecipeByName(name.get());
            
        }
        return recipeEntityList;

    } }