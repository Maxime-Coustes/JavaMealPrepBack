package com.example.mealprep.service;

import com.example.mealprep.dto.IngredientCreationResult;
import com.example.mealprep.entity.Ingredient;
import com.example.mealprep.repository.IngredientRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository repository;

    public IngredientService(IngredientRepository repository) {
        this.repository = repository; 
    }

    @SuppressWarnings("null")
    @Transactional
    public Ingredient createIngredient(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    @Transactional
    public IngredientCreationResult createIngredients(List<Ingredient> ingredients) {
        List<Ingredient> created = new ArrayList<>();
        List<Ingredient> existing = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            repository.findByName(ingredient.getName())
                    .ifPresentOrElse(
                            existing::add,
                            () -> created.add(repository.save(ingredient)));
        }

        return new IngredientCreationResult(created, existing);
    }

    public List<Ingredient> getAllIngredients() {
        return repository.findAll();
    }

    @SuppressWarnings("null")
    public Ingredient getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient with id '" + id + "' doesn't exist"));
    }

    @SuppressWarnings("null")
    @Transactional
    public void deleteIngredient(Long id) {
        repository.deleteById(id);
    }
}
