package com.iths.utlis;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.iths.pojo.Food;

import java.io.File;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Hutool {

    private Hutool() {
    }

    public static <T extends Food> void saveRecipeToFile(String filePath, List<T> menu) {
        try {
            // 检查文件路径是否有效
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                System.out.println("Warning: The directory does not exist, creating directories...");
                file.getParentFile().mkdirs();  // 创建父目录
            }

            // 将对象列表转换为 JSON 字符串
            String jsonStr = JSONUtil.toJsonStr(menu);

            // 将 JSON 字符串写入到文件中
            FileUtil.writeString(jsonStr, filePath, StandardCharsets.UTF_8);

            System.out.println("Data saved to " + filePath);
        } catch (Exception e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }


    }


    public static <T extends Food> List<T> loadMenuFromFile(String filePath, Class<T> clazz) {
        // 读取 JSON 文件内容为字符串，指定字符集为 UTF-8
        String jsonContent = FileUtil.readString(new File(filePath), StandardCharsets.UTF_8);

        // 检查文件是否为空或内容为空字符串
        if (jsonContent == null || jsonContent.trim().isEmpty()) {
            System.out.println("Warning: File is empty or contains invalid content.");
            return List.of();  // 返回一个空的 List
        }

        // 解析 JSON 字符串为 JSONArray
        JSONArray jsonArray;
        try {
            jsonArray = JSONUtil.parseArray(jsonContent);
        } catch (Exception e) {
            System.out.println("Error parsing JSON content: " + e.getMessage());
            return List.of();  // 返回一个空的 List 以避免程序崩溃
        }
        // 将 JSONArray 转换为 List<T>
        return jsonArray.toList(clazz);
    }


}
