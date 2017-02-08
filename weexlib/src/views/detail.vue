<template>
  <div class="wrapper">
    <text class="title">标题：{{notice.title}}</text>
    <text class="content">内容：{{notice.content}}</text>
  </div>
</template>

<script>
export default{
  computed:{
    notice(){
       if (this.$route && this.$route.params) {
          return this.$store.state.notices[this.$route.params.id]
        }
        return {title:"wc",content:"fuck"}
    }
  }
}
</script>

<style scoped>
  .wrapper {
    flex-direction: column;
    justify-content: center;
  }
  
  .title {
    font-size: 60px;
    text-align: center;
    color: #1B90F7;
  }
  
  .content {
    font-size: 60px;
    text-align: center;
    margin-top: 30px;
    color: #41B883;
  }
</style>
