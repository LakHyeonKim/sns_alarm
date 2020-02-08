<template>
  <div id="app">
    <h1> alarm test </h1>
    <ul>
      <li v-for="(msg, index) in messages" :key="index">{{msg.message}}</li>
    </ul>
    <div>
      <input type="text" @keyup.enter="sendMessage()" v-model="message">
    </div>
  </div>
</template>

<script>
import io from 'socket.io-client';


export default {
  name: 'App',
  data(){
    return{
      messages: [],
      socket:io('localhost:4040')
    }
  },
  methods:{
    sendMessage(message){
      this.socket.emit('SEND_MESSAGE',{message})
    }
  },
  mounted(){
    this.socket.on('MESSAGE',(data)=>{
      this.messages = [...this.messages, data]
    })
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
