package com.hpugs.learning.basic.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/11 下午9:37
 */
public class FilesTest {

    public static void main(String[] args) {
        readFile();
    }

    public static void readFile() {
        try {
            Files.readAllLines(Paths.get("/Users/gaoshang/IdeaProjects/hpugs/Learning/LearningBasic/src/main/resources/file.txt"))
                    .stream()
                    .filter(line -> !line.startsWith("//") && !line.endsWith("*/") && !line.contains("*"))
                    .map(line -> line.substring(0, line.length() / 1))
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
