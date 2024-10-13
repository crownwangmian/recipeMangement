package com.iths.controller;

import com.iths.pojo.Food;

import java.util.List;

public class RecipeController {


    // 添加菜品（方法级别的泛型处理）
    public static <T extends Food> void addFood(List<T> menu, T food) {
        menu.add(food);
        System.out.println("Added: " + food.getTitle());
    }

    // 删除菜品（根据标题删除）
    public static <T extends Food> void removeFood(List<T> menu, String title) {
        boolean removed = menu.removeIf(food -> food.getTitle().equalsIgnoreCase(title));
        if (removed) {
            System.out.println("Removed: " + title);
        } else {
            System.out.println("Food not found: " + title);
        }
    }

    // 更新菜品（根据标题更新）
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

    // 显示菜单（显示菜单中的所有菜品）
    public static <T extends Food> void displayMenu(List<T> menu) {
        if (menu.isEmpty()) {
            System.out.println("Menu is empty.");
            return;
        }

        System.out.println("Menu:");
        for (T food : menu) {
            food.showRecipe();  // 调用每个子类的展示方法
            System.out.println("-------------------");
        }
    }

    // 获取当前菜单（直接返回列表）
    public static <T extends Food> List<T> getMenu(List<T> menu) {
        return menu;
    }

}
