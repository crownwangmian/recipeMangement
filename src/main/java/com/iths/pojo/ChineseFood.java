package com.iths.pojo;

import java.util.List;

public class ChineseFood extends Food {
    private String spiceLevel;

    public ChineseFood(String title, List<Ingredients> ingredients, String instruction, String spiceLevel, FoodCategory category) {
        super(title, ingredients, instruction, category);  // 传递 FoodCategory（早餐、午餐或晚餐）
        this.spiceLevel = spiceLevel;
    }

    public String getSpiceLevel() {
        return spiceLevel;
    }

    public void setSpiceLevel(String spiceLevel) {
        this.spiceLevel = spiceLevel;
    }

    @Override
    public void showRecipe() {
        System.out.println("Chinese Food Recept:");
        System.out.println("title: " + getTitle());
        System.out.println("Category: " + getCategory());  // 打印类别（早餐、午餐或晚餐）
        System.out.println("Ingredients: " + getIngredients());
        System.out.println("Instruction: " + getInstruction());
        System.out.println("Spice Level: " + spiceLevel);
    }

}
