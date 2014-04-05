package com.smallbee.client.mock;

import com.smallbee.client.mock.handler.GameClientHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class GameClientMock {
	private final String host;
    private final int port;
    private final int messageSize;
    
    public GameClientMock(String host,int port,int msgSize){
    	this.host = host;
    	this.port = port;
    	this.messageSize = msgSize;
    }
    
    public void run() throws InterruptedException{
    	EventLoopGroup group = new NioEventLoopGroup();
    	Bootstrap bootstrap = new Bootstrap();
    	bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
    	.handler(new ChannelInitializer<Channel>() {

			@Override
			protected void initChannel(Channel ch) throws Exception {
				ch.pipeline().addLast(new GameClientHandler(messageSize));				
			}
		});
    	try {
			ChannelFuture future = bootstrap.connect(host, port).sync();			
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {			
			throw e;
		}finally{
			group.shutdownGracefully();
		}
    }
    
    public static void main(String[] args) throws Exception{
    	 new GameClientMock("localhost", 8020, 10).run();
    }
}
