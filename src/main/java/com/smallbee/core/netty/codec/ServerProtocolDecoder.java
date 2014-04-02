package com.smallbee.core.netty.codec;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.compression.ZlibCodecFactory;

/**
 * 编码解析器
 * 
 * @author will
 *
 */
public class ServerProtocolDecoder extends ByteToMessageDecoder {
	public ServerProtocolDecoder(){
		decoder = new EmbeddedChannel(ZlibCodecFactory.newZlibEncoder(2));
	}
	
	private EmbeddedChannel decoder;
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		decoder.writeInbound(in);
	}

}
