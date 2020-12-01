package com.hpugs.learning.io.nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/29 上午10:28
 */
public class NIO {

    public static void main(String[] args) {
//        fastCopy();
//
//        System.out.println("-------------------------");

        socketService();
    }

    public static void fastCopy() {
        System.out.println("文件复制开始");

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(new File("/Users/gaoshang/IdeaProjects/hpugs/Learning/LearningBasic/src/main/resources/file.txt"));

            FileChannel fileInChannel = fileInputStream.getChannel();

            fileOutputStream = new FileOutputStream(new File("/Users/gaoshang/IdeaProjects/hpugs/Learning/LearningBasic/src/main/resources/fileCopy.txt"));

            FileChannel fileOutChannel = fileOutputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

            int read = -1;
            while ((read = fileInChannel.read(byteBuffer)) > 0) {

                /* 切换读写 */
                byteBuffer.flip();

                fileOutChannel.write(byteBuffer);

                /* 清空缓冲区 */
                byteBuffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("文件复制结束");
    }

    private static void socketService() {

        try {
            Selector selector = Selector.open();

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8888);
            serverSocket.bind(inetSocketAddress);

            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();

                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey.channel();

                        SocketChannel accept = serverSocketChannel1.accept();
                        accept.configureBlocking(false);
                        accept.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

                    } else if (selectionKey.isReadable()) {
                        SocketChannel sChannel = (SocketChannel) selectionKey.channel();
                        System.out.println(readDataFromSocketChannel(sChannel));

                        if (selectionKey.isWritable()) {
                            writeDataFromSocketChannel(sChannel, "hello NIO");
                        }

                        sChannel.close();
                    }
                }

                iterator.remove();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String readDataFromSocketChannel(SocketChannel sChannel) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuilder data = new StringBuilder();

        while (true) {

            buffer.clear();
            int n = sChannel.read(buffer);
            if (n == -1) {
                break;
            }
            buffer.flip();
            int limit = buffer.limit();
            char[] dst = new char[limit];
            for (int i = 0; i < limit; i++) {
                dst[i] = (char) buffer.get(i);
            }
            data.append(dst);
            buffer.clear();
        }
        return data.toString();
    }

    private static void writeDataFromSocketChannel(SocketChannel sChannel, String str) {

        try {
            ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());

            sChannel.write(buffer);

            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
