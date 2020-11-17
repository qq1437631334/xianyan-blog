<template>
  <div>
    <el-button type="primary" size="mini" class="add_button" @click="openAddWindow()">添加</el-button>
    <!-- 数据表格开始 -->
    <el-table :data="typeList" border style="width: 100%">
      <el-table-column prop="typeId" label="编号" width="180" />
      <el-table-column prop="typeName" label="类型名称" width="180" />
      <el-table-column prop="typeBlogCount" label="博客数" width="180" />
      <el-table-column prop="enable" label="启用状态" width="180">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.enable">启用</el-tag>
          <el-tag v-else type="warning">未启用</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="enable" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="openUpdateWindow(scope.row.typeId)">编辑</el-button>
          <el-button
            v-if="scope.row.enable === 0"
            type="success"
            size="mini"
            @click="enable(scope.row.typeId)"
          >启用</el-button>
          <el-button v-else type="warning" size="mini" @click="disable(scope.row.typeId)">弃用</el-button>
          <el-button type="danger" size="mini" @click="openDeleteWindow(scope.row.typeId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 数据表格结束 -->

    <!-- 添加弹出层开始 -->
    <el-dialog title="添加" :visible.sync="dialogFormVisible">
      <type-add @getTypeList="getTypeList" @closeAddWindow="closeAddWindow" />
    </el-dialog>
    <!-- 添加弹出层结束 -->

    <!-- 更新抽屉开始 -->
    <el-drawer title="更新" :visible.sync="drawer">
      <type-update :type="type" @closeUpdateWindow="closeUpdateWindow" @getTypeList="getTypeList" />
    </el-drawer>
    <!-- 更新抽屉结束 -->
  </div>
</template>

<script>
import typeApi from '@/api/type'
import TypeAdd from './type-add'
import TypeUpdate from './type-update'
export default {
  components: {
    TypeAdd,
    TypeUpdate
  },
  data: function() {
    return {
      type: {}, // 分类
      typeList: [], // 类型列表
      dialogFormVisible: false, // 默认隐藏
      drawer: false // 默认隐藏
    }
  },
  created() {
    this.getTypeList()
  },
  methods: {
    getTypeList() {
      typeApi.getListBack().then((res) => {
        this.typeList = res.data
      })
    },
    deleteById(id) {
      return typeApi.delete(id)
    },
    openDeleteWindow(id) {
      this.$confirm('确认删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then((res) => {
          this.deleteById(id).then((res) => {
            this.$message.success(res.msg)
            this.getTypeList()
          })
        })
        .catch(() => {})
    },
    openUpdateWindow(id) {
      // 打开更新窗口之前先加载数据
      typeApi.getById(id).then((res) => {
        this.type = res.data
        this.drawer = true
      })
    },
    closeUpdateWindow() {
      this.drawer = false
    },
    openAddWindow() {
      this.dialogFormVisible = true
    },
    closeAddWindow() {
      this.dialogFormVisible = false
    },
    enable(id) {
      typeApi.enable(id).then((res) => {
        this.$message.success(res.msg)
        this.getTypeList()
      })
    },
    disable(id) {
      typeApi.disable(id).then((res) => {
        this.$message.success(res.msg)
        this.getTypeList()
      })
    }
  }
}
</script>

<style scoped>
.add_button {
  margin-bottom: 15px;
  margin-top: 15px;
  margin-left: 15px;
}
.delete_button {
  margin-left: 12px;
}
</style>
