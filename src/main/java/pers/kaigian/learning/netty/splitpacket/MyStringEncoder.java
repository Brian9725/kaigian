package pers.kaigian.learning.netty.splitpacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import pers.kaigian.learning.netty.ProtostuffUtil;

import java.util.List;

/**
 * @Author BrianHu
 * @Create 2021-04-14 14:35
 **/
public class MyStringEncoder extends MessageToMessageEncoder<String> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, String s, List<Object> list) throws Exception {
        System.out.println("MyStringEncoder...");
        byte[] content = ProtostuffUtil.serializer(s);
        MyProtocol mProtocol = new MyProtocol();
        mProtocol.setLength(content.length);
        mProtocol.setContent(content);
        list.add(mProtocol);
    }
}
