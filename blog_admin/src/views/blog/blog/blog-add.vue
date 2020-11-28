<template>
  <div>
    <el-form ref="form" :model="blog" label-width="80px" size="mini">
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
        <el-button type="primary" @click="onSubmit">添加</el-button>
        <router-link :to="'/blog'" class="link-type">
          <el-button>返回</el-button>
        </router-link>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import blogApi from '@/api/blog'
import { getToken } from '@/utils/auth'
import { uploadImage } from '@/api/upload'
export default {
  data() {
    return {
      blog: {
        blogId: '',
        blogTitle: '',
        blogImage: null,
        blogContent: '',
        blogRemark: null
      },
      imageUrl: null, // 上传图片回显
      headers: { // 上传文件的请求头
        Authorization: getToken()
      },
      uploadUrl: process.env.VUE_APP_UPLOAD_URL, // 上传图片路径
      typeList: this.$store.getters.typeList
    }
  },
  methods: {
    onSubmit() {
      blogApi.save(this.blog).then(res => {
        this.$message.success(res.msg)
        this.$router.push('/blog')
      })
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
