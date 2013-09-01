package com.will.smallbee.server.hander;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameServerSimpleHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LogManager.getLogger(
    		GameServerSimpleHandler.class.getName());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msgs) throws Exception {
        ctx.write(msgs);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        logger.log(Level.WARN, "Unexpected exception from downstream.", cause);
        ctx.close();
    }

}
