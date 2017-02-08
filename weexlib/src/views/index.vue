<template>
  <div class="wrapper">
    <text class="add" @click="num">点击:{{count}}</text>
    <list class="notice-list">
      <cell class="notice-cell" v-for="notice in notices" append="tree">
        <notice :notice="notice"></notice>
      </cell>
    </list>
  </div>
</template>

<script>
  import notice from '../components/notice.vue'

  export default {
    components: {
      notice
    },
    computed: {
      notices() {
        return this.$store.state.notices
      },
      count() {
        return this.$store.state.sum
      }
    },
    methods: {
      num() {
        console.log('click num')
        this.$store.dispatch('ADD_NUM')
      }
    },
    created() {
      this.$store.dispatch('FETCH_LIST_NOTICE')
    }
  }

</script>

<style scoped>
  .wrapper {
    flex-direction: column;
    justify-content: center;
  }
  
  .add {
    font-size: 60px;
    text-align: center;
    color: #1B90F7;
  }
  
  .notice-cell {
    margin-bottom: 3px;
    border-bottom-width: 2px;
    border-bottom-style: solid;
    border-bottom-color: #DDDDDD;
    background-color: #FFFFFF;
  }
</style>