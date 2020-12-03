<template>
  <div>
    <el-form :model="blog" label-width="80px">
      <el-form-item label="标题">
        <el-input v-model="blog.blogTitle" />
      </el-form-item>
      <el-form-item label="分类">
        <el-select v-model="blog.blogType" clearable filterable placeholder="请选择" style="width: 100%">
          <el-option
            v-for="type in typeList"
            :key="type.typeId"
            :label="type.typeName"
            :value="type.typeId"
          />
        </el-select></el-form-item>
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
      <el-form-item label="内容">
        <mavon-editor ref="md" v-model="blog.blogContent" @imgAdd="uploadImg" />
      </el-form-item>
      <el-form-item label="备注">
        <el-input v-model="blog.blogRemark" type="textarea" />
      </el-form-item>
      <el-form-item>
        <el-button blog="primary" @click="updateblog()">更新</el-button>
        <el-button @click="closeWindow()">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import blogApi from '@/api/blog'
import uploadImage from '@/api/upload'
import { getToken } from '@/utils/auth'

export default {
  props: {
    blog: {
      type: Object,
      default: null
    }
  },
  data: function() {
    return {
      imageUrl: this.blog.blogImage, // 上传图片回显
      headers: { // 上传文件的请求头
        token: getToken()
      },
      uploadUrl: process.env.VUE_APP_UPLOAD_URL, // 上传图片路径
      typeList: this.$store.getters.typeList
    }
  },
  watch: {
    'blog.blogImage': function(newVal, oldVal) {
      this.imageUrl = newVal
    }
  },
  methods: {
    updateblog() {
      // 存入markdown渲染成html的代码
      this.blog.blogContent = this.$refs.md.d_render
      blogApi.update(this.blog).then(res => {
        this.$message.success(res.msg)
        this.closeWindow()
        this.$emit('getBlogList')
      })
    },
    closeWindow() {
      this.$emit('closeUpdateWindow')
      this.imageUrl = null
    },
    uploadSuccess(res, file) {
      this.$message.success(res.msg)
      this.imageUrl = res.data
      this.blog.blogImage = res.data
    },
    // 图片上传
    uploadImg(pos, $file) {
      // 定义上传uri
      const uploadUri = 'img/blogImg/'
      // 定义上传对象
      var formData = new FormData()
      formData.append('file', $file)
      formData.append('uploadUri', uploadUri)
      uploadImage(formData).then(res => {
        // 将markdown插入的图片url更换为返回的ul
        this.$refs.md.$img2Url(pos, res.data)
      })
    }

  }
}
</script>
