package com.smallbee.client.mock.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class GameClientHandler extends ChannelInboundHandlerAdapter {
	private final ByteBuf firstMessage;

    /**
     * Creates a client-side handler.
     */
    public GameClientHandler(int firstMessageSize) {
        if (firstMessageSize <= 0) {
            throw new IllegalArgumentException("firstMessageSize: " + firstMessageSize);
        }
        firstMessage = Unpooled.buffer(firstMessageSize);
        for (int i = 0; i < firstMessage.capacity(); i ++) {
            firstMessage.writeByte((byte) i);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
    	String test="test";
    	ByteBuf bb = Unpooled.buffer(test.length()); 
    	bb.writeBytes(test.getBytes());
        ctx.write(bb);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msgs) throws Exception {
        ctx.write(msgs);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
       
        ctx.close();
    }
}
