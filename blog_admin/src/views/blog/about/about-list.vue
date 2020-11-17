<template>
  <div>
    <el-button type="primary" size="mini" class="add_button" @click="openAddWindow()">添加</el-button>
    <!-- 数据表格开始 -->
    <el-table :data="aboutList" border style="width: 100%" @sort-change="changeSort">
      <el-table-column prop="aboutId" label="编号" width="180" show-overflow-tooltip />
      <el-table-column prop="aboutTitle" label="标题" show-overflow-tooltip />
      <el-table-column prop="aboutRead" label="阅读数" width="100" sortable="custom" />
      <el-table-column prop="enable" label="启用状态" width="180">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.enable">启用</el-tag>
          <el-tag v-else type="warning">未启用</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createdTime" label="创建时间" width="300" sortable="custom" />
      <el-table-column prop="updateTime" label="更新时间" width="300" sortable="custom" />
      <el-table-column prop="enable" fixed="right" label="操作" width="130">
        <template slot-scope="scope">
          <el-dropdown>
            <el-button type="primary" round size="mini">
              操作
              <i class="el-icon-arrow-down el-icon--right" />
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-button
                  type="primary"
                  round
                  size="mini"
                  @click="openUpdateWindow(scope.row.aboutId)"
                >编辑</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button
                  v-if="scope.row.enable === 0"
                  type="success"
                  round
                  size="mini"
                  @click="enable(scope.row.aboutId)"
                >启用</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button
                  v-if="scope.row.enable === 1"
                  type="warning"
                  round
                  size="mini"
                  @click="disable(scope.row.aboutId)"
                >弃用</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button
                  type="danger"
                  round
                  style="margin-top:12px"
                  size="mini"
                  @click="deleteById(scope.row.aboutId)"
                >删除</el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
    <!-- 数据表格结束 -->

    <!-- 添加弹出层开始 -->
    <el-dialog title="添加" :visible.sync="dialogFormVisible">
      <AboutAdd @closeAddWindow="closeAddWindow" @getAboutList="getAboutList" />
    </el-dialog>
    <!-- 添加弹出层结束 -->

    <!-- 更新弹出层开始 -->
    <el-dialog title="更新" :visible.sync="drawer">
      <about-update
        :about="about"
        @closeUpdateWindow="closeUpdateWindow"
        @getAboutList="getAboutList"
      />
    </el-dialog>
    <!-- 更新弹出层结束 -->

    <!-- 分页组件 -->
    <el-pagination
      :current-page="page.pageNUm"
      :page-sizes="[5, 10 , 20, 50]"
      :page-size="page.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="about.total"
      z
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import aboutApi from '@/api/about'
import AboutAdd from './about-add'
import AboutUpdate from './about-update'
export default {
  components: {
    AboutAdd,
    AboutUpdate
  },
  data: function() {
    return {
      page: {
        pageNum: 1,
        pageSize: 4,
        orderByField: 'created_time',
        orderByMode: 'desc'
      },
      aboutList: [],
      about: {},
      dialogFormVisible: false,
      drawer: false
    }
  },
  created() {
    this.getAboutList()
  },
  methods: {
    getAboutList() {
      aboutApi.getPage(this.page).then((res) => {
        this.aboutList = res.data.list
      })
    },
    deleteById(id) {
      aboutApi.delete(id).then((res) => {
        this.$message.success(res.msg)
        this.getAboutList()
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
      aboutApi.getById(id).then((res) => {
        this.about = res.data
        this.drawer = true
      })
    },
    closeUpdateWindow() {
      this.drawer = false
    },
    handleSizeChange(val) {
      this.page.pageSize = val
      this.getAboutList()
    },
    handleCurrentChange(val) {
      this.page.pageNum = val
      this.getAboutList()
    },
    changeSort(obj) {
      this.page.orderByField = obj.prop
      this.page.orderByMode = obj.order
      if (this.page.orderByField === null) {
        this.page.orderByField = 'created_time'
      }
      if (this.page.orderByField === 'typeName') {
        this.page.orderByField = 'aboutType'
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
      this.getAboutList()
    },
    enable(id) {
      aboutApi.enable(id).then((res) => {
        this.$message.success(res.msg)
        this.getAboutList()
      })
    },
    disable(id) {
      aboutApi.disable(id).then((res) => {
        this.$message.success(res.msg)
        this.getAboutList()
      })
    }
  }
}
</script>
