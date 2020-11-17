<template>
  <div>
    <a-card title="音乐欣赏" :head-style="headStyle">
      <div>
        <aplayer :audio="musicList" :autoplay="autoplay" />
      </div>
    </a-card>
  </div>
</template>
<script>
import musicApi from '@/api/music'
export default {
  data() {
    return {
      headStyle: {
        fontSize: '18px',
        fontWeight: 'bold',
        lineHeight: '15px',
        borderLeft: '5px solid #409eff'
      },
      musicList: [],
      autoplay: true
    }
  },
  created() {
    this.getMusics()
  },
  methods: {
    getMusics() {
      musicApi.getList().then(res => {
        this.musicList = res.data
      })
    }
  }
}
</script>
