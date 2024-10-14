package com.iths.controller;

import com.iths.pojo.Food;

import java.util.List;

public class RecipeController {


    // add recipes
    public static <T extends Food> void addFood(List<T> menu, T food) {
        menu.add(food);
        System.out.println("Added: " + food.getTitle());
    }

    // delete recipes
    public static <T extends Food> void removeFood(List<T> menu, String title) {
        boolean removed = menu.removeIf(food -> food.getTitle().equalsIgnoreCase(title));
        if (removed) {
            System.out.println("Removed: " + title);
        } else {
            System.out.println("Food not found: " + title);
        }
    }

    // update recipes
    public static <T extends Food> void updateFood(List<T> menu, String title, T updatedFood) {
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getTitle().equalsIgnoreCase(title)) {
                menu.set(i, updatedFood);
                System.out.println("Updated: " + title);
                return;
            }
        }
        System.out.println("Food not found: " + title);
    }

    // show all recipes
    public static <T extends Food> void displayMenu(List<T> menu) {
        if (menu.isEmpty()) {
            System.out.println("Menu is empty.");
            return;
        }

        System.out.println("Menu:");
        for (T food : menu) {
            food.showRecipe();
            System.out.println("-------------------");
        }
    }

    // get current menu
    public static <T extends Food> List<T> getMenu(List<T> menu) {
        return menu;
    }

}
