package pers.kaigian.learning.netty.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author BrianHu
 * @Create 2021-04-13 13:54
 **/
public class ChatClientHandler extends SimpleChannelInboundHandler {
	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
		System.out.println(o.toString());
	}
}
