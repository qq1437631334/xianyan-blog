<template>
  <div class="main">
    <transition name="menu">
      <navbar v-show="show" class="menu-container" />
    </transition>
    <div class="main-container">
      <div class="left-container">
        <a-config-provider :locale="locale">
          <router-view />
        </a-config-provider>
      </div>
      <right />
    </div>
    <bottom />
    <a-back-top />
  </div>
</template>
<script>
import zhCN from 'ant-design-vue/lib/locale-provider/zh_CN'
import Navbar from '@/views/navbar/navbar'
import Right from '@/views/right/index'
import Bottom from '@/views/bottom/index'
export default {
  components: {
    Navbar,
    Right,
    Bottom
  },
  data() {
    return {
      locale: zhCN,
      show: true
    }
  },
  created() {
    this.mouseScroll()
  },
  mounted() {
  },
  methods: {
    mouseScroll() {
      // 给页面绑定滑轮滚动事件
      if (document.addEventListener) { // firefox
        document.addEventListener('DOMMouseScroll', this.watchScroll, false)
      }
      // 滚动滑轮触发scrollFunc方法  //ie 谷歌
      window.onmousewheel = document.onmousewheel = this.watchScroll
    },
    watchScroll(e) {
      e = e || window.event
      if (e.wheelDelta) {
        if (e.wheelDelta > 0 && this.show === false) {
          // 当滑轮向上滚动
          this.show = true
        }
        if (e.wheelDelta < 0 && this.show === true) {
          // 当滑轮向下滚动
          this.show = false
        }
      } else if (e.detail) {
        if (e.detail < 0 && this.show === false) {
          // 当滑轮向上滚动
          this.show = true
        }
        if (e.detail > 0 && this.show === true) {
          // 当滑轮向下滚动
          this.show = false
        }
      }
    }
  }
}
</script>
<style>
.main {
  background-color: #f5f5f5;
}
.main-container {
  width: 1200px;
  margin: auto;
  display: flex;
  flex-direction: row;
}

.left-container {
  width: 850px;
  padding: 0 10px;
}

.menu-container {
  position: sticky;
  top: 0;
  z-index: 999;
  width: 100%;
  height: 70px;
}

/* 可以设置不同的进入和离开动画 */
/* 设置持续时间和动画函数 */
.menu-enter-active,
.menu-leave-active {
  transition: all .8s ease;
}
.menu-enter, .menu-leave-to
/* .menu-leave-active for below version 2.1.8 */ {
  transform: translateY(-70px);
  opacity: 0;
}

</style>
