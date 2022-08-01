package com.example.springboot.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        generator();
    }

    private static void generator(){
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/develop?serverTimezone=GMT%2b8", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("Tropinone") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\files\\html\\develop\\springboot\\src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.springboot") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\files\\html\\develop\\springboot\\src\\main\\resources\\mapper\\")); //设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();//允许使用lombok
                    builder.mapperBuilder().enableMapperAnnotation().build();//在mapper前加上@Mapper
                    builder.controllerBuilder().enableRestStyle().enableHyphenStyle();//使用RestController而不是Controller,并且开启下划线转驼峰
                    builder.addInclude("sys_admin") // 设置需要生成的表名
                            .addTablePrefix("t_", "sys_"); //设置过滤表前缀
                })
                .execute();
    }
}
