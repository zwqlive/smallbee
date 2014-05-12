package com.smallbee.core.netty.handler;

import com.smallbee.core.message.IMessage;

public interface IMessageHandler<T extends IMessage> {
	
	void handle(T message);
}
