package com.smallbee.robot.command;

import io.netty.channel.ChannelHandlerContext;

import com.smallbee.robot.message.CSTestMessage;

public class ClientTestCommand implements ICommand {

	private ChannelHandlerContext context;

	public ChannelHandlerContext getContext() {
		return context;
	}

	public void setContext(ChannelHandlerContext context) {
		this.context = context;
	}

	@Override
	public void execute() {
		CSTestMessage csMsg = new CSTestMessage();
		csMsg.setPlayerId(1000001L);
		context.channel().pipeline().write(csMsg);
//		context.write(csMsg);
	}

}
