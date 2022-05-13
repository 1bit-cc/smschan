package com.example.smschan.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Config {
    // 读取指定文件内容
    public String getFileString(String filepath) throws IOException {
        File tokenFile = new File(filepath);
        byte[] bytesToken = new byte[1024];
        StringBuffer sb = new StringBuffer();
        FileInputStream in = new FileInputStream(tokenFile);
        int len;
        while ((len = in.read(bytesToken)) != -1) {
            sb.append(new String(bytesToken, 0, len));
        }
        in.close();
        String rDat = sb.toString();
        return rDat;
    }

    // 读取指定文件内容，并分割成字符串数组，分隔符为","
    public String[] getFileArrays(String filepath) throws IOException{
        String fileString = this.getFileString(filepath);
        String[] fileArrays = fileString.split(",");
        return fileArrays;
    }
}
