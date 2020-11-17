<template>
  <div>
    <el-form :model="type" label-width="80px">
      <el-form-item label="类型名称">
        <el-input v-model="type.typeName" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateType()">更新</el-button>
        <el-button @click="closeWindow()">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import typeApi from '@/api/type'
export default {
  props: {
    type: {
      type: Object,
      default: null
    }
  },
  methods: {
    updateType() {
      typeApi.update(this.type).then(res => {
        this.$message.success(res.msg)
        this.closeWindow()
        this.$emit('getTypeList')
      })
    },
    closeWindow() {
      this.$emit('closeUpdateWindow')
    }
  }
}
</script>
