package com.hpugs.learning.pattern.structure.facade;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/2 下午6:11
 */
public class BitrateReader {

    public static VideoFile read(VideoFile file, Codec codec) {
        System.out.println("BitrateReader: reading file...");
        codec.read(file);
        return file;
    }

    public static VideoFile convert(VideoFile buffer, Codec codec) {
        System.out.println("BitrateReader: writing file...");
        codec.convert(buffer);
        return buffer;
    }

}
