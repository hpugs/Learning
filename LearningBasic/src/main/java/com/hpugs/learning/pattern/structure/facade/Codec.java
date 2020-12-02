package com.hpugs.learning.pattern.structure.facade;

/**
 * 解码器
 *
 * @author gaoshang
 * date: 2020/12/2 下午5:49
 */
public interface Codec {

    void read(VideoFile videoFile);

    VideoFile convert(VideoFile videoFile);

}
