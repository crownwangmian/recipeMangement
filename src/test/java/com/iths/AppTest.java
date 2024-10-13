package com.iths;

import com.iths.pojo.ChineseFood;
import com.iths.pojo.FoodCategory;
import com.iths.pojo.Ingredients;
import com.iths.utlis.Hutool;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

        Hutool.saveRecipeToFile("chinese.json", foodList);


    }


    @Test
    public void testDownload() {

        // 使用类加载器来获取资源文件路径
        String filePath = getClass().getClassLoader().getResource("chinese.json").getPath();


        List<ChineseFood> chineseFoods = Hutool.loadMenuFromFile(filePath, ChineseFood.class);

        for (ChineseFood food : chineseFoods) {
            food.showRecipe();  // 这会调用每个对象的 toString() 方法
        }

    }


}
