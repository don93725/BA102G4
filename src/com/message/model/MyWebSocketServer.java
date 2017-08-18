package com.message.model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import net.sf.json.JSONObject;

import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

@ServerEndpoint("/MyWebSocketServer/{name}")
public class MyWebSocketServer {

	private static final Map<String,Session> connectedSessions = Collections.synchronizedMap(new HashMap<String,Session>());

	@OnOpen
	public void onOpen(@PathParam("name") String name, Session userSession) throws IOException {
		connectedSessions.put(name,userSession);
		String text = String.format("Session ID = %s, connected; name = %s", 
				userSession.getId(), name);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		JSONObject json = JSONObject.fromObject(message);
		System.out.println("-------------------------");
		System.out.println(message);
		if (json != null) {
			String type = json.getString("type");	
			if("textMsg".equals(type)){
				Gson gson = new Gson();
				TempMsg msg = gson.fromJson(message, TempMsg.class);
				Session rcvSession = connectedSessions.get(msg.rcv_no);
				MessageService messageService = new MessageService();
				Message m = messageService.add(msg.getRcv_no(), msg.getPost_no(), msg.getMsg_ctx());
				SimpleDateFormat sdfor = new SimpleDateFormat("HH:mm");
				m.setDate(sdfor.format(m.getSend_time()));
				sdfor = new SimpleDateFormat("yyyy-MM-dd");
				m.setDay(sdfor.format(m.getSend_time()));
				message = gson.toJson(m);				
				message= "{ \"type\":\"text\",\"obj\":"+message+"}";
				if(userSession.isOpen()){
					userSession.getAsyncRemote().sendText(message);			
				}
				if(rcvSession!=null){
					if(rcvSession.isOpen()){
						rcvSession.getAsyncRemote().sendText(message);
					}			
				}				
			}
			
			if("msg".equals(type)){
				String rcv_no = json.getString("rcv_no");
				String data = json.getString("data");
				data= "{ \"type\":\"phone\",\"obj\":"+data+"}";
				Session rcvSession = connectedSessions.get(rcv_no);					
				if(rcvSession!=null&&rcvSession.isOpen()){
						rcvSession.getAsyncRemote().sendText(data);
				}
				
			}
			if("phoneCall".equals(type)){
				String rcv_no = json.getString("rcv_no");
				String post_no = json.getString("post_no");
				String nickname = json.getString("nickname");
				String mem_rank = json.getString("mem_rank");
				String data= "{ \"type\":\"call\",\"obj\": {\"rcv_no\":"+post_no+",\"nickname\":\""+nickname+"\",\"mem_rank\":\""+mem_rank+"\"}}";
				Session rcvSession = connectedSessions.get(rcv_no);					
				if(rcvSession!=null&&rcvSession.isOpen()){
					rcvSession.getAsyncRemote().sendText(data);
				}else{
					data = "{ \"type\":\"stopCall\"}";
					Session postSession = connectedSessions.get(post_no);
					
					postSession.getAsyncRemote().sendText(data);							
				}
				
			}
			if("stopCall".equals(type)){
				String rcv_no = json.getString("rcv_no");
				
				String data = "{ \"type\":\"stopCall\"}";
				Session rcvSession = connectedSessions.get(rcv_no);	
				if(rcvSession!=null&&rcvSession.isOpen()){
					rcvSession.getAsyncRemote().sendText(data);
				}
				
				
			}
			if("stopWait".equals(type)){
				String rcv_no = json.getString("rcv_no");
				String nickname = json.getString("user_nickname");
				String data = "{ \"type\":\"stopWait\",\"obj\": {\"nickname\":\""+nickname+"\"}}";
				Session rcvSession = connectedSessions.get(rcv_no);	
				if(rcvSession!=null&&rcvSession.isOpen()){
					rcvSession.getAsyncRemote().sendText(data);
				}
				
				
			}
		}	
		
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
	}
}
