<template>
  <div>
    <router-link :to="'/blog/add'" class="link-type">
      <el-button size="mini" type="primary" class="add_button">写博客</el-button>
    </router-link>
    <!-- 数据表格开始 -->
    <el-table :data="blogList" border style="width: 100%" @sort-change="changeSort">
      <el-table-column prop="blogId" label="编号" width="180" show-overflow-tooltip />
      <el-table-column prop="blogTitle" label="标题" width="180" show-overflow-tooltip />
      <el-table-column prop="typeName" label="类型" width="100" sortable="custom" />
      <el-table-column prop="blogImage" label="封面" width="100">
        <template slot-scope="scope">
          <div class="demo-image__preview">
            <el-image
              style="width: 100px; height: 100px"
              :src="scope.row.blogImage"
              :preview-src-list="[scope.row.blogImage]"
            />
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="blogRead" label="阅读数" width="70" sortable="custom" />
      <el-table-column prop="blogGoods" label="点赞数" width="70" sortable="custom" />
      <el-table-column prop="blogComment" label="评论数" width="70" sortable="custom" />
      <el-table-column prop="blogCollection" label="收藏数" width="70" sortable="custom" />
      <el-table-column prop="blogRemark" label="简介" width="215" />
      <el-table-column prop="createdTime" label="创建时间" width="155" sortable="custom" />
      <el-table-column prop="updateTime" label="更新时间" width="155" sortable="custom" />
      <el-table-column prop="enable" fixed="right" label="操作" width="130">
        <template slot-scope="scope">
          <el-dropdown>
            <el-button type="primary" round>
              操作
              <i class="el-icon-arrow-down el-icon--right" />
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-button type="primary" size="mini" round @click="openUpdateWindow(scope.row.blogId)">编辑</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button type="primary" size="mini" t round style="margin-top:12px" @click="openReadWindow(scope.row.blogId)">查看</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button type="danger" size="mini" round style="margin-top:15px" @click="deleteById(scope.row.blogId)">删除</el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
    <!-- 数据表格结束 -->

    <!-- 更新弹出层开始 -->
    <el-dialog title="更新" :visible.sync="drawer">
      <blog-update :blog="blog" @closeUpdateWindow="closeUpdateWindow" @getBlogList="getBlogList" />
    </el-dialog>
    <!-- 更新弹出层结束 -->

    <!-- 阅读弹出层开始 -->
    <el-dialog title="阅读" :visible.sync="readWindow">
      <p class="markdown-body" v-html="content" />
    </el-dialog>
    <!-- 阅读弹出层结束 -->

    <!-- 分页组件 -->
    <el-pagination
      :current-page="page.pageNUm"
      :page-sizes="[5, 10 , 20, 50]"
      :page-size="page.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="blog.total"
      z
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import blogApi from '@/api/blog'
import BlogUpdate from './blog-update'
var { Sitdown } = require('sitdown')
export default {
  components: {
    BlogUpdate
  },
  data: function() {
    return {
      page: {
        pageNum: 1,
        pageSize: 4,
        orderByField: 'created_time',
        orderByMode: 'desc'
      },
      blog: {
        blogId: '',
        blogTitle: '',
        blogImage: null,
        blogGoods: 0,
        blogRead: 0,
        blogCollection: 0,
        blogRemark: null,
        blogComment: 0,
        blogSource: null,
        createdTime: '',
        updateTime: '',
        version: 1,
        deleted: null,
        blogName: '',
        blogMonth: null
      },
      blogList: [],
      dialogFormVisible: false,
      drawer: false,
      readWindow: false,
      content: null
    }
  },
  created() {
    this.getBlogList()
  },
  methods: {
    getBlogList() {
      blogApi.getPage(this.page).then((res) => {
        this.blogList = res.data.data
      })
    },
    deleteById(id) {
      blogApi.delete(id).then((res) => {
        this.$message.success(res.msg)
        this.getBlogList()
      })
    },
    openUpdateWindow(id) {
      // 打开更新窗口之前先加载数据
      blogApi.getById(id).then(res => {
        this.blog = res.data
        this.transformationContent()
        console.log(this.blog)
        this.drawer = true
      })
    },
    closeUpdateWindow() {
      this.drawer = false
    },
    openReadWindow(id) {
      this.readWindow = true
      blogApi.getById(id).then(res => {
        this.content = res.data.blogContent
      })
    },
    closeReadWindow(id) {
      this.readWindow = false
    },
    handleSizeChange(val) {
      this.page.pageSize = val
      this.getBlogList()
    },
    handleCurrentChange(val) {
      this.page.pageNum = val
      this.getBlogList()
    },
    changeSort(obj) {
      this.page.orderByField = obj.prop
      this.page.orderByMode = obj.order
      if (this.page.orderByField === null) {
        this.page.orderByField = 'created_time'
      }
      if (this.page.orderByField === 'typeName') {
        this.page.orderByField = 'blogType'
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
      this.getBlogList()
    },
    // 将博客内容从HTML转换成MD
    transformationContent() {
      var sitdown = new Sitdown()
      sitdown.service.addRule('strikethrough', {
        filter: function(node, options) {
          var Class = node.getAttribute('class')
          return (
            node.nodeName === 'DIV' &&
      Class &&
      Class.indexOf('hljs') !== -1 &&
      Class.indexOf('hljs-') === -1
          )
        },
        replacement: function(content, node, options) {
          node = node.children[0]
          return (
            '``` ' + node.className.substring(5) + '\n' + node.innerText + '\n```\n'
          )
        }
      })
      sitdown.service.addRule('strikethrough', {
        filter: function(node, options) {
          var Class = node.getAttribute('class')
          return (
            node.nodeName === 'DIV' && Class && Class.indexOf('hljs-center') !== -1
          )
        },
        replacement: function(content, node, options) {
          console.log({ node })
          node = node.children[0]
          return '::: hljs-center' + '\n' + node.innerText + '\n:::\n'
        }
      })
      sitdown.service.addRule('strikethrough', {
        filter: ['ins'],
        replacement: function(content, node, options) {
          return '++' + content + '++'
        }
      })
      sitdown.service.addRule('strikethrough', {
        filter: ['sub', 'sup'],
        replacement: function(content, node, options) {
          if (node.nodeName === 'SUB') {
            return '~' + content + '~'
          } else {
            return '^' + content + '^'
          }
        }
      })
      this.blog.blogContent = sitdown.HTMLToMD(this.blog.blogContent)
    }
  }
}
</script>
