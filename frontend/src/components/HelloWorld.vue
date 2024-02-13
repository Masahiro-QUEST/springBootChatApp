<template>
  <div>
    <button @click="connect" :disabled="connected">Connect</button>
    <button @click="disconnect" :disabled="!connected">Disconnect</button>
    <div>
      <input type="text" v-model="name" placeholder="Your name" />
      <input
        type="text"
        v-model="newMessage"
        placeholder="Type a message"
        @keyup.enter="sendMessage"
      />
      <button @click="sendMessage" :disabled="!connected">Send</button>
    </div>
    <div>
      <p v-for="(message, index) in messages" :key="index">{{ message }}</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ws: null,
      connected: false,
      name: "",
      newMessage: "",
      messages: [],
    };
  },
  methods: {
    connect() {
      this.ws = new WebSocket("ws://localhost:8081/chat");
      this.ws.onopen = () => {
        this.connected = true;
        console.log("WebSocket connection opened");
      };
      this.ws.onmessage = (event) => {
        this.messages.push(event.data);
      };
      this.ws.onclose = () => {
        this.connected = false;
        console.log("WebSocket connection closed");
      };
      this.ws.onerror = (error) => {
        console.error("WebSocket error", error);
      };
    },
    disconnect() {
      if (this.ws) {
        this.ws.close();
      }
    },
    sendMessage() {
      if (
        this.ws &&
        this.ws.readyState === WebSocket.OPEN &&
        this.newMessage.trim()
      ) {
        const messageData = {
          name: this.name,
          message: this.newMessage,
        };
        this.ws.send(JSON.stringify(messageData)); // JSON形式の文字列に変換して送信
        this.newMessage = ""; // メッセージを送信後、入力フィールドをクリア
      }
    },
  },
};
</script>
