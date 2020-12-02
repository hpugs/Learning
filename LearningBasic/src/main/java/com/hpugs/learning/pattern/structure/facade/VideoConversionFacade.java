package com.hpugs.learning.pattern.structure.facade;

/**
 * 外观模式
 *
 * @author gaoshang
 * date: 2020/12/2 下午6:09
 */
public class VideoConversionFacade {

    public VideoFile convertVideo(String fileName, String format) {
        System.out.println("VideoConversionFacade: conversion started.");
        VideoFile file = new VideoFile(fileName);
        Codec sourceCodec = CodecFactory.extract(file);
        Codec destinationCodec;
        if (format.equals("MP4")) {
            destinationCodec = new Mp4Codec();
        } else {
            destinationCodec = new OggCodec();
        }
        VideoFile buffer = BitrateReader.read(file, sourceCodec);
        VideoFile intermediateResult = BitrateReader.convert(buffer, destinationCodec);
        System.out.println("VideoConversionFacade: conversion completed.");
        return intermediateResult;
    }

    public static void main(String[] args) {
        VideoConversionFacade videoConversionFacade = new VideoConversionFacade();
        VideoFile videoFile = videoConversionFacade.convertVideo("test.mp4", "OGG");
        System.out.println(videoFile.toString());
    }

}
