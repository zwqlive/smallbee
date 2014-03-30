package com.smallbee.core.message;

import java.util.Collection;

import com.smallbee.core.netty.session.ISession;

/**
 * 消息接口
 * 
 * @author will
 *
 */
public interface IMessage extends ITransportable{
	/**
	 * 消息id
	 * @return
	 */
	int getId();
	/**
	 * 设置id
	 */
	void setId(int id);
	/**
	 * 发送者id
	 * @return
	 */
	long getSendRoleId();
	/**
	 * 设置发送角色id
	 * 
	 * @param sendRoleId
	 */
	void setSendRoleId(long sendRoleId);
	/**
	 * 接收者id列表
	 * @return
	 */
	Collection<Long> getReceiveRoles();
	/**
	 * 设置接收角色id列表
	 * @param receiveRoles
	 */
	void setReceiveRoles(Collection<Long> receiveRoles);
	
	/**
	 * 消息所在的会话
	 * 
	 * @return
	 */
	ISession getSession();
	/**
	 * 设置消息的会话
	 * 
	 * @param session
	 */
	void setSession(ISession session);
}
