package com.smallbee.core.netty.codec;

import com.smallbee.core.message.IMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ServerProtocolEncoder extends MessageToByteEncoder<IMessage>{

	@Override
	protected void encode(ChannelHandlerContext arg0, IMessage arg1, ByteBuf arg2) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
