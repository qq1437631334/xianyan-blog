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
              <div class="update-img-container">
                <img :src="header?header:defaultHeaderUrl" class="user-header"><br>
                <a href="javascript:void(0);" @click="openHeaderUploadDialog">点我上传</a>
                <a-input v-decorator="['header', { rules: [{ required: true, message: '请上传头像' }] }]" hidden />
              </div>
            </a-form-item>
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
    <a-modal
      title="上传头像"
      :visible="uploadDialog"
      @cancel="uploadCancel"
    >
      <a-upload-dragger name="file" :multiple="true" :action="uploadUrl" :headers="headers" @change="handleChange">
        <p class="ant-upload-drag-icon">
          <a-icon type="inbox" />
        </p>
        <p class="ant-upload-text">点击上传或拖拽到这里</p>
      </a-upload-dragger>
    </a-modal>
  </div>
</template>
<script>
import userApi from '@/api/myUser'
import { setToken, getToken } from '@/utils/auth'
export default {
  data() {
    return {
      current: ['login'],
      login: true,
      loginForm: this.$form.createForm(this),
      registerForm: this.$form.createForm(this),
      register: false,
      // 控制上传弹窗
      uploadDialog: false,
      // 上传图片路径
      uploadUrl: process.env.VUE_APP_UPLOAD_URL,
      // 上传文件的请求头
      headers: {
        token: getToken()
      },
      //  默认头像路径
      defaultHeaderUrl: 'http://808km.top/blog/myblog/static/img/78a381109dd1919a0bde420b62525b2df37ac73f17b1-rx7BWq_fw658.png',
      //  注册表单的header
      header: undefined
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
        userApi.login(values).then(res => {
          setToken(res.data.token)
          this.$store.commit('SET_USER_INFO', res.data.userInfo)
          console.log(res)
          this.$message.info(res.msg)
          this.$router.back()
        })
      })
    },
    registerSubmit(e) {
      e.preventDefault()
      // eslint-disable-next-line handle-callback-err
      this.registerForm.validateFields((err, values) => {
        //  必须表单校验通过才注册
        if (!err) {
          userApi.register(values).then(res => {
            this.$message.info(res.msg)
            location.reload()
          })
        }
      })
    },
    // 打开头像上传弹窗
    openHeaderUploadDialog() {
      this.uploadDialog = true
    },
    // 上传弹窗取消回调
    uploadCancel() {
      this.uploadDialog = false
    },
    // 文件上传触发
    handleChange(info) {
      const status = info.file.status
      if (status === 'done') {
        const res = info.file.response
        this.$message.success(res.msg)
        this.registerForm.setFieldsValue({
          'header': res.data
        })
        this.header = res.data
        this.uploadDialog = false
      }
    }
  }
}
</script>
<style scoped>
.login-background {
  width: 100%;
  height: 100%;
  position: fixed;
  background-image: url("http://808km.top/public/login-back.jpg");
  background-size: cover;
  overflow:auto;
}

.login-container {
  background-color: #fff;
  width: 30%;
  min-height: 30%;
  min-width: 200px;
  margin: 50px auto;
  border: 1px solid #e5e5e5;
  padding: 50px 50px 0 50px;
  border-radius: 5px;
  overflow:auto;
}
/* 设配手机端css */
@media screen and (max-width: 500px) {
 .login-container {
  background-color: #fff;
  width: 100%;
  min-height: 30%;
  border: 1px solid #e5e5e5;
  border-radius: 5px;
  overflow:auto;
}
}

/* 设配平板端css */
@media screen and (max-width: 1200px) {
 .login-container {
  background-color: #fff;
  width: 70%;
  min-height: 30%;
  border: 1px solid #e5e5e5;
  border-radius: 5px;
  overflow:auto;
}
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
  overflow:auto;
}

.user-header {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  border: 3px solid #e5e5e5;
}

.update-img-container {
  flex-direction: column;
  text-align: center;
}

/* 设置滚动条的样式 设置成纯黑色 */
::-webkit-scrollbar {
width:12px;
}
/* 滚动槽 */
::-webkit-scrollbar-track {
-webkit-box-shadow:inset006pxrgba(0,0,0,0.3);
border-radius:10px;
}
/* 滚动条滑块 */
::-webkit-scrollbar-thumb {
border-radius:10px;
background:rgba(255, 255, 255, 0.3);
-webkit-box-shadow:inset006pxrgba(0,0,0,0.5);
}
::-webkit-scrollbar-thumb:window-inactive {
background:rgba(0,0,0,0.1);
}
</style>

