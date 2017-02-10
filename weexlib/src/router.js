import Router from 'vue-router'


Vue.use(Router)

import HomeView from './views/index.vue'
import DetailView from './views/detail.vue'
import TestView from './views/test.vue'

export default new Router({
    routes: [
        { path: '/', component: HomeView },
        { path: '/detail/:id', component: DetailView },
        { path: '/test',component:TestView}
    ]
})