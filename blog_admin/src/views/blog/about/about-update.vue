<template>
  <div>
    <el-form :model="about" label-width="80px">
      <el-form-item label="标题">
        <el-input v-model="about.aboutTitle" />
      </el-form-item>
      <el-form-item label="内容">
        <mavon-editor ref="md" v-model="about.aboutContent" @imgAdd="uploadImg" />

      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateabout()">更新</el-button>
        <el-button @click="closeWindow()">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import aboutApi from '@/api/about'
import uploadImage from '@/api/upload'
export default {
  props: {
    about: {
      type: Object,
      default: null
    }
  },
  data: function() {
    return {
      typeList: this.$store.getters.typeList
    }
  },
  methods: {
    updateabout() {
      aboutApi.update(this.about).then(res => {
        this.$message.success(res.msg)
        this.$emit('closeUpdateWindow')
        this.$emit('getAboutList')
      })
    },
    closeWindow() {
      this.$emit('closeUpdateWindow')
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
