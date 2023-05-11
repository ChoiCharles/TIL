<template>
  <div id="app">
    <h1>Cat Image</h1>
    <img :src="cat" alt="">
    <br>
    <input type="submit" value="Get Cat" @click="getCat">
  </div>
</template>

<script>
import axios from 'axios'
const API_URL = 'https://api.thecatapi.com/v1/images/search'

export default {
  name: 'App',
  data() {
    return {
      cat: null
    }
  },
  created(){
    this.getCat()
  },
  methods: {
    getCat(){
      axios({
        method: 'get',
        url: API_URL
      })
      .then((res) => {
        console.log(res.data[0].url)
        this.cat = res.data[0].url
      })
      .catch((err) => {
        console.log(err)
      })
    }
  }
}
</script>

<style>
/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
} */
</style>
