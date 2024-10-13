package com.iths.pojo;

import java.util.List;

public class KoreanFood extends Food {

    private boolean hasKimchi;  // 是否包含泡菜

    public KoreanFood(String title, List<Ingredients> ingredients, String instruction, boolean hasKimchi, FoodCategory category) {
        super(title, ingredients, instruction, category);  // 传递 FoodCategory（早餐、午餐或晚餐）
        this.hasKimchi = hasKimchi;
    }

    public boolean isHasKimchi() {
        return hasKimchi;
    }

    public void setHasKimchi(boolean hasKimchi) {
        this.hasKimchi = hasKimchi;
    }

    @Override
    public void showRecipe() {
        System.out.println("Korean Food Recept:");
        System.out.println("Titel: " + getTitle());
        System.out.println("Category: " + getCategory());  // 打印类别（早餐、午餐或晚餐）
        System.out.println("Ingredients: " + getIngredients());
        System.out.println("Instruction: " + getInstruction());
        System.out.println("Has Kimchi: " + (hasKimchi ? "Yes" : "No"));
    }


}

