package com.iths.controller;

import com.iths.pojo.Food;

import java.util.List;

public class RecipeController<T extends Food> {
    private List<T> menu;  // 成员变量，存储 T 类型的 Food 子类

    // 构造函数，接收 List<T> 作为初始菜单
    public RecipeController(List<T> menu) {
        this.menu = menu;
    }

    // 添加菜品
    public void addFood(T food) {
        menu.add(food);
        System.out.println("Added: " + food.getTitle());
    }

    // 删除菜品，根据标题删除
    public void removeFood(String title) {
        menu.removeIf(food -> food.getTitle().equalsIgnoreCase(title));
        System.out.println("Removed: " + title);
    }

    // 更新菜品
    public void updateFood(String title, T updatedFood) {
        for (int i = 0; i < menu.size(); i++) {
            if (menu.get(i).getTitle().equalsIgnoreCase(title)) {
                menu.set(i, updatedFood);
                System.out.println("Updated: " + title);
                return;
            }
        }
        System.out.println("Food not found: " + title);
    }

    // 显示菜单
    public void displayMenu() {
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

    // 获取当前菜单
    public List<T> getMenu() {
        return menu;
    }

}
