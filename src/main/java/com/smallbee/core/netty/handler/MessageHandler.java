package com.smallbee.core.netty.handler;

import com.smallbee.core.message.Message;

import io.netty.channel.ChannelHandlerAdapter;

public abstract class MessageHandler<T extends Message> extends ChannelHandlerAdapter implements IMessageHandler<T>{

	@Override
	public abstract void handle(T message);
	
}
