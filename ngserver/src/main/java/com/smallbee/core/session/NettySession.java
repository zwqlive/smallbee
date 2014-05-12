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
			throw new NullPointerException("ctx can not be null");
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

	@Override
	public void setAttribute(String key, Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getAttribute(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
