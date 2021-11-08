package recipes.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)

public class RecipeFormDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    @NotEmpty
    private String[] ingredients;
    @NotEmpty
    private String[] directions;

    @NotBlank
    private String category;

    private LocalDateTime date;

    public RecipeFormDto(String name, String description, @NotEmpty String[] ingredients, @NotEmpty String[] directions, String category, LocalDateTime date) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
        this.category = category;
        this.date = date;
    }

    public RecipeFormDto(String name, String description, @NotEmpty String[] ingredients, @NotEmpty String[] directions, String category) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
        this.category = category;
    }

    public RecipeFormDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getDirections() {
        return directions;
    }

    public void setDirections(String[] directions) {
        this.directions = directions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
