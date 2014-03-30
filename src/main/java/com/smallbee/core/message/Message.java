package com.smallbee.core.message;

import java.util.ArrayList;
import java.util.Collection;

import com.smallbee.core.netty.session.ISession;

/**
 * 抽象的消息类
 * 
 * @author will
 *
 */
public abstract class Message extends TransportObject implements IMessage{
	private int id;
	private long sendRoleId;
	private Collection<Long> receiveRoles = new ArrayList<Long>();
	private ISession session;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getSendRoleId() {
		return sendRoleId;
	}
	public void setSendRoleId(long sendRoleId) {
		this.sendRoleId = sendRoleId;
	}
	public Collection<Long> getReceiveRoles() {
		return receiveRoles;
	}
	public void setReceiveRoles(Collection<Long> receiveRoles) {
		this.receiveRoles = receiveRoles;
	}
	public ISession getSession() {
		return session;
	}
	public void setSession(ISession session) {
		this.session = session;
	}
}
