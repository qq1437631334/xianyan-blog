<template>
  <div>
    <el-form :model="about" label-width="80px">
      <el-form-item label="标题">
        <el-input v-model="about.aboutTitle" />
      </el-form-item>
      <el-form-item label="内容">
        <tinymce v-model="about.aboutContent" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateabout()">更新</el-button>
        <el-button @click="closeWindow()">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import Tinymce from '@/components/Tinymce/index'
import aboutApi from '@/api/about'
export default {
  components: {
    Tinymce
  },
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
    }
  }
}
</script>
