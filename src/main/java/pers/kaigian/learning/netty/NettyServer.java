package pers.kaigian.learning.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Author BrianHu
 * @Create 2021-04-13 10:32
 **/
public class NettyServer {
	public static void main(String[] args) {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							socketChannel.pipeline().addLast("decoder", new StringDecoder());
							socketChannel.pipeline().addLast("encoder", new StringEncoder());
							socketChannel.pipeline().addLast(new NettyServerHandler());
						}
					});
			System.out.println("netty server start");
			ChannelFuture channelFuture = bootstrap.bind(9000).sync();
			channelFuture.addListener((ChannelFutureListener) channelFuture1 -> {
				if (channelFuture1.isSuccess()) {
					System.out.println("监听9000成功");
				} else {
					System.out.println("监听9000失败");
				}
			});
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
