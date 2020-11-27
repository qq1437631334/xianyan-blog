<template>
  <div class="main">
    <router-view />
  </div>
</template>
<style>
.main {
  background-color: #f5f5f5;
}

/* 设置滚动条的样式 设置成纯黑色 不让用户看到 但是可以实现滚动效果 */
::-webkit-scrollbar {
width:12px;
}
/* 滚动槽 */
::-webkit-scrollbar-track {
box-shadow   : inset 0 0 5px rgba(0, 0, 0, 0.2);
border-radius:10px;
}
/* 滚动条滑块 */
::-webkit-scrollbar-thumb {
border-radius:10px;
background:rgba(0,0,0,0.1);
-webkit-box-shadow:inset006pxrgba(0,0,0,0.5);
}
::-webkit-scrollbar-thumb:window-inactive {
background:rgba(0,0,0,0.1);
}
</style>
