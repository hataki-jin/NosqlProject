package com.bjtu.redis;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReadUtil {
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
            int ch = 0;
            StringBuilder str = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                str.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = str.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
