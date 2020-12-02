package com.hpugs.learning.pattern.structure.facade;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/2 下午6:06
 */
public class CodecFactory {

    public static Codec extract(VideoFile file) {
        String type = file.getCodecType();
        if (type.equalsIgnoreCase(Mp4Codec.codecType)) {
            System.out.println("CodecFactory: extracting mp4 audio...");
            return new Mp4Codec();
        } else if (type.equalsIgnoreCase(OggCodec.codecType)) {
            System.out.println("CodecFactory: extracting ogg audio...");
            return new OggCodec();
        }
        return null;
    }

}
