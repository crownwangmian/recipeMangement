package com.iths.pojo;

import java.util.List;

public class WesternFood extends Food{
    private String winePairing;  // 例如西餐中的葡萄酒搭配

    public WesternFood(String title, List<Ingredients> ingredients, String instruction, String winePairing, FoodCategory category) {
        super(title, ingredients, instruction, category);  // 传递 FoodCategory（早餐、午餐或晚餐）
        this.winePairing = winePairing;
    }

    public String getWinePairing() {
        return winePairing;
    }

    public void setWinePairing(String winePairing) {
        this.winePairing = winePairing;
    }

    @Override
    public void showRecipe() {
        System.out.println("Western Food Recept:");
        System.out.println("title: " + getTitle());
        System.out.println("Category: " + getCategory());  // 打印类别（早餐、午餐或晚餐）
        System.out.println("Ingredients: " + getIngredients());
        System.out.println("Instruction: " + getInstruction());
        System.out.println("Wine Pairing: " + winePairing);
    }

}
