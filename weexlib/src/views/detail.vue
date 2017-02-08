<template>
  <div class="wrapper">
    <text class="weex">Hello Weex33 !</text>
    <text class="vue">Hello Vue1111 !</text>
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
      }
    },
    methods: {

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
  
  .weex {
    font-size: 60px;
    text-align: center;
    color: #1B90F7;
  }
  
  .vue {
    font-size: 60px;
    text-align: center;
    margin-top: 30px;
    color: #41B883;
  }
   .notice-view {
      height: 100%;
    }

    .notice-cell {
      margin-bottom: 3px;
      border-bottom-width: 2px;
      border-bottom-style: solid;
      border-bottom-color: #DDDDDD;
      background-color: #FFFFFF;
    }
</style>
