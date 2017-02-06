// import Vue from 'vue'
import Vuex from 'vuex'

if (WXEnvironment.platform !== 'Web') {
  Vue.use(Vuex)
}

const store = new Vuex.Store({
  
})

export default store
