<template>
  <div>
    <div class="info-container">
      <div class="header-container">
        <div class="update-img-container">
          <img :src="user.header" class="user-header">
          <a href="javascript:void(0);" @click="openHeaderUploadDialog">点我上传</a>
        </div>
        <div class="meta-container">
          <div class="user-id">ID：{{ user.userId }}</div>
          <div class="user-collection">收藏 {{ dataCount.collectionCount }} 评论 {{ dataCount.commentCount }}</div>
          <a-divider />
          <div class="info-form">
            <a-form :form="infoForm" @submit="infoSubmit">
              <a-form-item label="账号" :label-col="{ span: 5 }" :wrapper-col="{ span: 19 }">
                <a-input
                  v-decorator="[
                    'username',
                  ]"
                  disabled
                />
              </a-form-item>
              <a-form-item label="密码" :label-col="{ span: 5 }" :wrapper-col="{ span: 19 }">
                <a-button type="primary" @click="openUpdatePwdDialog">点我修改</a-button>
              </a-form-item>
              <a-form-item label="昵称" :label-col="{ span: 5 }" :wrapper-col="{ span: 19 }">
                <a-input
                  v-decorator="[
                    'nickname',
                    { rules: [{ required: true, message: '请输入昵称!' }] }
                  ]"
                  placeholder="请输入昵称"
                />
              </a-form-item>
              <a-form-item label="姓名" :label-col="{ span: 5 }" :wrapper-col="{ span: 19 }">
                <a-input
                  v-decorator="[
                    'name',
                    { rules: [{ required: true, message: '请输入姓名!' }] }
                  ]"
                  placeholder="请输入姓名"
                />
              </a-form-item>
              <a-form-item label="邮箱" :label-col="{ span: 5 }" :wrapper-col="{ span: 19 }">
                <a-input
                  v-decorator="[
                    'userEmail',
                    { rules: [{ required: true, message: '请输入邮箱!' }] }
                  ]"
                  placeholder="请输入邮箱"
                />
              </a-form-item>
              <a-form-item label="性别" :label-col="{ span: 5 }" :wrapper-col="{ span: 19 }">
                <a-radio-group v-decorator="['sex']">
                  <a-radio :value="1">男</a-radio>
                  <a-radio :value="2">女</a-radio>
                </a-radio-group>
              </a-form-item>
              <a-form-item>
                <a-button type="primary" html-type="submit" block>修改</a-button>
              </a-form-item>
            </a-form>
          </div>
        </div>
      </div>
    </div>

    <a-modal
      title="修改密码"
      :visible="visible"
      @ok="handleOk"
      @cancel="handleCancel"
    >
      <a-form :form="passwordForm">
        <a-form-item label="密码" :label-col="{ span: 5 }" :wrapper-col="{ span: 19 }">
          <a-input
            v-decorator="[
              'password',
              { rules: [{ required: true, message: '请输入密码!' }] }
            ]"
            type="password"
            placeholder="请输入密码"
          />
        </a-form-item>
      </a-form>
    </a-modal>

    <a-modal
      title="修改头像"
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
import { getToken } from '@/utils/auth'
export default {
  data() {
    return {
      infoForm: this.$form.createForm(this),
      passwordForm: this.$form.createForm(this),
      user: this.$store.getters.getUserInfo,
      dataCount: {},
      uploadUrl: process.env.VUE_APP_UPLOAD_URL, // 上传图片路径
      headers: { // 上传文件的请求头
        token: getToken()
      },
      uploadDialog: false, // 控制修改头像弹窗显示
      visible: false, // 控制修改密码弹窗展示
      header: null
    }
  },
  watch: {
    'user.header': function(newVal, oldVal) {
      this.header = newVal
    }
  },
  created() {
    this.getCommentAndCollectionCount()
  },
  mounted() {
    this.infoForm.setFieldsValue({ nickname: this.user.nickname })
    this.infoForm.setFieldsValue({ userEmail: this.user.userEmail })
    this.infoForm.setFieldsValue({ sex: this.user.sex })
    this.infoForm.setFieldsValue({ username: this.user.username })
  },
  methods: {
    infoSubmit(e) {
      e.preventDefault()
      // eslint-disable-next-line handle-callback-err
      this.infoForm.validateFields((err, values) => {
        // 在校验数据成功后才更新
        if (!err) {
          values.userId = this.user.userId
          userApi.update(values).then(res => {
            this.$message.info(res.msg)
            if (values.nickname) {
              this.user.nickname = values.nickname
            }
            if (values.userEmail) {
              this.user.userEmail = values.userEmail
            }
            if (values.sex) {
              this.user.sex = values.sex
            }
            this.$store.commit('SET_USER_INFO', this.user)
          })
        }
      })
    },
    handleOk(e) { // 提交
      // eslint-disable-next-line handle-callback-err
      this.passwordForm.validateFields((err, values) => {
        if (values.password) {
          values.userId = this.user.userId
          if (this.header != null) {
            values.header = this.header
          }
          values.version = this.user.version
          console.log(values)
          userApi.update(values).then(res => {
            this.$message.info(res.msg)
            this.user.version = this.user.version + 1
            this.user.password = ''
            this.header = null
          })
        }
      })
      this.visible = false
    },
    handleCancel(e) {
      this.visible = false
    },
    uploadCancel(e) {
      this.uploadDialog = false
    },
    openHeaderUploadDialog() {
      // 打开头像上传弹窗
      this.uploadDialog = true
    },
    handleChange(info) {
      // 文件上传触发
      const status = info.file.status
      if (status === 'done') {
        const res = info.file.response
        this.$message.success(res.msg)
        this.user.header = res.data
        userApi.update(this.user).then(res => {
          this.user.version = this.user.version + 1
          this.$store.commit('SET_USER_INFO', this.user)
        })
        this.uploadDialog = false
      }
    },
    getCommentAndCollectionCount() {
      userApi.getCommentAndCollectionCount().then(res => {
        this.dataCount = res.data
      })
    },
    openUpdatePwdDialog() {
      // 打开修改密码弹窗
      this.visible = true
    }
  }
}
</script>
<style scoped>
.user-header {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  border: 3px solid #e5e5e5;
}

.header-container {
  display: flex;
  flex-direction: row;
}

.meta-container {
  display: flex;
  flex-direction: column;
  margin-left: 20px;
}

.user-id {
  color: #b39999;
  font-size: 18px;
}

.user-collection {
  line-height: 40px;
  font-size: 16px;
}

.update-img-container {
  display: flex;
  flex-direction: column;
}

.update-img-container a {
  text-align: center;
  margin-top: 20px;
}
</style>
