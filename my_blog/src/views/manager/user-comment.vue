<template>
  <div>
    <div v-for="item in comments.data" :key="item.id" class="collection-container">
      <router-link :to="'/info/' + item.commentBlogId" class="collection-card">
        <div class="blog-title">{{ item.commentBlog.blogTitle }}</div>
        <div class="blog-comment">{{ item.commentContent }}</div>
        <div class="blog-bottom">
          <div class="blog-time">{{ item.commentBlog.createdTime }}</div>
          <div class="blog-meta">
            <a-icon type="eye" /> {{ item.commentBlog.blogRead }}
            <a-icon type="heart" /> {{ item.commentBlog.blogCollection }}
            <a-icon type="like" /> {{ item.commentBlog.blogGoods }}
            <a-icon type="message" /> {{ item.commentBlog.blogComment }}
          </div>
        </div>
      </router-link>
      <div class="button-container">
        <a-popconfirm title="确定要删除该条评论吗？" cancel-text="取消" ok-text="删除" @confirm="deleteById(item.id)">
          <a-button type="danger" class="" size="small">
            删除评论
          </a-button>
          <a-icon slot="icon" type="question-circle-o" style="color: red" />
        </a-popconfirm>
      </div>
      <a-divider />
    </div>
    <div class="blog-pagination">
      <a-pagination :show-total="total => `共 ${total} 条`" :page-size="page.pageSize" show-quick-jumper :default-current="1" :total="comments.totalCount" @change="pageChange" />
    </div>
  </div>
</template>
<script>
import commentApi from '@/api/comment'
export default {
  data: function() {
    return {
      page: {
        pageNum: 1,
        pageSize: 5
      },
      comments: []
    }
  },
  created() {
    this.getPage()
  },
  methods: {
    pageChange(pageNumber) {
      this.page.pageNum = pageNumber
      this.getPage()
    },
    getPage() {
      commentApi.getPage(this.page).then(res => {
        this.comments = res.data
      })
    },
    deleteById(id) {
      commentApi.deleteById(id).then(res => {
        this.$message.info(res.msg)
        this.getPage()
      })
    }
  }
}
</script>
<style scoped>
a {
  display: flex;
  flex-direction: column;
  color: #3e4149;
  width: 560px;
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
.button-container{
  display: flex;
  flex-direction: row-reverse;
  justify-content: space-between;
  padding-top: 15px;
}
.ant-divider-horizontal{
  margin:15px
}
</style>
