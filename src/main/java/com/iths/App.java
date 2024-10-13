package com.iths;

import com.iths.controller.RecipeController;
import com.iths.pojo.*;
import com.iths.utlis.Hutool;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 加载不同类型的菜单
        List<ChineseFood> chineseFoods = loadFoodList("src/main/json/chinese.json", ChineseFood.class);
        List<KoreanFood> koreanFoods = loadFoodList("src/main/json/korea.json", KoreanFood.class);
        List<WesternFood> westernFoods = loadFoodList("src/main/json/western.json", WesternFood.class);

        showMainMenu(chineseFoods, koreanFoods, westernFoods);

    }

    public static void showMainMenu(List<ChineseFood> chineseFoods, List<KoreanFood> koreanFoods, List<WesternFood> westernFoods) {
        boolean running = true;
        while (running) {
            System.out.println("====================欢迎进入菜单管理系统==================================");
            System.out.println("1. 添加菜品");
            System.out.println("2. 删除菜品");
            System.out.println("3. 更新菜品");
            System.out.println("4. 显示所有菜品");
            System.out.println("5. 退出并保存");
            System.out.print("请选择操作（输入数字）：");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 处理换行符

            switch (choice) {
                case 1:
                    addFoodMenu(chineseFoods, koreanFoods, westernFoods);
                    break;
                case 2:
                    deleteFoodMenu(chineseFoods, koreanFoods, westernFoods);
                    break;
                case 3:
                    updateFoodMenu(chineseFoods, koreanFoods, westernFoods);
                    break;
                case 4:
                    displayFoodMenu(chineseFoods, koreanFoods, westernFoods);
                    break;
                case 5:
                    saveFoodLists(chineseFoods, koreanFoods, westernFoods);
                    running = false;
                    break;
                default:
                    System.out.println("无效选择，请重新输入！");
            }
        }
    }

    // 添加菜品
    private static void addFoodMenu(List<ChineseFood> chineseFoods, List<KoreanFood> koreanFoods, List<WesternFood> westernFoods) {
        System.out.println("请选择菜系（1. 中餐 2. 韩餐 3. 西餐）：");
        int cuisineChoice = scanner.nextInt();
        scanner.nextLine(); // 处理换行符
        System.out.println("请输入菜品名称：");
        String title = scanner.nextLine();
        List<Ingredients> ingredients = inputIngredients();
        System.out.println("请输入烹饪说明：");
        String instruction = scanner.nextLine();
        FoodCategory foodCategory = selectFoodCategory();
        switch (cuisineChoice) {
            case 1:
                System.out.println("请输入辛辣程度：");
                String spiceLevel = scanner.nextLine();
                RecipeController.addFood(chineseFoods, new ChineseFood(title, ingredients, instruction, spiceLevel, foodCategory));
                break;
            case 2:
                System.out.println("是否含有泡菜 (请输入 yes 或 no)：");
                String response = scanner.next();
                boolean hasKimchi = response.equalsIgnoreCase("yes");
                RecipeController.addFood(koreanFoods, new KoreanFood(title, ingredients, instruction, hasKimchi, foodCategory));
                break;
            case 3:
                System.out.println("请输入酒的类型：");
                String wine = scanner.nextLine();
                RecipeController.addFood(westernFoods, new WesternFood(title, ingredients, instruction, wine, foodCategory));
                break;
            default:
                System.out.println("无效选择，请重新输入！");
        }
    }

    // 删除菜品
    private static void deleteFoodMenu(List<ChineseFood> chineseFoods, List<KoreanFood> koreanFoods, List<WesternFood> westernFoods) {
        System.out.println("请选择菜系（1. 中餐 2. 韩餐 3. 西餐）：");
        int cuisineChoice = scanner.nextInt();
        scanner.nextLine(); // 处理换行符
        System.out.println("请输入要删除的菜品名称：");
        String title = scanner.nextLine();
        switch (cuisineChoice) {
            case 1:
                RecipeController.removeFood(chineseFoods, title);
                break;
            case 2:
                RecipeController.removeFood(koreanFoods, title);
                break;
            case 3:
                RecipeController.removeFood(westernFoods, title);
                break;
            default:
                System.out.println("无效选择，请重新输入！");
        }
    }

    // 更新菜品
    private static void updateFoodMenu(List<ChineseFood> chineseFoods, List<KoreanFood> koreanFoods, List<WesternFood> westernFoods) {
        System.out.println("请选择菜系（1. 中餐 2. 韩餐 3. 西餐）：");
        int cuisineChoice = scanner.nextInt();
        scanner.nextLine(); // 处理换行符
        System.out.println("请输入要更新的菜品名称：");
        String title = scanner.nextLine();

        // 根据选择的菜系进行相应的更新逻辑
        switch (cuisineChoice) {
            case 1:
                // 中餐 - 更新辛辣程度
                System.out.println("请输入新的菜品名称：");
                String newChineseTitle = scanner.nextLine();
                List<Ingredients> newChineseIngredients = inputIngredients();
                System.out.println("请输入新的烹饪说明：");
                String newChineseInstruction = scanner.nextLine();
                System.out.println("请输入新的辛辣程度：");
                String newSpiceLevel = scanner.nextLine();
                FoodCategory chineseCategory = selectFoodCategory();

                RecipeController.updateFood(chineseFoods, title,
                        new ChineseFood(newChineseTitle, newChineseIngredients, newChineseInstruction, newSpiceLevel, chineseCategory));
                break;

            case 2:
                // 韩餐 - 更新是否含有泡菜
                System.out.println("请输入新的菜品名称：");
                String newKoreanTitle = scanner.nextLine();
                List<Ingredients> newKoreanIngredients = inputIngredients();
                System.out.println("请输入新的烹饪说明：");
                String newKoreanInstruction = scanner.nextLine();
                System.out.println("是否含有泡菜 (请输入 yes 或 no)：");
                String response = scanner.next();
                boolean newHasKimchi = response.equalsIgnoreCase("yes");
                FoodCategory koreanCategory = selectFoodCategory();

                RecipeController.updateFood(koreanFoods, title,
                        new KoreanFood(newKoreanTitle, newKoreanIngredients, newKoreanInstruction, newHasKimchi, koreanCategory));
                break;

            case 3:
                // 西餐 - 更新酒类型
                System.out.println("请输入新的菜品名称：");
                String newWesternTitle = scanner.nextLine();
                List<Ingredients> newWesternIngredients = inputIngredients();
                System.out.println("请输入新的烹饪说明：");
                String newWesternInstruction = scanner.nextLine();
                System.out.println("请输入新的酒的类型：");
                String newWineType = scanner.nextLine();
                FoodCategory westernCategory = selectFoodCategory();

                RecipeController.updateFood(westernFoods, title,
                        new WesternFood(newWesternTitle, newWesternIngredients, newWesternInstruction, newWineType, westernCategory));
                break;

            default:
                System.out.println("无效选择，请重新输入！");
        }
    }

    // 显示所有菜品
    private static void displayFoodMenu(List<ChineseFood> chineseFoods, List<KoreanFood> koreanFoods, List<WesternFood> westernFoods) {
        System.out.println("中餐菜单：");
        RecipeController.displayMenu(chineseFoods);
        System.out.println("韩餐菜单：");
        RecipeController.displayMenu(koreanFoods);
        System.out.println("西餐菜单：");
        RecipeController.displayMenu(westernFoods);
    }

    // 保存所有菜单
    private static void saveFoodLists(List<ChineseFood> chineseFoods, List<KoreanFood> koreanFoods, List<WesternFood> westernFoods) {
        String projectPath = System.getProperty("user.dir");
        String chinesePath = projectPath + "/src/main/json/chinese.json";
        String koreanPath = projectPath + "/src/main/json/korea.json";
        String westernPath = projectPath + "/src/main/json/western.json";

        Hutool.saveRecipeToFile(chinesePath, chineseFoods);
        Hutool.saveRecipeToFile(koreanPath, koreanFoods);
        Hutool.saveRecipeToFile(westernPath, westernFoods);

        System.out.println("所有菜单已保存。");
    }


    // 获取不同的list集合
    public static <T extends Food> List<T> loadFoodList(String fileName, Class<T> clazz) {

        // 使用 Hutool 的 loadMenuFromFile 来加载 JSON 文件
        List<T> menu = Hutool.loadMenuFromFile(fileName, clazz);

        // 将返回的不可变列表转换为可变列表
        return new ArrayList<>(menu);  // 转换为 ArrayList 确保列表是可变的
    }

    // 方法用于输入多个食材
    private static List<Ingredients> inputIngredients() {
        List<Ingredients> ingredients = new ArrayList<>();  // 用于存储多个食材
        String ingredientName;
        double amount;
        String unit;
        do {
            System.out.println("请输入食材名称（输入 'Y' 退出）：");
            ingredientName = scanner.nextLine();
            if (ingredientName.equalsIgnoreCase("y")) {
                break;
            }

            System.out.println("请输入食材数量：");
            amount = scanner.nextDouble();
            scanner.nextLine(); // 处理换行符

            System.out.println("请输入单位：");
            unit = scanner.nextLine();

            // 将输入的食材添加到列表
            ingredients.add(new Ingredients(ingredientName, amount, unit));

        } while (true);

        return ingredients;  // 返回完整的食材列表
    }

    // 选择 FoodCategory
    private static FoodCategory selectFoodCategory() {
        System.out.println("请选择菜品类别（1. 早餐 2. 午餐 3. 晚餐）：");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine(); // 处理换行符

        switch (categoryChoice) {
            case 1:
                return FoodCategory.BREAKFAST;
            case 2:
                return FoodCategory.LUNCH;
            case 3:
                return FoodCategory.DINNER;
            default:
                System.out.println("无效选择，默认为午餐。");
                return FoodCategory.LUNCH;
        }
    }
}



