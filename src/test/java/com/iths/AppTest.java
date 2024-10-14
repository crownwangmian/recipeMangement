package com.iths;

import com.iths.controller.RecipeController;
import com.iths.pojo.ChineseFood;
import com.iths.pojo.FoodCategory;
import com.iths.pojo.Ingredients;
import com.iths.utlis.Hutool;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testAddFood() {
        List<ChineseFood> foodList = new ArrayList<>();
        Ingredients ingredient = new Ingredients("sugar", 100, "grams");
        List<Ingredients> ins = new ArrayList<>();
        ins.add(ingredient);
        ChineseFood food = new ChineseFood("noodle", ins, "instruction", "high", FoodCategory.LUNCH);
        ChineseFood food2 = new ChineseFood("noodle2", ins, "instruction", "high", FoodCategory.LUNCH);
        foodList.add(food);
        foodList.add(food2);
        Hutool.saveRecipeToFile("src/main/json/chinese.json", foodList);
    }

    @Test
    public void testDownload() {
        // 使用类加载器来获取资源文件路径
        String filePath = getClass().getClassLoader().getResource("src/main/json/chinese.json").getPath();
        List<ChineseFood> chineseFoods = Hutool.loadMenuFromFile(filePath, ChineseFood.class);
        for (ChineseFood food : chineseFoods) {
            food.showRecipe();  // 这会调用每个对象的 toString() 方法
        }
    }

    @Test
    public void add() {
        ArrayList<ChineseFood> chineseFood = new ArrayList<>();
        ChineseFood food1 = new ChineseFood("noodle", List.of(new Ingredients("sugar", 100, "grams")),
                "instruction", "high", FoodCategory.LUNCH);

        // Assert initial size is 0
        assertEquals(0, chineseFood.size());

        // Add food to the list using RecipeController
        RecipeController.addFood(chineseFood, food1);

        // Assert that the size of the list is now 1
        assertEquals(1, chineseFood.size());

        // Assert that the added food is the expected one
        assertEquals("noodle", chineseFood.get(0).getTitle());

    }
}
