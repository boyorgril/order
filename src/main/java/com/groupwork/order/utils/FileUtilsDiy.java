package com.groupwork.order.utils;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;

public class FileUtilsDiy {

    @Value(value = "${resources_path}")
    public static String resources_path;//资源文件绝对地址目录

    public static void test(){
        System.out.println(resources_path);
    }


    public static void saveFile(File inoutFile, String name, String path){
        System.out.println(resources_path);
    }

}
