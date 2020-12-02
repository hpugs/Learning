package com.hpugs.learning.pattern.structure.facade;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/2 下午5:47
 */
public class VideoFile {

    private String name;

    private String codecType;

    public VideoFile(String name) {
        this.name = name;
        this.codecType = name.substring(name.lastIndexOf(".") + 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodecType() {
        return codecType;
    }

    public void setCodecType(String codecType) {
        this.codecType = codecType;
    }

    @Override
    public String toString() {
        return "VideoFile{" +
                "name='" + name + '\'' +
                ", codecType='" + codecType + '\'' +
                '}';
    }
}
