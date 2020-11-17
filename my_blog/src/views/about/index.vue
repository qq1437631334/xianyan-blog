<template>
  <div>
    <div class="blog-bg">
      <div class="blog-title">{{ about.aboutTitle }}</div>
      <div class="blog-item">
        <div class="blog-time">发布时间：{{ about.createdTime }}</div>
        <div class="blog-meta">
          <a-icon type="eye" /> {{ about.aboutRead }} 阅读
        </div>
      </div>
      <div class="blog-content" v-html="about.aboutContent" />
    </div>
  </div>
</template>

<script>
import aboutApi from '@/api/about'
export default {
  data: function() {
    return {
      about: {}
    }
  },
  created() {
    this.getAbout()
  },
  methods: {
    getAbout() {
      aboutApi.read().then(res => {
        this.about = res.data
      })
    }
  }
}
</script>

<style scoped>

.blog-bg {
  background: #fff;
  padding: 40px 25px;
}

.blog-title {
  text-align: center;
  font-size: 24px;
  font-weight: bold;
  line-height: 70px;
}

.blog-item {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-bottom: 30px;
  margin-top: 10px;
}

.blog-action {
  width: 300px;
  margin: auto;
  display: flex;
  flex-direction: row;
  justify-content: space-evenly;
  font-size: 24px;
}
</style>
