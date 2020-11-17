<template>
  <div class="login-background">
    <div class="login-container">
      <div class="login-title">欢迎进入闲言博客</div>
      <a-menu v-model="current" mode="horizontal" @select="changeTab">
        <a-menu-item key="login">登录</a-menu-item>
        <a-menu-item key="register">注册</a-menu-item>
      </a-menu>
      <div class="content">
        <div v-show="login" class="login">
          <a-form :form="loginForm" @submit="loginSubmit">
            <a-form-item>
              <a-input
                v-decorator="[
                  'username',
                  { rules: [{ required: true, message: '请输入账号!' }] }
                ]"
                placeholder="请输入账号"
              />
            </a-form-item>
            <a-form-item>
              <a-input
                v-decorator="[
                  'password',
                  { rules: [{ required: true, message: '请输入密码!' }] }
                ]"
                placeholder="请输入密码"
                type="password"
              />
            </a-form-item>
            <a-form-item>
              <a-button type="primary" html-type="submit" block>登录</a-button>
            </a-form-item>
          </a-form>
        </div>
        <div v-show="register" class="register">
          <a-form :form="registerForm" @submit="registerSubmit">
            <a-form-item>
              <a-input
                v-decorator="[
                  'username',
                  { rules: [{ required: true, message: '请输入账号!' }] }
                ]"
                placeholder="请输入账号"
              />
            </a-form-item>
            <a-form-item>
              <a-input
                v-decorator="[
                  'password',
                  { rules: [{ required: true, message: '请输入密码!' }] }
                ]"
                placeholder="请输入密码"
                type="password"
              />
            </a-form-item>
            <a-form-item>
              <a-input
                v-decorator="[
                  'nickname',
                  { rules: [{ required: true, message: '请输入昵称!' }] }
                ]"
                placeholder="请输入昵称"
              />
            </a-form-item>
            <a-form-item>
              <a-input
                v-decorator="[
                  'userEmail',
                  { rules: [{ required: true, message: '请输入邮箱!' }] }
                ]"
                placeholder="请输入邮箱"
              />
            </a-form-item>
            <a-form-item>
              <a-radio-group v-decorator="['sex']">
                <a-radio value="1">
                  男
                </a-radio>
                <a-radio value="2">
                  女
                </a-radio>
              </a-radio-group>
            </a-form-item>
            <a-form-item>
              <a-button type="primary" html-type="submit" block>注册</a-button>
            </a-form-item>
          </a-form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import userApi from '@/api/myUser'
import md5 from 'js-md5'
import { setToken } from '@/utils/auth'
export default {
  data() {
    return {
      current: ['login'],
      login: true,
      loginForm: this.$form.createForm(this),
      registerForm: this.$form.createForm(this),
      register: false
    }
  },
  methods: {
    changeTab(e) {
      const key = e.key
      if (key === 'login') {
        this.login = true
        this.register = false
      }
      if (key === 'register') {
        this.register = true
        this.login = false
      }
    },
    loginSubmit(e) {
      e.preventDefault()
      // eslint-disable-next-line handle-callback-err
      this.loginForm.validateFields((err, values) => {
        values.password = md5(values.password)
        userApi.login(values).then(res => {
          setToken(res.data.token)
          this.$store.commit('SET_USER_INFO', res.data.userInfo)
          this.$message.info(res.msg)
          this.$router.back()
        })
      })
    },
    registerSubmit(e) {
      e.preventDefault()
      // eslint-disable-next-line handle-callback-err
      this.registerForm.validateFields((err, values) => {
        values.password = md5(values.password)
        userApi.register(values).then(res => {
          this.$message.info(res.msg)
          location.reload()
        })
      })
    }
  }
}
</script>
<style scoped>
.login-background {
  width: 100%;
  height: 100%;
  position: fixed;
  background-image: url("http://808km.top/blog/myblog/login-back.jpg");
}

.login-container {
  background-color: #fff;
  width: 450px;
  min-height: 450px;
  margin: 150px auto;
  border: 1px solid #e5e5e5;
  padding: 50px 50px 0 50px;
  border-radius: 5px;
}

.login-title {
  font-size: 24px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 20px;
}

.ant-menu-item {
  width: 50%;
  text-align: center;
}

.content {
  margin-top: 20px;
}
</style>
