package com.smallbee.core.session;

import java.net.SocketAddress;

import com.smallbee.core.message.IMessage;

import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * @author will
 *
 */
public class NettySession implements ISession{
	
	public NettySession(ChannelHandlerContext ctx){
		if(ctx == null){
			throw new IllegalArgumentException("ctx can not be null");
		}
		this.handlerContext = ctx;
	}
	
	private ChannelHandlerContext handlerContext;

	@Override
	public void write(IMessage message) {
		handlerContext.write(message);
	}

	@Override
	public SocketAddress remoteAddress() {		
		return handlerContext.channel().remoteAddress();
	}
	
}
