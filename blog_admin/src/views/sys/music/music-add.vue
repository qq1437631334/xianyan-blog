<template>
  <div>
    <el-form ref="form" :model="music" label-width="80px" size="mini">
      <el-form-item label="歌曲名">
        <el-input v-model="music.name" />
      </el-form-item>
      <el-form-item label="歌手">
        <el-input v-model="music.artist" />
      </el-form-item>
      <el-form-item label="封面">
        <el-upload
          class="avatar-uploader"
          :action="uploadUrl"
          :show-file-list="false"
          :on-success="uploadSuccess"
          :headers="headers"
        >
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon" />
        </el-upload>
      </el-form-item>
      <el-form-item label="上传歌曲">
        <el-upload
          class="upload-demo"
          :action="uploadUrl"
          :on-success="uploadMusicSuccess"
          :limit="1"
          :file-list="fileList"
          :headers="headers"
        >
          <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item label="歌词">
        <el-input v-model="music.lrc" type="textarea" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="mini" @click="onSubmit">添加</el-button>
        <el-button size="mini" @click="close">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import musicApi from '@/api/music'
import { getToken } from '@/utils/auth'
export default {
  data() {
    return {
      music: {},
      imageUrl: null, // 上传图片回显
      headers: { // 上传文件的请求头
        Authorization: getToken()
      },
      uploadUrl: process.env.VUE_APP_UPLOAD_URL, // 上传图片路径
      fileList: []
    }
  },
  methods: {
    onSubmit() {
      musicApi.save(this.music).then(res => {
        this.$message.success(res.msg)
        this.music = {}
        this.imageUrl = null
        this.fileList = []
        this.$emit('getMusicList')
        this.$emit('closeAddWindow')
      })
    },
    close() {
      this.$emit('closeAddWindow')
    },
    uploadSuccess(res, file) {
      this.$message.success(res.msg)
      this.imageUrl = res.data
      this.music.cover = res.data
    },
    uploadMusicSuccess(res, file, fileList) {
      this.$message.success(res.msg)
      this.music.url = res.data
    }
  }
}
</script>
