<template>
  <div>
    <el-button size="mini" type="primary" class="add_button" @click="openAddWindow()">添加</el-button>
    <audio :src="musicUrl" :autoplay="autoplay" controls="controls" />
    <!-- 数据表格开始 -->
    <el-table :data="musicList" border style="width: 100%" @sort-change="changeSort">
      <el-table-column prop="id" label="编号" width="50" />
      <el-table-column prop="name" label="歌曲名" width="180" show-overflow-tooltip />
      <el-table-column prop="artist" label="歌手" width="180" />
      <el-table-column prop="cover" label="封面" width="150">
        <template slot-scope="scope">
          <div class="demo-image__preview">
            <el-image
              style="width: 150px; height: 150px"
              :src="scope.row.cover"
              :preview-src-list="[scope.row.cover]"
            />
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="enable" label="启用状态" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.enable">启用</el-tag>
          <el-tag v-else type="warning">未启用</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createdTime" label="创建时间" sortable="custom" />
      <el-table-column prop="enable" fixed="right" label="操作" width="130">
        <template slot-scope="scope">
          <el-dropdown>
            <el-button type="primary" size="mini" round>
              操作
              <i class="el-icon-arrow-down el-icon--right" />
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-button
                type="primary"
                size="mini"
                round
                style="margin-left:18px;margin-bottom:3px"
                @click="openUpdateWindow(scope.row.id)"
              >编辑</el-button>
              <el-dropdown-item>
                <el-button
                  type="primary"
                  size="mini"
                  round
                  @click="listenMusic(scope.row.url)"
                >试听</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button
                  v-if="scope.row.enable === 0"
                  type="success"
                  size="mini"
                  round
                  @click="enable(scope.row.id)"
                >启用</el-button>

                <el-button
                  v-else
                  type="warning"
                  round
                  size="mini"
                  @click="disable(scope.row.id)"
                >弃用</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button
                  type="danger"
                  size="mini"
                  round
                  @click="deleteById(scope.row.id)"
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
      <MusicAdd @closeAddWindow="closeAddWindow" @getMusicList="getMusicList" />
    </el-dialog>
    <!-- 添加弹出层结束 -->

    <!-- 更新弹出层开始 -->
    <el-dialog title="更新" :visible.sync="drawer">
      <music-update
        :music="music"
        @closeUpdateWindow="closeUpdateWindow"
        @getMusicList="getMusicList"
      />
    </el-dialog>
    <!-- 更新弹出层结束 -->

    <!-- 分页组件 -->
    <el-pagination
      :current-page="page.pageNUm"
      :page-sizes="[5, 10 , 20, 50]"
      :page-size="page.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="music.total"
      z
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import musicApi from '@/api/music'
import MusicAdd from './music-add'
import MusicUpdate from './music-update'
export default {
  components: {
    MusicAdd,
    MusicUpdate
  },
  data: function() {
    return {
      page: {
        pageNum: 1,
        pageSize: 4,
        orderByField: 'created_time',
        orderByMode: 'desc'
      },
      music: {},
      musicList: [],
      dialogFormVisible: false,
      drawer: false,
      readWindow: false,
      autoplay: true,
      musicUrl: ''
    }
  },
  created() {
    this.getMusicList()
  },
  methods: {
    getMusicList() {
      musicApi.getPage(this.page).then((res) => {
        this.musicList = res.data.list
      })
    },
    deleteById(id) {
      musicApi.delete(id).then((res) => {
        this.$message.success(res.msg)
        this.getMusicList()
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
      musicApi.getById(id).then((res) => {
        this.music = res.data
        this.drawer = true
      })
    },
    closeUpdateWindow() {
      this.drawer = false
    },
    openReadWindow(id) {
      this.readWindow = true
      musicApi.getById(id).then((res) => {
        this.content = res.data.musicContent
      })
    },
    closeReadWindow(id) {
      this.readWindow = false
    },
    handleSizeChange(val) {
      this.page.pageSize = val
      this.getMusicList()
    },
    handleCurrentChange(val) {
      this.page.pageNum = val
      this.getMusicList()
    },
    enable(id) {
      musicApi.enable(id).then((res) => {
        this.$message.success(res.msg)
        this.getMusicList()
      })
    },
    disable(id) {
      musicApi.disable(id).then((res) => {
        this.$message.success(res.msg)
        this.getMusicList()
      })
    },
    changeSort(obj) {
      this.page.orderByField = obj.prop
      this.page.orderByMode = obj.order
      if (this.page.orderByField === null) {
        this.page.orderByField = 'created_time'
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
      this.getMusicList()
    },
    listenMusic(url) {
      this.musicUrl = url
    }
  }
}
</script>
