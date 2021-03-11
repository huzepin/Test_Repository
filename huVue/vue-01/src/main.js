// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vant from 'vant'
import 'vant/lib/index.css'
import 'vant/lib/icon/local.css'
/* import { Cell, CellGroup, Button, Toast, GoodsAction, GoodsActionIcon, GoodsActionButton } from 'vant' */
Vue.config.productionTip = false
Vue.use(Vant)
/*
Vue.use(Cell)
Vue.use(CellGroup)
Vue.use(Button)
Vue.use(Toast)
Vue.use(GoodsAction)
Vue.use(GoodsActionIcon)
Vue.use(GoodsActionButton)
*/
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router: router,
  components: { App },
  template: '<App/>'

})
