package pers.kaigian.learning.netty.splitpacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author BrianHu
 * @Create 2021-04-14 14:05
 **/
public class MyMessageDecoder extends ByteToMessageDecoder {
    private int length = 0;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("MyMessageDecoder");
        if (byteBuf.readableBytes() >= 4) {
            if (length == 0) {
                length = byteBuf.readInt();
            }
            if (byteBuf.readableBytes() >= length) {
                byte[] content = new byte[length];
                byteBuf.readBytes(content);
                MyProtocol mProtocol = new MyProtocol();
                mProtocol.setLength(length);
                mProtocol.setContent(content);
                list.add(mProtocol);
                length = 0;
            }
        }
    }
}
