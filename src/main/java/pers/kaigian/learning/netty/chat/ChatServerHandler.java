package pers.kaigian.learning.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Author BrianHu
 * @Create 2021-04-13 13:54
 **/
public class ChatServerHandler extends SimpleChannelInboundHandler {
	private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		channelGroup.writeAndFlush("客户端-" + channel.remoteAddress() + "上线了");
		channelGroup.add(channel);
		System.out.println(channel.remoteAddress() + "上线了");
	}

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
		Channel curChannel = channelHandlerContext.channel();
		for (Channel channel : channelGroup) {
			if (channel == curChannel) {
				channel.writeAndFlush("我说：" + o.toString());
			} else {
				channel.writeAndFlush(curChannel.remoteAddress() + "说：" + o.toString());
			}
		}
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		channelGroup.writeAndFlush("客户端-" + channel.remoteAddress() + "下线了");
		System.out.println(channel.remoteAddress() + "下线了");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}
