package com.controller.websocket.stomp.applicationdestination;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class AppController {
	
	@MessageMapping("/java/publish")
	@SendTo("/topic/java")
	public String handleMessage(@Payload Chat chat, SimpMessageHeaderAccessor headerAccessor)
	{
		return headerAccessor.getSessionAttributes().get("username")+ ":" +  chat.getMessage();
	}
	
	@MessageMapping("/java/join")
	@SendTo("/topic/java")
	public String join(@Payload Chat chat, SimpMessageHeaderAccessor headerAccessor)
	{
		headerAccessor.getSessionAttributes().put("username", chat.getUserName());
		return chat.getUserName() + " joined the chat";
	}
	
}
 