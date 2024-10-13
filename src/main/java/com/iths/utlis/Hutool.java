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
        // 将对象列表转换为 JSON 字符串
        String jsonStr = JSONUtil.toJsonStr(menu);

        // 将 JSON 字符串写入到文件中
        FileUtil.writeString(jsonStr, filePath, StandardCharsets.UTF_8);

        System.out.println("Data saved to " + filePath);

    }

    public static <T extends Food> List<T> loadMenuFromFile(String filePath, Class<T> clazz) {
        // 读取 JSON 文件内容为字符串，指定字符集为 UTF-8
        String jsonContent = FileUtil.readString(new File(filePath), StandardCharsets.UTF_8);

        // 解析 JSON 字符串为 JSONArray
        JSONArray jsonArray = JSONUtil.parseArray(jsonContent);

        // 将 JSONArray 转换为 List<T>
        return jsonArray.toList(clazz);
    }


}
