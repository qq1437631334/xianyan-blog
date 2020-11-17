<template>
  <div>
    <div class="my-container">
      <my-back />
      <recommend />
      <music />
      <my-link />
    </div>
  </div>
</template>

<script>
import MyBack from './my-back'
import Recommend from './recommend'
import Music from './music'
import MyLink from './link'
export default {
  components: {
    MyBack,
    Recommend,
    Music,
    MyLink
  }
}
</script>

<style scoped>
.my-container {
  width: 350px;
  padding: 0 10px;
}

</style>
