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
      <div class="markdown-body" v-html="content" />
    </div>
  </div>
</template>

<script>
import aboutApi from '@/api/about'
import marked from 'marked'
import hljs from 'highlight.js'
import 'highlight.js/styles/atom-one-dark.css'
export default {
  data: function() {
    return {
      about: {},
      // 显示的内容
      content: ''
    }
  },
  created() {
    this.getAbout()
  },
  methods: {
    getAbout() {
      aboutApi.read().then(res => {
        this.about = res.data
        // 展现经过markdown渲染后的html
        marked.setOptions({
          renderer: new marked.Renderer(),
          highlight: function(code) {
            return hljs.highlightAuto(code).value
          },
          pedantic: false,
          gfm: true,
          tables: true,
          breaks: false,
          sanitize: false,
          smartLists: true,
          smartypants: false,
          xhtml: false
        })
        this.content = marked(res.data.aboutContent)
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
