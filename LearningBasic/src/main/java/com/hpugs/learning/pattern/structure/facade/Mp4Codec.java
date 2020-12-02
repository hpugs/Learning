package com.hpugs.learning.pattern.structure.facade;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/2 下午5:52
 */
public class Mp4Codec implements Codec {

    public static final String codecType = "MP4";

    @Override
    public void read(VideoFile videoFile) {
        System.out.println("使用MP4格式读取文件");
    }

    @Override
    public VideoFile convert(VideoFile videoFile) {
        System.out.println("将视频文件"+videoFile.getName()+"转化为MP4格式");
        videoFile.setCodecType(codecType);
        return videoFile;
    }
}
