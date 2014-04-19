package com.smallbee.robot;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import com.smallbee.core.netty.codec.ServerProtocolFactory;
import com.smallbee.core.netty.server.MessageRegistryService;
import com.smallbee.robot.handler.ClientHandler;
import com.smallbee.robot.message.CSTestMessage;
import com.smallbee.robot.message.SCTestMessage;

/**
 * 机器人客户端
 * 
 * @author will
 * 
 */
public class Robot implements IClient {

	@Override
	public IClient connect(String host, int port) {
		
		Bootstrap bootstrap = new Bootstrap();
		EventLoopGroup group = new NioEventLoopGroup();
		bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<Channel>() {

			@Override
			protected void initChannel(Channel ch) throws Exception {
				ch.pipeline().addFirst(ServerProtocolFactory.getEncoder());
				ch.pipeline().addFirst(ServerProtocolFactory.getDecoder());
				ch.pipeline().addLast(new ClientHandler());
			}
		});
		ChannelFuture cf = bootstrap.connect(host, port);
		try {
			cf.sync();
			cf.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			group.shutdownGracefully();
		}
		return this;
	}

}
