package com.smallbee.core.netty.server;

import java.util.HashMap;
import java.util.Map;

import com.smallbee.core.message.IMessage;
import com.smallbee.core.message.Message;
import com.smallbee.core.netty.handler.IMessageHandler;

/**
 * 消息注册服务
 * 
 * @author will
 *
 */
public class MessageRegistryService {
	
	public static Map<Integer,Class<? extends Message>> messageClasses = new HashMap<Integer, Class<? extends Message>>();
	public static Map<Integer,Class<? extends IMessageHandler<? extends IMessage>>> handlerClasses = new HashMap<Integer, Class<? extends IMessageHandler<? extends IMessage>>>();
	public static void register(int messageId,Class<? extends Message> msgClass, Class<? extends IMessageHandler<? extends IMessage>>  handlerClass){
		messageClasses.put(messageId, msgClass);
		handlerClasses.put(messageId, handlerClass);
	}
	
	public static Message getMessage(int msgId) throws InstantiationException, IllegalAccessException{
		if(messageClasses.containsKey(msgId)){
			return messageClasses.get(msgId).newInstance();
		}else{
			return null;
		}
	}
	
	public static IMessageHandler<? extends IMessage> getHandler(int msgId) throws InstantiationException, IllegalAccessException{
		if(handlerClasses.containsKey(msgId)){
			return handlerClasses.get(msgId).newInstance();
		}else{
			return null;
		}
	}
}
