<template>
  <div>
    <el-form label-width="120px">
      <el-form-item label="请输入密码">
        <el-input v-model="password" show-password />
      </el-form-item>
      <el-form-item label="请再次输入密码">
        <el-input v-model="password2" show-password />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">修改</el-button>
        <el-button @click="close">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import userApi from '@/api/myUser'
import md5 from 'js-md5'
export default {
  props: {
    id: {
      type: Number,
      default: 0
    }
  },
  data: function() {
    return {
      password: '',
      password2: ''
    }
  },
  methods: {
    close() {
      this.$emit('closePasswordWindow')
    },
    onSubmit() {
      if (this.password === this.password2) {
        this.password = md5(this.password)
        this.password2 = this.password
        userApi.updatePassword(this.id, this.password).then(res => {
          this.$message.success(res.msg)
          this.$emit('getUserList')
          this.close()
        })
      } else {
        this.$message.error('两次输入的密码不同！')
      }
    }
  }
}
</script>
