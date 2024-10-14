package com.iths.controller;

import com.iths.pojo.*;
import com.iths.utlis.Hutool;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuController {
    private static final Scanner scanner = new Scanner(System.in);

    public static void startMenu() {
        // Load different types of menus
        List<ChineseFood> chineseFoods = loadFoodList("src/main/json/chinese.json", ChineseFood.class);
        List<KoreanFood> koreanFoods = loadFoodList("src/main/json/korea.json", KoreanFood.class);
        List<WesternFood> westernFoods = loadFoodList("src/main/json/western.json", WesternFood.class);

        showMainMenu(chineseFoods, koreanFoods, westernFoods);
    }

    public static void showMainMenu(List<ChineseFood> chineseFoods, List<KoreanFood> koreanFoods, List<WesternFood> westernFoods) {
        boolean running = true;
        while (running) {
            System.out.println("==================== Welcome to the Menu Management System ================================");
            System.out.println("1. Add a dish");
            System.out.println("2. Delete a dish");
            System.out.println("3. Update a dish");
            System.out.println("4. Show all dishes");
            System.out.println("5. Exit and save");
            System.out.print("Please select an option (enter a number 1-5):");

            String input = scanner.nextLine().trim();
            if (input.matches("[1-5]")) {
                int choice = Integer.parseInt(input);

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
                        System.out.println("Invalid selection, please try again!");
                }
            } else {
                System.out.println("Invalid input, please enter a number between 1 and 5!");
            }
        }
    }

    // Add a dish
    private static void addFoodMenu(List<ChineseFood> chineseFoods, List<KoreanFood> koreanFoods, List<WesternFood> westernFoods) {
        System.out.println("Select cuisine (1. Chinese 2. Korean 3. Western):");
        String cuisineChoice = scanner.nextLine().trim();
        if (cuisineChoice.matches("[1-3]")) {
            System.out.println("Enter dish name:");
            String title = scanner.nextLine();
            List<Ingredients> ingredients = inputIngredients();
            System.out.println("Enter cooking instructions:");
            String instruction = scanner.nextLine();
            FoodCategory foodCategory = selectFoodCategory();
            switch (Integer.parseInt(cuisineChoice)) {
                case 1:
                    System.out.println("Enter spice level:");
                    String spiceLevel = scanner.nextLine();
                    RecipeController.addFood(chineseFoods, new ChineseFood(title, ingredients, instruction, spiceLevel, foodCategory));
                    break;
                case 2:
                    System.out.println("Contains kimchi (enter yes or no):");
                    String response = scanner.nextLine().trim();
                    boolean hasKimchi = response.equalsIgnoreCase("yes");
                    RecipeController.addFood(koreanFoods, new KoreanFood(title, ingredients, instruction, hasKimchi, foodCategory));
                    break;
                case 3:
                    System.out.println("Enter type of wine:");
                    String wine = scanner.nextLine();
                    RecipeController.addFood(westernFoods, new WesternFood(title, ingredients, instruction, wine, foodCategory));
                    break;
            }
        } else {
            System.out.println("Invalid selection, please enter a number between 1 and 3!");
        }
    }

    // Delete a dish
    private static void deleteFoodMenu(List<ChineseFood> chineseFoods, List<KoreanFood> koreanFoods, List<WesternFood> westernFoods) {
        System.out.println("Select cuisine (1. Chinese 2. Korean 3. Western):");
        String cuisineChoice = scanner.nextLine().trim();
        if (cuisineChoice.matches("[1-3]")) {
            System.out.println("Enter the name of the dish to delete:");
            String title = scanner.nextLine();
            switch (Integer.parseInt(cuisineChoice)) {
                case 1:
                    RecipeController.removeFood(chineseFoods, title);
                    break;
                case 2:
                    RecipeController.removeFood(koreanFoods, title);
                    break;
                case 3:
                    RecipeController.removeFood(westernFoods, title);
                    break;
            }
        } else {
            System.out.println("Invalid selection, please enter a number between 1 and 3!");
        }
    }

    // Update a dish
    // TODO: This part of the code is highly similar to the addition method, and the method can be extracted in the next version.
    private static void updateFoodMenu(List<ChineseFood> chineseFoods, List<KoreanFood> koreanFoods, List<WesternFood> westernFoods) {
        System.out.println("Select cuisine (1. Chinese 2. Korean 3. Western):");
        String cuisineChoice = scanner.nextLine().trim();
        if (cuisineChoice.matches("[1-3]")) {
            System.out.println("Enter the name of the dish to update:");
            String title = scanner.nextLine();

            switch (Integer.parseInt(cuisineChoice)) {
                case 1:
                    System.out.println("Enter new dish name:");
                    String newChineseTitle = scanner.nextLine();
                    List<Ingredients> newChineseIngredients = inputIngredients();
                    System.out.println("Enter new cooking instructions:");
                    String newChineseInstruction = scanner.nextLine();
                    System.out.println("Enter new spice level:");
                    String newSpiceLevel = scanner.nextLine();
                    FoodCategory chineseCategory = selectFoodCategory();
                    RecipeController.updateFood(chineseFoods, title,
                            new ChineseFood(newChineseTitle, newChineseIngredients, newChineseInstruction, newSpiceLevel, chineseCategory));
                    break;

                case 2:
                    System.out.println("Enter new dish name:");
                    String newKoreanTitle = scanner.nextLine();
                    List<Ingredients> newKoreanIngredients = inputIngredients();
                    System.out.println("Enter new cooking instructions:");
                    String newKoreanInstruction = scanner.nextLine();
                    System.out.println("Contains kimchi (enter yes or no):");
                    String response = scanner.nextLine();
                    boolean newHasKimchi = response.equalsIgnoreCase("yes");
                    FoodCategory koreanCategory = selectFoodCategory();
                    RecipeController.updateFood(koreanFoods, title,
                            new KoreanFood(newKoreanTitle, newKoreanIngredients, newKoreanInstruction, newHasKimchi, koreanCategory));
                    break;

                case 3:
                    System.out.println("Enter new dish name:");
                    String newWesternTitle = scanner.nextLine();
                    List<Ingredients> newWesternIngredients = inputIngredients();
                    System.out.println("Enter new cooking instructions:");
                    String newWesternInstruction = scanner.nextLine();
                    System.out.println("Enter new type of wine:");
                    String newWineType = scanner.nextLine();
                    FoodCategory westernCategory = selectFoodCategory();
                    RecipeController.updateFood(westernFoods, title,
                            new WesternFood(newWesternTitle, newWesternIngredients, newWesternInstruction, newWineType, westernCategory));
                    break;
            }
        } else {
            System.out.println("Invalid selection, please enter a number between 1 and 3!");
        }
    }

    // Display all dishes
    private static void displayFoodMenu(List<ChineseFood> chineseFoods, List<KoreanFood> koreanFoods, List<WesternFood> westernFoods) {
        System.out.println("Chinese menu:");
        RecipeController.displayMenu(chineseFoods);
        System.out.println("Korean menu:");
        RecipeController.displayMenu(koreanFoods);
        System.out.println("Western menu:");
        RecipeController.displayMenu(westernFoods);
    }

    // Save all menus
    private static void saveFoodLists(List<ChineseFood> chineseFoods, List<KoreanFood> koreanFoods, List<WesternFood> westernFoods) {
        String projectPath = System.getProperty("user.dir");
        String chinesePath = projectPath + "/src/main/json/chinese.json";
        String koreanPath = projectPath + "/src/main/json/korea.json";
        String westernPath = projectPath + "/src/main/json/western.json";

        Hutool.saveRecipeToFile(chinesePath, chineseFoods);
        Hutool.saveRecipeToFile(koreanPath, koreanFoods);
        Hutool.saveRecipeToFile(westernPath, westernFoods);

        System.out.println("All menus saved.");
    }

    // Load different list collections
    public static <T extends Food> List<T> loadFoodList(String fileName, Class<T> clazz) {
        List<T> menu = Hutool.loadMenuFromFile(fileName, clazz);
        return new ArrayList<>(menu);  // Convert to ArrayList to ensure list is mutable
    }

    // Method for inputting multiple ingredients
    private static List<Ingredients> inputIngredients() {
        List<Ingredients> ingredients = new ArrayList<>();  // Used to store multiple ingredients
        String ingredientName;
        double amount;
        String unit;
        do {
            System.out.println("Enter ingredient name (enter 'Y' to stop):");
            ingredientName = scanner.nextLine();
            if (ingredientName.equalsIgnoreCase("y")) {
                break;
            }
            System.out.println("Enter amount:");
            amount = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter unit:");
            unit = scanner.nextLine();

            ingredients.add(new Ingredients(ingredientName, amount, unit));
        } while (true);

        return ingredients;  // Return the complete ingredient list
    }

    // Select FoodCategory
    private static FoodCategory selectFoodCategory() {
        System.out.println("Select dish category (1. Breakfast 2. Lunch 3. Dinner):");
        String categoryChoice = scanner.nextLine().trim();
        if (categoryChoice.matches("[1-3]")) {
            switch (Integer.parseInt(categoryChoice)) {
                case 1:
                    return FoodCategory.BREAKFAST;
                case 2:
                    return FoodCategory.LUNCH;
                case 3:
                    return FoodCategory.DINNER;
            }
        }
        System.out.println("Invalid selection, defaulting to Lunch.");
        return FoodCategory.LUNCH;
    }
}
