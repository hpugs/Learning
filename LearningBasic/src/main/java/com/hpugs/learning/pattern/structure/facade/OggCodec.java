package com.hpugs.learning.pattern.structure.facade;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/2 下午5:52
 */
public class OggCodec implements Codec {

    public static final String codecType = "OGG";

    @Override
    public void read(VideoFile videoFile) {
        System.out.println("读取OGG格式");
    }

    @Override
    public VideoFile convert(VideoFile videoFile) {
        System.out.println("将视频文件"+videoFile.getName()+"转化为OGG格式");
        videoFile.setCodecType(codecType);
        return videoFile;
    }
}
