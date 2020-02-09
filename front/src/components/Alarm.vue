<template>
  <div>
    <notifications group="foo" />
    <div id="main-content" class="container">
      <div class="row">
        <div class="col-md-6">
          <form class="form-inline">
            <div class="form-group">
              <label for="connect">WebSocket connection: solo cast</label>
              <button
                id="connect"
                class="btn btn-default"
                type="submit"
                :disabled="soloconnected == true"
                @click.prevent="soloconnect"
              >solo_Connect</button>
              <button
                id="disconnect"
                class="btn btn-default"
                type="submit"
                :disabled="soloconnected == false"
                @click.prevent="disconnect"
              >Disconnect</button>
            </div>
          </form>
        </div>
        <div class="col-md-6">
          <form class="form-inline">
            <div class="form-group">
              <label for="name">solo cast</label>
              <input
                type="text"
                id="name"
                class="form-control"
                v-model="solo_send_message"
                placeholder="Your name here..."
              />
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
      isfirst: true,
      timerID: 0,
      latest_alarm_id: 0,
      new_alarm_id: 0,
      solo_received_messages: [],
      solo_send_message: null,
      soloconnected: false
    };
  },
  methods: {
    alarm() {
      this.timerID = setInterval(this.solosend, 2000);
    },
    solosend() {
      console.log("Send message:" + this.solo_send_message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { memberId: this.solo_send_message };
        console.log(JSON.stringify(msg));
        this.stompClient.send("/app/info", JSON.stringify(msg), {});
      }
    },
    soloconnect() {
      this.socket = new SockJS("http://localhost:8081/gs-guide-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        frame => {
          this.soloconnected = true;
          console.log(frame);
          this.stompClient.subscribe("/user/queue/info", tick => {
            console.log(JSON.parse(tick.body).idAlarm);
            if (this.latest_alarm_id != JSON.parse(tick.body).idAlarm) {
              if (!this.isfirst) {
                this.$notify({
                  group: "foo",
                  title: "Important message",
                  text:
                    JSON.parse(tick.body).memberColler +
                    "님에게서 알림이 도착했어요~"
                });
                this.solo_received_messages.push(JSON.parse(tick.body));
              }
              this.isfirst = false;
            }
            this.latest_alarm_id = JSON.parse(tick.body).idAlarm;
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
      clearInterval(this.timerID);
      this.soloconnected = false;
    },
    tickleConnection() {
      this.soloconnected ? this.disconnect() : this.connect();
    }
  },
  mounted() {
    //this.connect();
  }
};
</script>
<style scoped="">
</style>