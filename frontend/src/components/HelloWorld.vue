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
    // WebSocketサーバーへの接続を開始します。
    // wsにnew WebSocketを使って新しいWebSocketインスタンスを割り当て、サーバーのURL（この場合はws://localhost:8081/chat）を指定します。
    // イベントリスナー(onopen, onmessage, onclose, onerror)を設定して、接続の開始、メッセージの受信、接続の終了、エラーの発生時の動作を定義します。
    connect() {
      this.ws = new WebSocket("ws://localhost:8081/chat");
      // WebSocket接続が開かれたときに呼ばれる。
      this.ws.onopen = () => {
        this.connected = true;
        console.log("WebSocket connection opened");
      };
      // メッセージが受信されたときに呼ばれる。
      this.ws.onmessage = (event) => {
        this.messages.push(event.data);
      };
      // WebSocket接続が閉じたときに呼ばれる。
      this.ws.onclose = () => {
        this.connected = false;
        console.log("WebSocket connection closed");
      };
      // エラーが発生したときに呼ばれる。
      this.ws.onerror = (error) => {
        console.error("WebSocket error", error);
      };
    },
    disconnect() {
      if (this.ws) {
        this.ws.close();
      }
    },
    // ユーザーが入力したメッセージをWebSocketサーバーに送信します。
    // 入力されたメッセージが空でないこと、WebSocket接続が開いていること（WebSocket.OPEN）を確認した後、
    // ユーザー名とメッセージを含むオブジェクトをJSON形式の文字列に変換してサーバーに送信します。メッセージを送信した後、newMessageを空にして入力フィールドをクリアします。
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
