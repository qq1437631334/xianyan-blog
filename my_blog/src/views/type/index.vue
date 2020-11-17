<template>
  <div>
    <div class="type-container">
      <div class="type-title">分类</div>
      <div class="type-count">共计 {{ count }} 个分类</div>
      <div class="type-content">
        <router-link v-for="item in typeList" :key="item.typeId" :to="'/blogList/'+item.typeId" class="type-item">{{ item.typeName }} （{{ item.typeBlogCount }}）</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import typeApi from '@/api/type'
export default {
  data: function() {
    return {
      typeList: [],
      count: 0
    }
  },
  created() {
    this.getTypeList()
  },
  methods: {
    getTypeList() {
      typeApi.getTypeList().then(res => {
        this.typeList = res.data
        this.count = res.data.length
      })
    }
  }

}
</script>

<style scoped>
.type-container {
  background: #fff;
  padding: 40px 25px;
}

.type-title {
  text-align: center;
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 60px;
}

.type-count {
  text-align: center;
  font-size: 20px;
  margin-bottom: 40px;
}

.type-content {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.type-item{
  color: black;
  font-size: 16px;
}
</style>
