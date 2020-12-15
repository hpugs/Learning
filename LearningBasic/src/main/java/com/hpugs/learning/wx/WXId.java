package com.hpugs.learning.wx;

import java.util.Base64;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/10 上午10:42
 */
public class WXId {

    public static void main(String[] args) {
        String openId1 = "olrWO4gCHQ6k7IPSkBhEgsTbojOw";
        String openId2 = "oSCQQ5fJB1aYQZN2N-CVybZZQFIg";
        String openId3 = "oL7Fo6IyPoJ955o5kTizYJqFMxgM";
        String openId4 = "oZ7d35IBKelehjSOVGDwDGstFK3c";
        String openId5 = "o7Mye4sKQfFK1i6Fx2gZxaTJhRWs";
        convertOpenId(openId1);
    }

    private static void convertOpenId(String openId){
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(openId);
        System.out.println(String.valueOf(decode));
    }

}
