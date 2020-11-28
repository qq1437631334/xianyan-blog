<template>
  <div>
    <el-form ref="form" :model="about" label-width="80px" size="mini">
      <el-form-item label="标题">
        <el-input v-model="about.aboutTitle" />
      </el-form-item>
      <el-form-item label="内容">
        <mavon-editor ref="md" v-model="about.aboutContent" @imgAdd="uploadImg" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="mini" @click="onSubmit">提交</el-button>
        <el-button size="mini">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import aboutApi from '@/api/about'
import { uploadImage } from '@/api/upload'
export default {
  data() {
    return {
      about: {}
    }
  },
  methods: {
    onSubmit() {
      aboutApi.save(this.about).then(res => {
        this.$message.success(res.msg)
        this.about = {}
        this.$emit('getAboutList')
        this.$emit('closeAddWindow')
      })
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
