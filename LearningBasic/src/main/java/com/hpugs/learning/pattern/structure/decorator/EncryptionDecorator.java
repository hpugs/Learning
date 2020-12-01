package com.hpugs.learning.pattern.structure.decorator;

import java.util.Base64;

/**
 * 加密装饰
 *
 * @author gaoshang
 * date: 2020/12/1 下午3:41
 */
public class EncryptionDecorator extends DataSourceDecorator {

    public EncryptionDecorator(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void write(String txt) {
        super.write(encode(txt));
    }

    @Override
    public String read() {
        return decode(super.read());
    }

    private String encode(String data) {
        byte[] result = data.getBytes();
        for (int i = 0; i < result.length; i++) {
            result[i] += (byte) 1;
        }
        return Base64.getEncoder().encodeToString(result);
    }

    private String decode(String data) {
        byte[] result = Base64.getDecoder().decode(data);
        for (int i = 0; i < result.length; i++) {
            result[i] -= (byte) 1;
        }
        return new String(result);
    }
}
