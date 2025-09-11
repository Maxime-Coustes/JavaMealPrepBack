package com.example.mealprep.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredient", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 10)
    private String unit;

    private Float proteins;
    private Float fat;
    private Float carbs;
    private Float calories;

    //@OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<RecipeIngredient> recipeIngredients = new HashSet<>();

    public Ingredient() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public Float getProteins() { return proteins; }
    public void setProteins(Float proteins) { this.proteins = proteins; }

    public Float getFat() { return fat; }
    public void setFat(Float fat) { this.fat = fat; }

    public Float getCarbs() { return carbs; }
    public void setCarbs(Float carbs) { this.carbs = carbs; }

    public Float getCalories() { return calories; }
    public void setCalories(Float calories) { this.calories = calories; }

    //public Set<RecipeIngredient> getRecipeIngredients() { return recipeIngredients; }

    /*public void addRecipeIngredient(RecipeIngredient ri) {
        recipeIngredients.add(ri);
        ri.setIngredient(this);
    }

    public void removeRecipeIngredient(RecipeIngredient ri) {
        recipeIngredients.remove(ri);
        ri.setIngredient(null);
    }*/
}

