<template>
  <div>
    <el-form :model="admin" label-width="80px">
      <el-form-item label="用户名">
        <el-input v-model="admin.username" />
      </el-form-item>
      <el-form-item label="密码">
        <el-button type="text" @click="openPasswordWindow">修改密码</el-button>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="admin.name" />
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
      <el-form-item label="个性签名">
        <el-input v-model="admin.signature" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateadmin()">更新</el-button>
      </el-form-item>
    </el-form>
    <!-- 修改密码弹出框 -->
    <el-dialog title="修改密码" :visible.sync="dialogVisible" width="30%">
      <admin-update-password @closePasswordWindow="closePasswordWindow" />
    </el-dialog>
  </div>
</template>

<script>
import { getToken } from '@/utils/auth'
import adminApi from '@/api/admin'
import AdminUpdatePassword from './admin-update-password'
export default {
  components: {
    AdminUpdatePassword
  },
  data: function() {
    return {
      admin: {},
      headers: {
        // 上传文件的请求头
        Authorization: getToken()
      },
      uploadUrl: process.env.VUE_APP_UPLOAD_URL, // 上传图片路径
      imageUrl: null, // 上传图片回显
      dialogVisible: false
    }
  },
  watch: {
    'admin.header': function(newVal, oldVal) {
      this.admin.header = newVal
    }
  },
  created() {
    this.getAdmin()
  },
  methods: {
    getAdmin() {
      adminApi.getAdmin().then((res) => {
        this.admin = res.data
        this.imageUrl = this.admin.header
      })
    },
    uploadSuccess(res, file) {
      this.$message.success(res.msg)
      this.imageUrl = res.data
      this.admin.header = res.data
    },
    updateadmin() {
      adminApi.update(this.admin).then((res) => {
        this.$message.success(res.msg)
      })
    },
    openPasswordWindow() {
      this.dialogVisible = true
    },
    closePasswordWindow() {
      this.dialogVisible = false
    }
  }
}
</script>
