package com.yamadatadashidai.chatapp.chatapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplicationのアノテーションはエントリーポイント（起点）
// SpringApplication.run(ChatappApplication.class, args);行はSpring Bootフレームワークを起動し、アプリケーションを実行
@SpringBootApplication
public class ChatappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatappApplication.class, args);
	}

}
