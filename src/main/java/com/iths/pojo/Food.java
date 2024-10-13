package com.iths.pojo;

import java.util.List;

public abstract class Food {
    private String title;
    private List<Ingredients> ingredients;
    private String instruction;
    private FoodCategory category;

    public FoodCategory getCategory() {
        return category;
    }

    public void setCategory(FoodCategory category) {
        this.category = category;
    }

    protected Food() {
    }

    protected Food(String title, List<Ingredients> ingredients, String instruction, FoodCategory category) {
        this.title = title;
        this.ingredients = ingredients;
        this.instruction = instruction;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    protected void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstruction() {
        return instruction;
    }

    protected void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public abstract void showRecipe();

}
