// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import VueI18n from 'vue-i18n';


// 国际化
Vue.use(VueI18n);
const i18n = new VueI18n({
  locale: 'zh_CN',
  messages: {
    'zh_CN': require('./assets/js/lang/zh_CN'),
    'zh_TW': require('./assets/js/lang/zh_TW'),
    'en': require('./assets/js/lang/en'),
    'ja': require('./assets/js/lang/ja'),
    'ko': require('./assets/js/lang/ko')
  }
})

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  i18n,
  components: {App},
  template: '<App/>'
})
