<template>
  <div>
    <el-button type="primary" class="add_button" @click="openAddWindow()">添加</el-button>
    <!-- 数据表格开始 -->
    <el-table :data="userList" border style="width: 100%" @sort-change="changeSort">
      <el-table-column prop="userId" label="编号" width="50" />
      <el-table-column prop="username" label="用户名" width="180" />
      <el-table-column prop="password" label="密码" width="180" show-overflow-tooltip />
      <el-table-column prop="name" label="姓名" width="80" />
      <el-table-column prop="sex" label="性别" width="50">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.sex === 1">男</el-tag>
          <el-tag v-else>女</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="userImage" label="头像" width="100">
        <template slot-scope="scope">
          <div class="demo-image__preview">
            <el-image
              style="width: 100px; height: 100px"
              :src="scope.row.header"
              :preview-src-list="[scope.row.header]"
            />
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="nickname" label="昵称" width="100" />
      <el-table-column prop="userEmail" label="电子邮箱" width="180" />
      <el-table-column prop="createdTime" label="创建时间" width="180" sortable="custom" />
      <el-table-column prop="updateTime" label="更新时间" width="180" sortable="custom" />
      <el-table-column prop="enable" fixed="right" label="操作" width="130">
        <template slot-scope="scope">
          <el-dropdown>
            <el-button type="primary" round>
              操作
              <i class="el-icon-arrow-down el-icon--right" size="mini" />
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-button type="primary" round size="mini" @click="openUpdateWindow(scope.row.userId)">编辑</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button id="resetPassword" type="warning" round size="mini" @click="openPasswordWindow(scope.row.userId)">修改密码</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button type="danger" round style="margin-top:15px" size="mini" @click="deleteById(scope.row.userId)">删除</el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
    <!-- 数据表格结束 -->

    <!-- 添加弹出层开始 -->
    <el-dialog title="添加" :visible.sync="dialogFormVisible">
      <UserAdd @closeAddWindow="closeAddWindow" @getUserList="getUserList" />
    </el-dialog>
    <!-- 添加弹出层结束 -->

    <!-- 更新弹出层开始 -->
    <el-dialog title="更新" :visible.sync="drawer">
      <user-update :user="user" @closeUpdateWindow="closeUpdateWindow" @getUserList="getUserList" />
    </el-dialog>
    <!-- 更新弹出层结束 -->

    <!-- 修改密码弹出框 -->
    <el-dialog title="修改密码" :visible.sync="dialogVisible" width="30%">
      <user-update-password :id="id" @closePasswordWindow="closePasswordWindow" @getUserList="getUserList" />
    </el-dialog>

    <!-- 分页组件 -->
    <el-pagination
      :current-page="page.pageNUm"
      :page-sizes="[5, 10 , 20, 50]"
      :page-size="page.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="user.total"
      z
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import userApi from '@/api/myUser'
import UserAdd from './user-add'
import UserUpdate from './user-update'
import UserUpdatePassword from './user-update-password'
export default {
  components: {
    UserAdd,
    UserUpdate,
    UserUpdatePassword
  },
  data: function() {
    return {
      page: {
        pageNum: 1,
        pageSize: 4,
        orderByField: 'created_time',
        orderByMode: 'desc'
      },
      user: {},
      userList: [],
      dialogFormVisible: false,
      dialogVisible: false,
      drawer: false,
      id: null
    }
  },
  created() {
    this.getUserList()
  },
  methods: {
    getUserList() {
      userApi.getPage(this.page).then((res) => {
        this.userList = res.data.list
      })
    },
    deleteById(id) {
      userApi.delete(id).then((res) => {
        this.$message.success(res.msg)
        this.getUserList()
      })
    },
    openAddWindow() {
      this.dialogFormVisible = true
    },
    closeAddWindow() {
      this.dialogFormVisible = false
    },
    openUpdateWindow(id) {
      // 打开更新窗口之前先加载数据
      userApi.getById(id).then(res => {
        this.user = res.data
        this.drawer = true
      })
    },
    closeUpdateWindow() {
      this.drawer = false
    },
    handleSizeChange(val) {
      this.page.pageSize = val
      this.getUserList()
    },
    handleCurrentChange(val) {
      this.page.pageNum = val
      this.getUserList()
    },
    changeSort(obj) {
      this.page.orderByField = obj.prop
      this.page.orderByMode = obj.order
      if (this.page.orderByField === null) {
        this.page.orderByField = 'created_time'
      }
      if (this.page.orderByField === 'typeName') {
        this.page.orderByField = 'userType'
      }
      if (this.page.orderByMode === null) {
        this.page.orderByMode = 'desc'
      }
      if (this.page.orderByMode === 'ascending') {
        this.page.orderByMode = 'asc'
      }
      if (this.page.orderByMode === 'descending') {
        this.page.orderByMode = 'desc'
      }
      this.getUserList()
    },
    openPasswordWindow(id) {
      this.id = id
      this.dialogVisible = true
    },
    closePasswordWindow() {
      this.dialogVisible = false
    }
  }
}
</script>

<style>
#resetPassword {
  margin-top: 12px;
}
</style>
