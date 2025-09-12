package com.example.mealprep.dto;

import com.example.mealprep.entity.Ingredient;
import java.util.List;

public class IngredientCreationResponse {
    private List<Ingredient> created;
    private List<Ingredient> existing;

    public IngredientCreationResponse(List<Ingredient> created, List<Ingredient> existing) {
        this.created = created;
        this.existing = existing;
    }

    public List<Ingredient> getCreated() {
        return created;
    }

    public List<Ingredient> getExisting() {
        return existing;
    }
}
