import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import VueApexCharts from 'vue3-apexcharts'
import mixins from '@/mixins'

import 'bootstrap/dist/js/bootstrap.bundle.min'
import 'bootstrap/dist/css/bootstrap.min.css'
import '../scss/custom.scss'

const app = createApp(App)
app.use(router)
app.use(VueApexCharts)
app.use(mixins)
app.mount('#app')
