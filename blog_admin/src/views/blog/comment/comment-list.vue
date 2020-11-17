<template>
  <div>
    <!-- 数据表格开始 -->
    <el-table :data="commentList.data" border style="width: 100%">
      <el-table-column prop="commentBlog.blogTitle" label="标题" show-overflow-tooltip />
      <el-table-column prop="commentUser.nickname" label="评价人" width="150" />
      <el-table-column prop="commentContent" label="评价内容" width="400" />
      <el-table-column prop="commentGood" label="点赞数" width="100" />
      <el-table-column prop="createdTime" label="评价时间" width="200" />
      <el-table-column fixed="right" label="操作" width="130">
        <template slot-scope="scope">
          <el-button
            type="danger"
            round
            style="margin-top:12px"
            size="mini"
            @click="deleteById(scope.row.id)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 数据表格结束 -->

    <!-- 分页组件 -->
    <el-pagination
      :current-page="page.pageNUm"
      :page-sizes="[5, 10 , 20, 50]"
      :page-size="page.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="commentList.totalCount"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import commentApi from '@/api/comment'
export default {
  data: function() {
    return {
      page: {
        pageNum: 1,
        pageSize: 10
      },
      commentList: {}
    }
  },
  created() {
    this.getCommentList()
  },
  methods: {
    getCommentList() {
      commentApi.getPageBack(this.page).then(res => {
        this.commentList = res.data
      })
    },
    deleteById(id) {
      commentApi.delete(id).then((res) => {
        this.$message.success(res.msg)
        this.getCommentList()
      })
    },
    handleSizeChange(val) {
      this.page.pageSize = val
      this.getCommentList()
    },
    handleCurrentChange(val) {
      this.page.pageNum = val
      this.getCommentList()
    }
  }
}
</script>
