<template>
  <div>
    <div class="menu-container">
      <a class="nav-logo" href="/">
        <img src="../../assets/img/logo.png" class="logo-img">
        <span class="logo-text">闲言</span>
      </a>
      <div class="nav-menu-search">
        <a-menu v-model="current" mode="horizontal">
          <a-menu-item key="1">
            <router-link to="/">
              <a-icon type="mail" />首页
            </router-link>
          </a-menu-item>
          <a-menu-item key="2">
            <router-link to="/type">
              <a-icon type="mail" />分类
            </router-link>
          </a-menu-item>
          <a-menu-item key="3">
            <router-link to="/timeLine">
              <a-icon type="mail" />时间轴
            </router-link>
          </a-menu-item>
          <a-menu-item key="4">
            <router-link to="/about">
              <a-icon type="mail" />关于闲言
            </router-link>
          </a-menu-item>
        </a-menu>
        <a-input-search
          v-model="blogTitle"
          placeholder="在这里能搜到奇怪的东西"
          style="width: 200px"
          @search="searchBlog"
        />
        <router-link v-if="!userInfo.username" to="/login" class="login">登录/注册</router-link>
        <div v-else class="login-header">
          <a-dropdown placement="bottomRight">
            <a class="ant-dropdown-link" @click="e => e.preventDefault()">
              <img :src="userInfo.header" class="login-header-img"> <a-icon type="down" />
            </a>
            <a-menu slot="overlay">
              <a-menu-item>
                <router-link to="/manager">个人信息</router-link>
              </a-menu-item>
              <a-menu-item>
                <a @click="logout">登出</a>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import store from '@/store/index'
export default {
  data() {
    return {
      current: ['1'],
      blogTitle: '' // 搜索关键字
    }
  },
  computed: {
    userInfo() {
      return store.getters.getUserInfo
    }
  },
  methods: {
    searchBlog() {
      if (this.blogTitle === '') {
        this.blogTitle = ' '
      }
      this.$router.push('/search/' + this.blogTitle)
    },
    logout() {
      this.$store.commit('SET_USER_INFO', {})
      this.$router.push('/login')
      this.$message.info('登出成功！')
    }
  }
}
</script>
<style scoped>
.menu-container {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 0 60px;
  line-height: 70px;
  background-color: #fff;
  margin-bottom: 10px;
}

.nav-logo {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.logo-img {
  width: 40px;
  height: 40px;
  margin-right: 10px;
}

.logo-text {
  font-size: 24px;
  color: #5f5f5f;
}

.nav-menu-search {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-right: 40px;
}

.ant-menu {
  font-size: 16px !important;
}

.ant-menu-horizontal {
  border-bottom: none !important;
}

.login {
  color: black;
  margin-left: 20px;
}

.login:hover {
  color: black;
}

.login-header {
  color: black;
  margin-left: 20px;
}

.login-header-img {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  border: 3px solid #e5e5e5;
}
.ant-dropdown-menu{
  margin-top:20px
}
</style>
