<template>
  <div id="app">
    <alarm></alarm>
  </div>
</template>

<script>
import Alarm from "@/components/Alarm.vue"


export default {
  name: 'App',
  components:{
    Alarm
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
