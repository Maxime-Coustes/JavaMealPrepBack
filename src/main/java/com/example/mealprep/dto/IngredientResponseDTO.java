package com.example.mealprep.dto;


public class IngredientResponseDTO {

    private Long id;
    private String name;
    private String unit;
    private Float proteins;
    private Float fat;
    private Float carbs;
    private Float calories;

    public IngredientResponseDTO(Long id, String name,String unit, float calories, float proteins, float carbs, float fat){
        this.id =id;
        this.name = name;
        this.unit = unit;
        this.calories = calories;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fat = fat;
    }

    static final String TEXTTEST = "here it is blabla";

    // DTO immuable, pas besoin de setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getUnit() { return unit; }
    public Float getProteins() { return proteins; }
    public Float getFat() { return fat; }
    public Float getCarbs() { return carbs; }
    public Float getCalories() { return calories; }
    public String getTxt() { return IngredientResponseDTO.TEXTTEST ;}

    @Override
    public String toString(){
        return "IngredientResponseDTO{ id= " + id + ", name =  " + name + ", unit = " + unit + ", prot = " + proteins + ", fat = " + fat + ", carbs = " + carbs + ", calories = " + calories + ", textbonus = " + IngredientResponseDTO.TEXTTEST ;
    }

}
