package com.example.mealprep.service;

import com.example.mealprep.entity.Ingredient;
import com.example.mealprep.repository.IngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository repository;

    public IngredientService(IngredientRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Ingredient createIngredient(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    public List<Ingredient> getAllIngredients() {
        return repository.findAll();
    }

    public Ingredient getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteIngredient(Long id) {
        repository.deleteById(id);
    }
}
