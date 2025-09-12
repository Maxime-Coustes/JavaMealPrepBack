package com.example.mealprep.dto;

import com.example.mealprep.entity.Ingredient;

import java.util.List;

public class IngredientCreationResult {

    private final List<Ingredient> created;
    private final List<Ingredient> existing;

    public IngredientCreationResult(List<Ingredient> created, List<Ingredient> existing) {
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
