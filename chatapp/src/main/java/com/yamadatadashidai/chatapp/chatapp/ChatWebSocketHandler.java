package com.yamadatadashidai.chatapp.chatapp;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload(); // JSON形式のメッセージを受信
        // JSONパーサーを使って適切にパースするのがベストプラクティスです
        // 以下は単純化された例です
        String name = payload.substring(payload.indexOf("\"name\":\"") + 8, payload.indexOf("\","));
        String userMessage = payload.substring(payload.indexOf("\"message\":\"") + 11, payload.lastIndexOf("\""));
    
        System.out.println("Received message from " + name + ": " + userMessage);
    
        // 応答メッセージを構築
        String responsePayload = name + ": " + userMessage;
        session.sendMessage(new TextMessage(responsePayload));
    }
    
}
