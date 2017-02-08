<template>
    <div class="cell-notice">
    <text class="title" @click="jump(`/detail/${notice.id}`)">{{notice.title}}</text>
    </div>
</template>

<style scoped>
.cell-notice{
   position:relative;
   padding-top: 20px;
   padding-left: 20px;
   padding-top: 20px;
   padding-right: 20px;
}
.title{
    font-size: 33px;
    color: #404040;
}
</style>

<script>
 export default{
     props:{
         notice:{
             type:Object,
             required:true
         }
     }
 }
</script>
