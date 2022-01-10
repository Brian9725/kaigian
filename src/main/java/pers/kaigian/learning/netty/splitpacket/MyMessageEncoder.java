package pers.kaigian.learning.netty.splitpacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import pers.kaigian.learning.netty.ProtostuffUtil;

/**
 * @author BrianHu
 * @create 2021-04-14 14:06
 **/
public class MyMessageEncoder extends MessageToByteEncoder<MyProtocol> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MyProtocol myProtocol, ByteBuf byteBuf) throws Exception {
        System.out.println("MyMessageEncoder...");
        System.out.println("实际内容：" + ProtostuffUtil.deserializer(myProtocol.getContent(), String.class));
        byteBuf.writeInt(myProtocol.getLength());
        byteBuf.writeBytes(myProtocol.getContent());
    }
}
