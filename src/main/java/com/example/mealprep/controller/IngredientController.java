package com.example.mealprep.controller;

import com.example.mealprep.dto.ApiResponse;
import com.example.mealprep.dto.IngredientCreationResponse;
import com.example.mealprep.dto.IngredientCreationResult;
import com.example.mealprep.dto.IngredientResponseDTO;
import com.example.mealprep.entity.Ingredient;
import com.example.mealprep.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    private final IngredientService service;
    private static final Logger logger = LoggerFactory.getLogger(IngredientController.class);

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
    public ResponseEntity<List<IngredientResponseDTO>> getAll() {
        List<Ingredient> ingredients = service.getAllIngredients();

        List<IngredientResponseDTO> dtos = ingredients.stream()
                .map(ingredient -> new IngredientResponseDTO(ingredient.getId(),
                        ingredient.getName(), ingredient.getUnit(), ingredient.getProteins(),
                        ingredient.getFat(), ingredient.getCarbs(), ingredient.getCalories()))
                .toList();

                logger.info("\n\n //////////// response: {} ////////////", dtos);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientResponseDTO> getById(@PathVariable Long id) {
        logger.info("\n\n //////////// Valeur de maVariable: {} ////////////", id);
        Ingredient ingredient = service.getById(id);
        IngredientResponseDTO dto = new IngredientResponseDTO(ingredient.getId(),
                ingredient.getName(), ingredient.getUnit(), ingredient.getProteins(),
                ingredient.getFat(), ingredient.getCarbs(), ingredient.getCalories());

        ResponseEntity<IngredientResponseDTO> response = ResponseEntity.ok(dto);

        logger.info("\n\n //////////// response: {} ////////////", response.getBody());
        return response;
        // return ResponseEntity.ok(service.getById(id));
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
