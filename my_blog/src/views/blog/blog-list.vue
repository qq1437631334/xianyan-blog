<template>
  <div>
    <div class="button-container">
      <div class="button-text">{{ name }}</div>
      <div class="button-menu">
        <a-menu v-model="current" mode="horizontal" @click="changeSort">
          <a-menu-item key="createdTime">
            最新
          </a-menu-item>
          <a-menu-item key="blogGoods">
            点赞最多
          </a-menu-item>
          <a-menu-item key="blogComment">
            评论最多
          </a-menu-item>
          <a-menu-item key="blogRead">
            阅读最多
          </a-menu-item>
          <a-menu-item key="blogCollection">
            收藏最多
          </a-menu-item>
        </a-menu>
      </div>
    </div>

    <div class="blog-list-container">
      <a-card v-for="item in blog.data" :key="item.blogId" :body-style="blogBodyStyle" class="blog-card">
        <div class="blog-main">
          <div v-if="item.blogImage" class="blog-image">
            <img :src="item.blogImage" class="blog-cover">
          </div>
          <router-link :to="'/info/'+item.blogId" :class="item.blogImage ? 'image-blog' : 'blog-container'">
            <div class="blog-title">{{ item.blogTitle }}</div>
            <div class="blog-comment">{{ item.blogRemark }}</div>
            <div class="blog-bottom">
              <div class="blog-type">
                分类：<a-tag color="green">{{ item.typeName }}</a-tag>
              </div>
              <div class="blog-meta">
                <div class="blog-time">{{ item.createdTime }}</div>
                <div class="blog-other">
                  <a-icon type="eye" /> {{ item.blogRead }}
                  <a-icon type="heart" /> {{ item.blogCollection }}
                  <a-icon type="like" /> {{ item.blogGoods }}
                  <a-icon type="message" /> {{ item.blogComment }}
                </div>
              </div>
            </div>
          </router-link>
        </div>
      </a-card>
    </div>

    <div class="blog-pagination">
      <a-pagination :show-total="total => `共 ${total} 条`" :page-size="parseInt(page.pageSize)" show-quick-jumper :default-current="1" :total="blog.totalCount" @change="pageChange" />
    </div>

  </div>
</template>

<script>
import blogApi from '@/api/blog'
export default {
  props: {
    name: {
      type: String,
      default: '博客'
    }
  },
  data() {
    return {
      current: ['createdTime'],
      blogBodyStyle: {
        padding: '18px'
      },
      page: {
        'pageNum': '1',
        'pageSize': '5',
        'orderByField': 'created_time',
        'orderByMode': 'desc',
        'typeId': ''
      },
      blog: []
    }
  },
  created() {
    if (this.$route.params.typeId) {
      this.page.typeId = this.$route.params.typeId
    }
    this.getPage()
  },
  methods: {
    pageChange(pageNumber) {
      this.page.pageNum = pageNumber
      this.getPage()
    },
    getPage() {
      blogApi.getPage(this.page).then(res => {
        this.blog = res.data
      })
    },
    changeSort(e) {
      this.page.orderByField = e.key
      this.getPage()
    }
  }
}
</script>

<style scoped>
.button-container {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding: 0 5px;
  align-items: center;
  line-height: 70px;
}

.button-text {
  font-size: 20px;
}

.ant-menu-horizontal {
  border-bottom: none !important;
}

.ant-menu {
  background: none !important;
}

a {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  color: #3e4149;
}

.blog-container {
  width: 100%;
  min-height: 130px;
}

.image-blog {
  width: 550px;
    min-height: 130px;
}

a:hover {
  color: #3e4149;
}

.blog-title {
  font-size: 16px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin-bottom: 10px;
}

.blog-comment {
  margin-bottom: 10px;
  font-size: 14px;
  color: #9c9ea8;
  line-height: 22px;
  max-height: 90px;
  overflow: hidden;
  text-overflow: ellipsis;
}

.blog-main {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.blog-bottom {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

.blog-meta {
  font-size: 12px;
  color: #9c9ea8;
}

.blog-image {
  width: 230px;
  margin-right: 20px;
}

.blog-cover {
    max-width: 200;
  max-height: 200px;
  width: 100%;
  border-radius: 5px;
}

.blog-card {
  margin-bottom: 15px;
  border-radius: 5px;
}
</style>
