<template>
  <div>
    <div id="main-content" class="container">
      <div class="row">
        <div class="col-md-6">
          <form class="form-inline">
            <div class="form-group">
              <label for="connect">WebSocket connection: broad cast </label>
              <button id="connect" class="btn btn-default" type="submit" :disabled="connected == true" @click.prevent="connect">Connect</button>
              <button id="disconnect" class="btn btn-default" type="submit" :disabled="connected == false" @click.prevent="disconnect">Disconnect
              </button>
            </div>
          </form>
        </div>
        <div class="col-md-6">
          <form class="form-inline">
            <div class="form-group">
              <label for="name">broad cast</label>
              <input type="text" id="name" class="form-control" v-model="send_message" placeholder="Your name here...">
            </div>
            <button id="send" class="btn btn-default" type="submit" @click.prevent="send">Send</button>
          </form>
        </div>
      </div>

      <div class="row">
        <div class="col-md-6">
          <form class="form-inline">
            <div class="form-group">
              <label for="connect">WebSocket connection: solo cast</label>
              <button id="connect" class="btn btn-default" type="submit" :disabled="soloconnected == true" @click.prevent="soloconnect">solo_Connect</button>
              <button id="disconnect" class="btn btn-default" type="submit" :disabled="soloconnected == false" @click.prevent="disconnect">Disconnect
              </button>
            </div>
          </form>
        </div>
        <div class="col-md-6">
          <form class="form-inline">
            <div class="form-group">
              <label for="name">solo cast</label>
              <input type="text" id="name" class="form-control" v-model="solo_send_message" placeholder="Your name here...">
            </div>
            <button id="send" class="btn btn-default" type="submit" @click.prevent="alarm">solo_Send</button>
          </form>
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <table id="conversation" class="table table-striped">
            <thead>
              <tr>
                <th>Greetings</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in received_messages" :key="item">
                <td>{{ item }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <table id="conversation" class="table table-striped">
            <thead>
              <tr>
                <th>solo Greetings</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in solo_received_messages" :key="item">
                <td>{{ item }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
export default {
  name: "websocketdemo",
  data() {
    return {
      received_messages: [],
      send_message: null,
      connected: false,
      solo_received_messages: [],
      solo_send_message: null,
      soloconnected: false
    };
  },
  methods: {
    alarm(){
      setInterval(this.solosend,2000);
    },
    send() {
      console.log("Send message:" + this.send_message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { name: this.send_message };
        console.log(JSON.stringify(msg));
        this.stompClient.send("/app/hello", JSON.stringify(msg), {});
      }
    },
    solosend() {
      //console.log("Send message:" + this.solo_send_message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { name: this.solo_send_message };
        //console.log(JSON.stringify(msg));
        this.stompClient.send("/app/info", JSON.stringify(msg), {});
      }
    },
    connect() {
      this.socket = new SockJS("http://localhost:8080/gs-guide-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        frame => {
          this.connected = true;
          console.log(frame);
          this.stompClient.subscribe("/topic/greetings", tick => {
            console.log(tick);
            this.received_messages.push(JSON.parse(tick.body).content);
          });
        },
        error => {
          console.log(error);
          this.connected = false;
        }
      );
    },
    soloconnect() {
      this.socket = new SockJS("http://localhost:8080/gs-guide-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        frame => {
          this.soloconnected = true;
          console.log(frame);
          this.stompClient.subscribe("/user/queue/info", tick => {
            console.log(tick);
            this.solo_received_messages.push(JSON.parse(tick.body).content);
          });
        },
        error => {
          console.log(error);
          this.soloconnected = false;
        }
      );
    },
    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
      this.connected = false;
    },
    tickleConnection() {
      this.connected ? this.disconnect() : this.connect();
    }
  },
  mounted() {
    // this.connect();
  }
};
</script>
<style scoped="">
</style>