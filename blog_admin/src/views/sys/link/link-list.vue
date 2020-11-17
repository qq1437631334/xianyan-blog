<template>
  <div>
    <el-button type="primary" size="mini" class="add_button" @click="openAddWindow()">添加</el-button>
    <!-- 数据表格开始 -->
    <el-table :data="linkList" border style="width: 100%">
      <el-table-column prop="linkId" label="编号" width="50" />
      <el-table-column prop="linkName" label="链接名" width="180" />
      <el-table-column prop="linkUrl" label="链接地址" show-overflow-tooltip />
      <el-table-column prop="createdTime" label="创建时间" width="180" />
      <el-table-column prop="updateTime" label="更新时间" width="180" />
      <el-table-column prop="enable" label="操作" width="160">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="openUpdateWindow(scope.row.linkId)">编辑</el-button>
          <el-button type="danger" size="mini" @click="openDeleteWindow(scope.row.linkId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 数据表格结束 -->

    <!-- 添加弹出层开始 -->
    <el-dialog title="添加" :visible.sync="dialogFormVisible">
      <link-add @getLinkList="getLinkList" @closeAddWindow="closeAddWindow" />
    </el-dialog>
    <!-- 添加弹出层结束 -->

    <!-- 更新抽屉开始 -->
    <el-drawer title="更新" :visible.sync="drawer">
      <link-update :link="link" @closeUpdateWindow="closeUpdateWindow" @getLinkList="getLinkList" />
    </el-drawer>
    <!-- 更新抽屉结束 -->
  </div>
</template>

<script>
import linkApi from '@/api/link'
import LinkAdd from './link-add'
import LinkUpdate from './link-update'
export default {
  components: {
    LinkAdd,
    LinkUpdate
  },
  data: function() {
    return {
      link: {}, // 分类
      linkList: [], // 类型列表
      dialogFormVisible: false, // 默认隐藏
      drawer: false // 默认隐藏
    }
  },
  created() {
    this.getLinkList()
  },
  methods: {
    getLinkList() {
      linkApi.getLinkList().then((res) => {
        this.linkList = res.data
      })
    },
    deleteById(id) {
      return linkApi.delete(id)
    },
    openDeleteWindow(id) {
      this.$confirm('确认删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        link: 'warning'
      })
        .then((res) => {
          this.deleteById(id).then((res) => {
            this.$message.success(res.msg)
            this.getLinkList()
          })
        })
        .catch(() => {})
    },
    openUpdateWindow(id) {
      // 打开更新窗口之前先加载数据
      linkApi.getById(id).then((res) => {
        this.link = res.data
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
      linkApi.enable(id).then((res) => {
        this.$message.success(res.msg)
        this.getLinkList()
      })
    },
    disable(id) {
      linkApi.disable(id).then((res) => {
        this.$message.success(res.msg)
        this.getLinkList()
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
