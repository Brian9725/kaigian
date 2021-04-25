package pers.kaigian.learning.netty.splitpacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author hukaiyang
 * @date 2021-04-14 14:59
 **/
public class SecondServerHandler extends SimpleChannelInboundHandler<String> {
	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
		System.out.println("SecondServerHandler:" + s);
	}
}
