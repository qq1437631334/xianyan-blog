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
        <el-button type="primary" @click="updateLink()">修改</el-button>
        <el-button @click="closeWinodw">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import linkApi from '@/api/link'
export default {
  props: {
    link: {
      type: Object,
      default: null
    }
  },
  methods: {
    updateLink() {
      linkApi.update(this.link).then(res => {
        this.$message.success(res.msg)
        this.closeWinodw()
        this.$emit('getLinkList')
      })
    },
    closeWinodw() {
      this.$emit('closeUpdateWindow')
    }
  }
}
</script>
