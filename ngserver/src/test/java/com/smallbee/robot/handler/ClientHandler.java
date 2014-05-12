package com.smallbee.robot.handler;

import io.netty.channel.ChannelHandlerContext;

import com.smallbee.core.netty.handler.MessageHandler;
import com.smallbee.robot.message.CSTestMessage;
import com.smallbee.robot.message.SCTestMessage;

public class ClientHandler extends MessageHandler<SCTestMessage>{
	
	@Override
    public void channelActive(ChannelHandlerContext ctx) {
        CSTestMessage msg = new CSTestMessage();
        msg.setPlayerId(1000000L);
        ctx.write(msg);
    }

	@Override
	public void handle(SCTestMessage message) {
		// TODO Auto-generated method stub
		
	}

}
