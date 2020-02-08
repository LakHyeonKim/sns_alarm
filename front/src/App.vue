<template>
  <div id="app">
    <websocket-greetings></websocket-greetings>
  </div>
</template>

<script>
import WebsocketGreetings from "@/components/WebsocketGreetings.vue"


export default {
  name: 'App',
  components:{
    WebsocketGreetings
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
