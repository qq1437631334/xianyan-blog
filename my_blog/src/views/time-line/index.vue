<template>
  <div class="time-line">
    <div v-for="item in timeLine" :key="item.month" class="timeline">
      <div class="month">{{ item.month }}</div>
      <a-timeline>
        <a-timeline-item v-for="blog in item.list" :key="blog.blogId">
          <router-link :to="'/info/'+blog.blogId">{{ blog.createdTime }} {{ blog.blogTitle }}</router-link>
        </a-timeline-item>
      </a-timeline>
    </div>
  </div>
</template>

<script>
import blogApi from '@/api/blog'
export default {
  data: function() {
    return {
      timeLine: []
    }
  },
  created() {
    this.getTimeLine()
  },
  methods: {
    getTimeLine() {
      blogApi.getTimeLine().then(res => {
        this.timeLine = res.data
      })
    }
  }
}
</script>

<style scoped>
.time-line {
  background: #fff;
  padding: 40px 25px;
}
.month {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 15px;
}

a {
  color: #595959;
}
</style>
