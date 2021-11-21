package pers.kaigian.learning.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author BrianHu
 * @Create 2021-04-07 16:50
 **/
public class NIOServer {
    public static void main(String[] args) throws IOException {
        List<SocketChannel> channelList = new ArrayList<>();
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(9000));
        serverSocket.configureBlocking(false);
        System.out.println("Service started");

        while (true) {
            SocketChannel socketChannel = serverSocket.accept();
            if (socketChannel != null) {
                System.out.println("Connected Success");
                socketChannel.configureBlocking(false);
                channelList.add(socketChannel);
            }
            for (SocketChannel sc : channelList) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                int len = sc.read(byteBuffer);
                if (len > 0) {
                    System.out.println(new String(byteBuffer.array()));
                }
            }
        }
    }
}
