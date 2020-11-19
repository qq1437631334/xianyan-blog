<template>
  <div>
    <div class="index-carousel">
      <a-carousel autoplay>
        <div>
          <img src="../../assets/img/carousel.gif" class="carousel-image">
        </div>
      </a-carousel>
    </div>
    <blog-list :name="'博客'" />
  </div>
</template>
<script>
import BlogList from '@/views/blog/blog-list'
export default {
  components: {
    BlogList
  }
}
</script>
<style>
.carousel-image {
  width: 100%;
  height: 100%;
}
</style>
