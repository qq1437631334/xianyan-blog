<template>
  <div v-loading="loading">
    <el-button type="primary" size="mini" class="add_button" @click="exportAll">导出全部</el-button>
    <el-button type="danger" size="mini" class="add_button" @click="deleteByIds">批量删除</el-button>
    <el-table
      ref="multipleTable"
      :data="logList"
      tooltip-effect="dark"
      style="width: 100%"
      border
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" select-on-indeterminate />
      <el-table-column prop="logId" label="编号" width="70" />
      <el-table-column prop="logUrl" label="请求地址" width="180" />
      <el-table-column prop="logParams" label="请求参数" width="300" show-overflow-tooltip />
      <el-table-column prop="logStatus" label="请求状态" width="60">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.logStatus">正常</el-tag>
          <el-tag v-else type="warning">异常</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="logMessage" label="异常信息" width="300" show-overflow-tooltip />
      <el-table-column prop="logMethod" label="请求方式" width="100" />
      <el-table-column prop="logTime" label="响应时间（毫秒）" width="80" />
      <el-table-column prop="logResult" label="返回结果" width="300" show-overflow-tooltip />
      <el-table-column prop="logIp" label="请求ip" width="200" />
      <el-table-column prop="createdTime" label="创建时间" width="180" />
    </el-table>
    <!-- 分页组件 -->
    <el-pagination
      :current-page="page.pageNUm"
      :page-sizes="[5, 10 , 20, 50]"
      :page-size="parseInt(page.pageSize)"
      layout="total, sizes, prev, pager, next, jumper"
      :total="log.total"
      z
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import logApi from '@/api/log'
export default {
  data: function() {
    return {
      ids: [],
      selection: [],
      page: {
        pageNum: '1',
        pageSize: '10',
        orderByField: 'created_time',
        orderByMode: 'desc'
      },
      log: {},
      logList: [],
      loading: false
    }
  },
  created() {
    this.getLogList()
  },
  methods: {
    getLogList() {
      logApi.getPage(this.page).then((res) => {
        this.logList = res.data.list
        this.log = res.data
      })
    },
    handleSelectionChange(selection) {
      this.selection = selection
    },
    handleSizeChange(val) {
      this.page.pageSize = val
      this.getLogList()
    },
    handleCurrentChange(val) {
      this.page.pageNum = val
      this.getLogList()
    },
    deleteByIds() {
      for (let i = 0; i < this.selection.length; i++) {
        this.ids[i] = this.selection[i].logId
      }
      logApi.deleteByIds(this.ids).then(res => {
        this.$message.success(res.msg)
        this.getLogList()
      })
    },
    exportAll() {
      this.loading = true
      logApi.exportExcel().then(res => {
        const blob = new Blob([res], { type: 'application/vnd.ms-excel' })
        const elink = document.createElement('a')
        elink.download = '系统日志.xlsx'
        elink.style.display = 'none'
        elink.href = URL.createObjectURL(blob)
        document.body.appendChild(elink)
        elink.click()
        URL.revokeObjectURL(elink.href)
        document.body.removeChild(elink)
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    }
  }
}
</script>
