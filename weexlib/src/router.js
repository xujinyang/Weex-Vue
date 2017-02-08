import Router from 'vue-router'


Vue.use(Router)

import HomeView from './views/index.vue'
import DetailView from './views/detail.vue'

export default new Router({
    routes: [
        { path: '/', component: HomeView },
        { path: '/detail', component: DetailView }
    ]
})