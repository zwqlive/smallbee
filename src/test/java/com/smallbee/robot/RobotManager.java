package com.smallbee.robot;

import com.smallbee.core.netty.server.MessageRegistryService;
import com.smallbee.robot.handler.ClientHandler;
import com.smallbee.robot.message.CSTestMessage;
import com.smallbee.robot.message.SCTestMessage;

/**
 * 机器人管理器
 * 
 * @author will
 * 
 */
public class RobotManager {
	
	private static void registryMessage() {
		MessageRegistryService.register(101101, CSTestMessage.class, null);
		MessageRegistryService.register(101201, SCTestMessage.class, ClientHandler.class);

	}
	
	public static void main(String[] args){
		registryMessage();
		
		IClient client = new Robot();
		client.connect("localhost", 8020);
	}
}
