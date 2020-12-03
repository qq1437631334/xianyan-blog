<template>
  <div>
    <div class="blog-bg">
      <div class="blog-title">{{ blog.blogTitle }}</div>
      <div class="blog-item">
        <div class="blog-time">发布时间：{{ blog.createdTime }}</div>
        <div class="blog-meta">
          <a-icon type="eye" /> {{ blog.blogRead }}
          <a-icon type="heart" /> {{ blog.blogCollection }}
          <a-icon type="like" /> {{ blog.blogGoods }}
        </div>
      </div>
      <div class="markdown-body" v-html="blog.blogContent" />
      <div v-if="userInfo.username" class="blog-action">
        <a class="blog-good" href="javascript:void(0);" :class="isGood ? 'blog-good meta-active' : 'blog-good'" @click="good">
          <a-icon type="like" /> 点赞
        </a>
        <a class="blog-collection" href="javascript:void(0);" :class="isCollection ? 'blog-collection meta-active' : 'blog-collection'" @click="collection">
          <a-icon type="heart" /> 收藏
        </a>
      </div>
    </div>

    <div class="user-comment">
      <a-textarea v-model="content" placeholder="请输入内容，不超过300字" :rows="4" />
      <div class="comment-button">
        <a-button type="primary" @click="save">发表评论</a-button>
        <div v-show="countShow" class="content-count">
          还能输入 {{ commentContentCount }} 个字符
        </div>
      </div>
    </div>

    <div v-for="item in commentList" :key="item.id" class="comment-bg">
      <div class="comment-header">
        <img :src="item.commentUser.header" class="header-img">
      </div>
      <div class="comment-container">
        <div class="comment-nickname">{{ item.commentUser.nickname }}</div>
        <div class="comment-time">发表于 {{ item.createdTime }}</div>
        <div class="comment-content">{{ item.commentContent }}</div>
      </div>
      <a :class="item.good? 'blog-good meta-active' : 'blog-good'" @click="commentGood(item)"><a-icon type="like" /> {{ item.commentGood }}</a>
    </div>
  </div>
</template>

<script>
import blogApi from '@/api/blog'
import store from '@/store/index'
import commentApi from '@/api/comment'
import '../../style/github.min.css'
export default {
  data() {
    return {
      // 评论内容
      content: '',
      countShow: false, // 控制是否显示字符个数提示
      commentContentCount: 300, // 显示还能输入的字符数量
      id: 0,
      blog: {},
      userInfo: store.getters.getUserInfo,
      commentList: [], // 评论
      comment: {},
      isGood: false,
      isCollection: false
    }
  },
  watch: {
    'content': function(newVal, oldVal) {
      if (this.content.length > 300) {
        this.content = this.content.substring(0, 300)
      }
      if (this.content.length > 0) {
        this.countShow = true
      } else {
        this.countShow = false
      }
      const newValLength = newVal ? newVal.length : 0
      const oldValLength = oldVal ? oldVal.length : 0
      this.commentContentCount = this.commentContentCount - (newValLength - oldValLength)
    },
    '$route.params.id': function(newVal, oldVal) {
      this.id = newVal
      this.read()
      this.getCommentList()
      this.getIsGood()
    }
  },
  created() {
    this.id = this.$route.params.id
    this.read()
    this.getCommentList()
    this.getIsGood()
  },
  methods: {
    read() {
      blogApi.read(this.id).then(res => {
        this.blog = res.data
      })
    },
    getBlog() {
      blogApi.getById(this.id).then(res => {
        this.blog = res.data
        this.getCommentList()
        this.getIsGood()
      })
    },
    good() {
      if (this.userInfo.username) {
        if (this.isGood === false) {
          const obj = {
            blogId: this.id
          }
          blogApi.good(obj).then(res => {
            this.$message.info(res.msg)
            this.isGood = true
            this.getBlog()
          })
        } else {
          this.$message.error('您已点赞，请勿重复点赞！')
        }
      } else {
        this.$router.push('/login')
        this.$message.info('请先登录再点赞！')
      }
    },
    getIsGood() {
      if (this.userInfo.username) {
        blogApi.getIsGood(this.id).then(res => {
          this.isGood = res.data
        })
      }
    },
    commentGood(comment) {
      if (this.userInfo.username) {
        if (comment.good === false) {
          commentApi.commentGood(this.id, comment.id).then(res => {
            this.$message.info(res.msg)
            this.getBlog()
          })
        } else {
          this.$message.error('请不要重复点赞！')
        }
      } else {
        this.$router.push('/login')
        this.$message.info('请先登录再点赞！')
      }
    },
    collection() {
      if (this.userInfo.username) {
        if (this.isCollection === false) {
          const obj = {
            blogId: this.id
          }
          blogApi.collection(obj).then(res => {
            this.$message.info(res.msg)
            this.isCollection = true
            this.getBlog()
          })
        } else {
          this.$message.error('您已收藏，请勿重复收藏！')
        }
      } else {
        this.$router.push('/login')
        this.$message.info('请先登录再收藏！')
      }
    },
    getIsCollection() {
      blogApi.getIsCollection(this.id).then(res => {
        this.isCollection = res.data
      })
    },
    getCommentList() {
      commentApi.getByBlogId(this.id).then(res => {
        this.commentList = res.data
      })
    },
    save() {
      const self = this
      this.content = this.content.trim()
      if (!this.userInfo.username) {
        this.$router.push('/login')
        this.$message.info('请先登录再评论！')
      } else {
        if (self.content.length <= 0 || self.content.trim() === '') {
          self.$message.error('评论不能为空！')
        } else {
          self.comment.commentContent = self.content
          self.comment.commentBlogId = self.id
          commentApi.save(self.comment).then(res => {
            self.$message.info(res.msg)
            self.getCommentList()
            self.content = ''
          })
        }
      }
    }
  }
}
</script>

<style scoped>
.comment-button {
  margin: 15px;
  display: flex;
  flex-direction: row-reverse;
  align-items: center;
}

.content-count {
  margin-right: 15px;
}

.user-comment {
  margin-top: 15px;
  background-color: #fff;
}
.comment-bg {
  background-color: #fff;
  margin-top: 15px;
  padding: 10px 25px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.comment-container {
  width: 580px;
  display: flex;
  flex-direction: column;
}

.comment-time {
  font-size: 12px;
  color: #9c9ea8;
}

.comment-nickname {
  font-weight: bold;
}

.header-img {
  width: 100px;
  height: 100px;
  border: 5px solid #e5e5e5;
  border-radius: 50%;
}

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

.blog-good {
  color: #595959;
}

.blog-good:hover {
  color: #595959;
}

.comment-good {
  color: #595959;
}

.comment-good:hover {
  color: #595959;
}

.blog-collection {
  color: #595959;
}

.blog-collection:hover {
  color: #595959;
}

.meta-active {
  /* 标识当前是否已点赞，是否已收藏 */
  color: red;
}

.meta-active:hover {
  /* 标识当前是否已点赞，是否已收藏 */
  color: red !important;
}

</style>
