package com.example.mealprep.controller;

import com.example.mealprep.dto.ApiResponse;
import com.example.mealprep.dto.IngredientCreationResponse;
import com.example.mealprep.dto.IngredientCreationResult;
import com.example.mealprep.entity.Ingredient;
import com.example.mealprep.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientService service;

    public IngredientController(IngredientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<IngredientCreationResponse>> createIngredients(
            @RequestBody List<Ingredient> ingredients) {

        if (ingredients == null || ingredients.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("No data provided",
                            List.of("Ingredient list is null or empty")));
        }
        try {
            IngredientCreationResult result = service.createIngredients(ingredients);

            boolean isConflict = result.getCreated().isEmpty();

            IngredientCreationResponse response = new IngredientCreationResponse(
                    result.getCreated(),
                    result.getExisting());
            return ResponseEntity
                    .status(isConflict ? HttpStatus.CONFLICT : HttpStatus.CREATED)
                    .body(ApiResponse.success(isConflict
                            ? "No ingredient created: all provided ingredients already exist."
                            : "Ingredient creation result.",
                            response));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Internal Server Error", List.of(e.getMessage())));
        }
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getAll() {
        return ResponseEntity.ok(service.getAllIngredients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Ingredient>> delete(@PathVariable Long id) {
        Ingredient deleted = service.deleteIngredient(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Ingredient with id '" + id + "' has been deleted.",
                        deleted));
    }
}
