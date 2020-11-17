<template>
  <div>
    <a-card class="my-back-card" hoverable>
      <img src="http://808km.top/blog/myblog/static/img/my-back.jpg" class="my-back">
      <img :src="adminInfo.header" class="my-header">
      <div class="my-bottom-container">
        <div class="my-nickname">{{ adminInfo.name }}</div>
        <div class="my-signature">{{ adminInfo.signature }}</div>
        <a-divider />
        <div class="social-container">
          <div class="social-text">社交：</div>
          <div class="social-item">
            <a href="https://blog.csdn.net/weixin_43448598" target="_blank">
              <img src="http://808km.top/blog/myblog/static/img/icons/csdn.png" class="social-icon">
            </a>
            <a href="https://gitee.com/Pink_oops" target="_blank">
              <img src="http://808km.top/blog/myblog/static/img/icons/gitee.png" class="social-icon">
            </a>
            <a href="http://wpa.qq.com/msgrd?v=3&uin=1437631334&site=qq&menu=yes" target="_blank">
              <img src="http://808km.top/blog/myblog/static/img/icons/qq.png" class="social-icon">
            </a>
            <a href="https://github.com/qq1437631334" target="_blank">
              <img src="http://808km.top/blog/myblog/static/img/icons/github.png" class="social-icon">
            </a>
          </div>
        </div>
        <a-divider />
        <div class="social-container">
          <div class="social-text">加入我们：</div>
          <div class="join-item">
            <a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=c3929a708c3565df98464b67b976dc29c02ded8367e5f6a7a779e79f3dd056f9">
              <img src="http://808km.top/blog/myblog/static/img/icons/QQqun.png" class="social-icon">
            </a>
          </div>
        </div>
      </div>
    </a-card>
  </div>
</template>

<script>
import adminApi from '@/api/admin'
export default {
  data: function() {
    return {
      adminInfo: {}
    }
  },
  created() {
    this.getAdminInfo()
  },
  methods: {
    getAdminInfo() {
      adminApi.getAdminInfo().then(res => {
        this.adminInfo = res.data
      })
    }
  }
}
</script>

<style>
.my-back {
  position: absolute;
  width: 100%;
  z-index: 1;
  top: 0;
}

.my-back-card .ant-card-body {
  padding: 0 !important;
}

.my-header {
  position: relative;
  display: block;
  width: 100px;
  height: 100px;
  border: 5px solid #e5e5e5;
  margin: 75px auto 10px;
  border-radius: 50%;
  z-index: 10;
}

.my-bottom-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 280px;
  margin: auto;
  margin-bottom: 30px;
}

.my-nickname {
  font-size: 22px;
  font-weight: bold;
  line-height: 60px;
}

.my-signature {
  font-size: 15px;
  color: #878d99;
}

.social-container {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

.social-text {
  font-size: 15px;
  color: #878d99;
}

.social-icon {
  width: 40px;
  height: 40px;
}

.social-item {
  width: 200px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.join-item {
  width: 200px;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}

</style>
