package com.hpugs.learning.io.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/29 上午11:01
 */
public class NIOClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream out = socket.getOutputStream();
        String s = "hello world";
        out.write(s.getBytes());
        out.flush();
        out.close();
    }

}
