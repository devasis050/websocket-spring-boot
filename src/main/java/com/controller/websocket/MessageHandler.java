package com.controller.websocket;

import java.io.IOException;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class MessageHandler implements WebSocketHandler {

	List<WebSocketSession> sessions;
	public MessageHandler() {}
	public MessageHandler(List<WebSocketSession> sessions) {
		this.sessions = sessions;
	}
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String msg = "Client added to chat:" + session.getId();
		System.out.println(msg);
		sessions.add(session);
		publish(msg);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		
		String msg = "Message from client: " + session.getId() +"::" +  ((TextMessage)message).getPayload();
		publish(msg);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		String msg = "Client closed:" + session.getId();
		sessions.removeIf(s -> session.getId().equals(s.getId()));
		publish(msg);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
	
	private void publish(String message)
	{
		TextMessage text = new TextMessage(message);
		for (WebSocketSession webSocketSession : sessions) {
			try {
				webSocketSession.sendMessage(text);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
