<template>
  <div>
    <el-form :model="type" label-width="80px">
      <el-form-item label="类型名称">
        <el-input v-model="type.typeName" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="addType()">添加</el-button>
        <el-button @click="closeWinodw">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import typeApi from '@/api/type'
export default {
  data: function() {
    return {
      type: {}
    }
  },
  methods: {
    addType() {
      typeApi.save(this.type).then(res => {
        this.$message.success(res.msg)
        this.closeWinodw()
        this.resetTypeList()
        this.$emit('getTypeList')
      })
    },
    closeWinodw() {
      this.$emit('closeAddWindow')
    },
    resetTypeList() {
      typeApi.getList().then(res => {
        this.$store.commit('global/SET_TYPE_LIST', res.data)
      })
    }
  }
}
</script>
