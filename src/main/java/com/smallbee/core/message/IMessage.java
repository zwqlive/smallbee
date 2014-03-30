package com.smallbee.core.message;

import java.util.Collection;

import com.smallbee.core.netty.session.ISession;

/**
 * 消息接口
 * 
 * @author will
 *
 */
public interface IMessage {
	/**
	 * 消息id
	 * @return
	 */
	int getId();
	/**
	 * 发送者id
	 * @return
	 */
	long getSendRoleId();
	/**
	 * 接收者id列表
	 * @return
	 */
	Collection<Long> getReceiveRoles();
	
	/**
	 * 消息所在的会话
	 * 
	 * @return
	 */
	ISession getSession();
}
