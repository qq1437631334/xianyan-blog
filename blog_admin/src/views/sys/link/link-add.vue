<template>
  <div>
    <el-form :model="link" label-width="80px">
      <el-form-item label="链接名">
        <el-input v-model="link.linkName" />
      </el-form-item>
      <el-form-item label="链接地址">
        <el-input v-model="link.linkUrl" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addLink()">添加</el-button>
        <el-button @click="closeWindow">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import linkApi from '@/api/link'
export default {
  data: function() {
    return {
      link: {}
    }
  },
  methods: {
    addLink() {
      linkApi.save(this.link).then(res => {
        this.$message.success(res.msg)
        this.closeWindow()
        this.$emit('getLinkList')
      })
    },
    closeWindow() {
      this.$emit('closeAddWindow')
    }
  }
}
</script>
