<template>
  <div>
    <el-form :model="user" label-width="80px">
      <el-form-item label="用户名">
        <el-input v-model="user.username" />
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="user.name" />
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="user.sex">
          <el-radio :label="1">男</el-radio>
          <el-radio :label="2">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="头像">
        <el-upload
          class="avatar-uploader"
          :action="uploadUrl"
          :show-file-list="false"
          :on-success="uploadSuccess"
          :headers="headers"
        >
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon" />
        </el-upload>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="user.nickname" />
      </el-form-item>
      <el-form-item label="电子邮箱">
        <el-input v-model="user.userEmail" />
      </el-form-item>
      <el-form-item>
        <el-button user="primary" @click="updateuser()">更新</el-button>
        <el-button @click="closeWindow()">取消</el-button>
      </el-form-item></el-form>

  </div>
</template>

<script>
import userApi from '@/api/myUser'
import { getToken } from '@/utils/auth'
export default {
  props: {
    user: {
      type: Object,
      default: null
    }
  },
  data: function() {
    return {
      imageUrl: this.user.header, // 上传图片回显
      headers: { // 上传文件的请求头
        Authorization: getToken()
      },
      uploadUrl: process.env.VUE_APP_UPLOAD_URL // 上传图片路径
    }
  },
  watch: {
    'user.userImage': function(newVal, oldVal) {
      this.imageUrl = newVal
    }
  },
  methods: {
    updateuser() {
      userApi.update(this.user).then(res => {
        this.$message.success(res.msg)
        this.closeWindow()
        this.$emit('getUserList')
      })
    },
    closeWindow() {
      this.$emit('closeUpdateWindow')
      this.imageUrl = null
    },
    uploadSuccess(res, file) {
      this.$message.success(res.msg)
      this.imageUrl = res.data
      this.user.userImage = res.data
    }
  }
}
</script>
