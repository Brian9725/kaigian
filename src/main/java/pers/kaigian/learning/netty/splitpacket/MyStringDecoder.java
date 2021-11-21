package pers.kaigian.learning.netty.splitpacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import pers.kaigian.learning.netty.ProtostuffUtil;

import java.util.List;

/**
 * @Author BrianHu
 * @Create 2021-04-14 14:54
 **/
public class MyStringDecoder extends MessageToMessageDecoder {
	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext, Object o, List list) throws Exception {
		System.out.println("MyStringDecoder...");
		MyProtocol mProtocol = (MyProtocol) o;
		String val = ProtostuffUtil.deserializer(mProtocol.getContent(), String.class);
		list.add(val);
	}
}
