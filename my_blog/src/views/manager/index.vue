<template>
  <div>
    <div class="manager-container">
      <a-menu
        v-model="current"
        style="width: 200px;min-height:800px"
        mode="vertical"
      >
        <a-menu-item key="1">
          <router-link to="userInfo">个人信息</router-link>
        </a-menu-item>
        <a-menu-item key="2">
          <router-link to="collection">我的收藏</router-link>
        </a-menu-item>
        <a-menu-item key="3">
          <router-link to="comment">我的评论</router-link>
        </a-menu-item>
      </a-menu>
      <router-view class="manager-main" />
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      current: ['1']
    }
  }
}

</script>
<style scoped>
.manager-container {
  display: flex;
  flex-direction: row;
}
.ant-menu-item  {
  padding-left: 40px;
}
.manager-main {
  margin-left: 15px;
  background-color: #fff;
  width: 100%;
  min-height: 800px;
  padding: 40px;
}
</style>
