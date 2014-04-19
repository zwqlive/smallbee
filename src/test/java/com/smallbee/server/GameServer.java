package com.smallbee.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import com.smallbee.core.netty.codec.ServerProtocolFactory;
import com.smallbee.server.handler.GameServerSimpleHandler;
import com.sun.tools.javac.main.JavacOption.Option;

public class GameServer {
	 private final int port;

	    public GameServer(int port) {
	        this.port = port;
	    }

	    public void run() throws Exception {
	        EventLoopGroup bossGroup = new NioEventLoopGroup();
	        EventLoopGroup workerGroup = new NioEventLoopGroup();
	        try {
	            ServerBootstrap b = new ServerBootstrap();
	            b.group(bossGroup, workerGroup)
	             .channel(NioServerSocketChannel.class)
	             .option(ChannelOption.TCP_NODELAY, true)
	             .option(ChannelOption.SO_BACKLOG, 100)
	             .option(ChannelOption.SO_KEEPALIVE, true)
	             .option(ChannelOption.SO_TIMEOUT, 3000)
	             .handler(new LoggingHandler(LogLevel.INFO))
	             .childHandler(new ChannelInitializer<SocketChannel>() {
	                 @Override
	                 public void initChannel(SocketChannel ch) throws Exception {
	                	 ch.pipeline().addFirst(ServerProtocolFactory.getEncoder());
	                	 ch.pipeline().addFirst(ServerProtocolFactory.getDecoder());
	                     ch.pipeline().addLast(new GameServerSimpleHandler());
	                 }
	             });

	            ChannelFuture f = b.bind(port).sync();
	            f.channel().closeFuture().sync();
	        } finally {
	            bossGroup.shutdownGracefully();
	            workerGroup.shutdownGracefully();
	        }
	    }

	    public static void main(String[] args) throws Exception {
	        int port=8020;	        
	        new GameServer(port).run();
	    }
}
