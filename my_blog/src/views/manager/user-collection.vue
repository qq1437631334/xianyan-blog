<template>
  <div>
    <div v-for="item in collections.data" :key="item.id" class="collection-container">
      <router-link :to="'/info/'+item.blogId" class="collection-card">
        <div class="blog-title">{{ item.blog.blogTitle }}</div>
        <div class="blog-bottom">
          <div class="blog-time">{{ item.blog.createdTime }}</div>
          <div class="blog-meta">
            <a-icon type="eye" /> {{ item.blog.blogRead }}
            <a-icon type="heart" /> {{ item.blog.blogCollection }}
            <a-icon type="like" /> {{ item.blog.blogGoods }}
            <a-icon type="message" /> {{ item.blog.blogComment }}
          </div>
        </div>
      </router-link>
      <div class="button-container">
        <a-popconfirm title="确定要取消收藏这篇博客吗吗？" cancel-text="取消" ok-text="确定" @confirm="deleteById(item.id)">
          <a-button type="danger" class="" size="small">
            取消收藏
          </a-button>
          <a-icon slot="icon" type="question-circle-o" style="color: red" />
        </a-popconfirm>
      </div>
      <a-divider />
    </div>
    <div class="blog-pagination">
      <a-pagination :show-total="total => `共 ${total} 条`" :page-size="page.pageSize" show-quick-jumper :default-current="1" :total="collections.totalCount" @change="pageChange" />
    </div>
  </div>
</template>
<script>
import userApi from '@/api/myUser'
export default {
  data: function() {
    return {
      page: {
        pageNum: 1,
        pageSize: 5
      },
      collections: []
    }
  },
  created() {
    this.getCollectionPage()
  },
  methods: {
    pageChange(pageNumber) {
      this.page.pageNum = pageNumber
      this.getCollectionPage()
    },
    getCollectionPage() {
      userApi.getCollectionPage(this.page).then(res => {
        this.collections = res.data
      })
    },
    deleteById(id) {
      userApi.deleteById(id).then(res => {
        this.$message.info(res.msg)
        this.getCollectionPage()
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
