import Router from 'vue-router'


Vue.use(Router)

import DetailView from './views/details.vue'
import HomeView from './views/index.vue'

export default new Router({
    routes: [
        { path: '/', component: HomeView },
        { path: '/detail', component: DetailView }
    ]
})