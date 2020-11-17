<template>
  <div>
    <a-card title="推荐阅读" :head-style="headStyle" :body-style="bodyStyle">
      <router-link v-for="item in recommendReadList" :key="item.blogId" :to="'/info/'+item.blogId" class="recom-card">
        <div class="blog-title">{{ item.blogTitle }}</div>
        <div class="blog-type">分类：<a-tag color="green">{{ item.typeName }}</a-tag></div>
        <div class="blog-bottom">
          <div class="blog-time">{{ item.createdTime }}}</div>
          <div class="blog-meta">
            <a-icon type="eye" /> {{ item.blogRead }}
            <a-icon type="heart" /> {{ item.blogCollection }}
            <a-icon type="like" /> {{ item.blogGoods }}
            <a-icon type="message" /> {{ item.blogComment }}
          </div>
        </div>
      </router-link>
    </a-card>
  </div>
</template>

<script>
import blogApi from '@/api/blog'
export default {
  data() {
    return {
      headStyle: {
        fontSize: '18px',
        fontWeight: 'bold',
        lineHeight: '15px',
        borderLeft: '5px solid #409eff'
      },
      bodyStyle: {
        maxHeight: '530px',
        overflow: 'hidden'
      },
      recommendReadList: []
    }
  },
  created() {
    this.getRecommendedReadList()
  },
  methods: {
    getRecommendedReadList() {
      blogApi.getRecommendedReadList().then(res => {
        this.recommendReadList = res.data
      })
    }
  }
}
</script>

<style scoped>
.recomment-card {
  max-height: 500px;
  overflow: hidden;
}
.ant-card-bordered {
  border: none !important;
}

a {
  display: flex;
  flex-direction: column;
  color: #3e4149;
}

a:hover {
  color: #3e4149;
}

.blog-title {
  font-size: 16px;
  line-height: 40px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.blog-type {
  line-height: 40px;
}

.blog-bottom {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  font-size: 12px;
  color: #b8b8b8;
  margin-bottom: 2px;
}

</style>
