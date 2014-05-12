package com.smallbee.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.smallbee.core.netty.server.MessageRegistryService;
import com.smallbee.robot.command.ClientTestCommand;
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

	public static void main(String[] args) {
		registryMessage();

		IClient client = new Robot();
		client.connect("localhost", 8020);

		// 监听命令行输入
		while (true) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				String cmd = reader.readLine();
				if ("quit".equals(cmd)) {
					client.disconnect();
					break;
				}else if("testmsg".equals(cmd)){
					ClientTestCommand testCommand = new ClientTestCommand();
					testCommand.setContext(client.context());
					testCommand.execute();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
