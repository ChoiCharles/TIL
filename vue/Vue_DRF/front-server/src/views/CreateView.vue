<!-- views/CreateView.vue -->

<template>
  <div>
    <h1>게시글 작성</h1>
    <form @submit.prevent="createArticles">
      <label for="title">제목 : </label>
      <input type="text" id="title" v-model.trim="title"><br>
      <label for="content">내용 : </label>
      <textarea id="content" cols="30" rows="10" v-model="content"></textarea><br>
      <input type="submit" id="submit">
    </form>
  </div>
</template>

<script>
import axios from 'axios'
const API_URL = 'http://127.0.0.1:8000'

export default {
  name: 'CreateView',
  data() {
    return {
      title : null,
      content : null,
    }
  },
  methods: {
    createArticles() {
      const title = this.title
      const content = this.content
      if (!title) {
        alert('제목을 입력해 주세요')
        return
      } else if (!content) {
        alert('내용을 입력해 주세요')
        return
      }
      axios({
        method: 'post',
        url: `${API_URL}/api/v1/articles/`,
        data: {title, content},
      })
      .then(() => {
        this.$router.push({name: 'ArticleView'})
      })
      .catch((err) => {
        console.log(err)
      })
    }
  }
}
</script>

<style>

</style>
