<template>
  <div>
    <main id="main" class="main">
      <section class="section dashboard">
        <div class="row">

          <!-- Left side columns -->
          <div class="col-lg-8">
            <div class="row">

              <!-- Sales Card -->
              <div class="col-xxl-4 col-md-6">
                <div class="card info-card sales-card">

                  <div class="filter">
                    <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                      <li class="dropdown-header text-start">
                        <h6>Filter</h6>
                      </li>

                      <li><a class="dropdown-item" href="#">Today</a></li>
                      <li><a class="dropdown-item" href="#">This Month</a></li>
                      <li><a class="dropdown-item" href="#">This Year</a></li>
                    </ul>
                  </div>

                  <div class="card-body">
                    <h5 class="card-title">업데이트 공고 <span>| Today</span></h5>

                    <div class="d-flex align-items-center">
                      <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-pin-angle" viewBox="0 0 16 16">
                          <path d="M9.828.722a.5.5 0 0 1 .354.146l4.95 4.95a.5.5 0 0 1 0 .707c-.48.48-1.072.588-1.503.588-.177 0-.335-.018-.46-.039l-3.134 3.134a5.927 5.927 0 0 1 .16 1.013c.046.702-.032 1.687-.72 2.375a.5.5 0 0 1-.707 0l-2.829-2.828-3.182 3.182c-.195.195-1.219.902-1.414.707-.195-.195.512-1.22.707-1.414l3.182-3.182-2.828-2.829a.5.5 0 0 1 0-.707c.688-.688 1.673-.767 2.375-.72a5.922 5.922 0 0 1 1.013.16l3.134-3.133a2.772 2.772 0 0 1-.04-.461c0-.43.108-1.022.589-1.503a.5.5 0 0 1 .353-.146zm.122 2.112v-.002.002zm0-.002v.002a.5.5 0 0 1-.122.51L6.293 6.878a.5.5 0 0 1-.511.12H5.78l-.014-.004a4.507 4.507 0 0 0-.288-.076 4.922 4.922 0 0 0-.765-.116c-.422-.028-.836.008-1.175.15l5.51 5.509c.141-.34.177-.753.149-1.175a4.924 4.924 0 0 0-.192-1.054l-.004-.013v-.001a.5.5 0 0 1 .12-.512l3.536-3.535a.5.5 0 0 1 .532-.115l.096.022c.087.017.208.034.344.034.114 0 .23-.011.343-.04L9.927 2.028c-.029.113-.04.23-.04.343a1.779 1.779 0 0 0 .062.46z"/>
                        </svg>
                      </div>
                      <div class="ps-3">
                        <h6>{{card.updated.today}}</h6>
                        <span class="text-muted small pt-2 ps-1">전일대비 </span><span
                          class="text-success small pt-1 fw-bold">{{card.updated.compare}}</span> <span
                          class="text-muted small pt-2 ps-1">증가</span>
                      </div>
                    </div>
                  </div>

                </div>
              </div><!-- End Sales Card -->

              <!-- Revenue Card -->
              <div class="col-xxl-4 col-md-6">
                <div class="card info-card revenue-card">

                  <div class="filter">
                    <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                      <li class="dropdown-header text-start">
                        <h6>Filter</h6>
                      </li>

                      <li><a class="dropdown-item" href="#">Today</a></li>
                      <li><a class="dropdown-item" href="#">This Month</a></li>
                      <li><a class="dropdown-item" href="#">This Year</a></li>
                    </ul>
                  </div>

                  <div class="card-body">
                    <h5 class="card-title">자격요건<span> | Today</span></h5>

                    <div class="d-flex align-items-center">
                      <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-stack" viewBox="0 0 16 16">
                          <path d="m14.12 10.163 1.715.858c.22.11.22.424 0 .534L8.267 15.34a.598.598 0 0 1-.534 0L.165 11.555a.299.299 0 0 1 0-.534l1.716-.858 5.317 2.659c.505.252 1.1.252 1.604 0l5.317-2.66zM7.733.063a.598.598 0 0 1 .534 0l7.568 3.784a.3.3 0 0 1 0 .535L8.267 8.165a.598.598 0 0 1-.534 0L.165 4.382a.299.299 0 0 1 0-.535L7.733.063z"/>
                          <path d="m14.12 6.576 1.715.858c.22.11.22.424 0 .534l-7.568 3.784a.598.598 0 0 1-.534 0L.165 7.968a.299.299 0 0 1 0-.534l1.716-.858 5.317 2.659c.505.252 1.1.252 1.604 0l5.317-2.659z"/>
                        </svg>
                      </div>
                      <div class="ps-3">
                        <h6>{{card.topRequirement.lang}}</h6>
                        <span class="text-success small pt-1 fw-bold">{{card.topRequirement.share}}%</span> <span
                          class="text-muted small pt-2 ps-1">of the total</span>

                      </div>
                    </div>
                  </div>

                </div>
              </div><!-- End Revenue Card -->

              <!-- Customers Card -->
              <div class="col-xxl-4 col-xl-12">

                <div class="card info-card customers-card">

                  <div class="card-body">
                    <h5 class="card-title">우대사항 <span>| Today</span></h5>

                    <div class="d-flex align-items-center">
                      <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-stack-overflow" viewBox="0 0 16 16">
                          <path d="M12.412 14.572V10.29h1.428V16H1v-5.71h1.428v4.282h9.984z"/>
                          <path d="M3.857 13.145h7.137v-1.428H3.857v1.428zM10.254 0 9.108.852l4.26 5.727 1.146-.852L10.254 0zm-3.54 3.377 5.484 4.567.913-1.097L7.627 2.28l-.914 1.097zM4.922 6.55l6.47 3.013.603-1.294-6.47-3.013-.603 1.294zm-.925 3.344 6.985 1.469.294-1.398-6.985-1.468-.294 1.397z"/>
                        </svg>
                      </div>
                      <div class="ps-3">
                        <h6>{{card.topPrefer.lang}}</h6>
                        <span class="text-danger small pt-1 fw-bold">{{card.topPrefer.share}}%</span> <span
                          class="text-muted small pt-2 ps-1">of the total</span>

                      </div>
                    </div>

                  </div>
                </div>

              </div><!-- End Customers Card -->

              <!-- Reports -->
              <div class="col-12">
                <div class="card">
                  <div class="card-body">
                    <h5 class="card-title">Reports <span>/Today</span></h5>

                    <!-- Line Chart -->
                    <div id="reportsChart">
                      <!-- Apex Chart -->
                      <apexchart :options="mainChart.chartOptions" :series="mainChart.series"/>
                    </div>
                  </div>

                </div>
              </div><!-- End Reports -->

            </div>
          </div><!-- End Left side columns -->

          <!-- Right side columns -->
          <div class="col-lg-4">

            <!-- Sidebar charts -->
            <div class="card">
              <div class="card-body">
                <h5 class="card-title-chart">주요업무 <span>| Top3</span></h5>
                <!-- Apex Chart -->
                <apexchart :options="sideChart1.chartOptions" :series="sideChart1.series"/>
              </div>
            </div>
            <!-- End Sidebar charts -->

            <!-- Recent Activity -->
            <div class="card">
              <div class="card-body">
                <h5 class="card-title-chart">자격요건 <span>| Top3</span></h5>
                <!-- Apex Chart -->
                <apexchart :options="sideChart2.chartOptions" :series="sideChart2.series"/>
              </div>
            </div><!-- End Recent Activity -->

            <!-- Budget Report -->
            <div class="card">
              <div class="card-body">
                <h5 class="card-title-chart">우대사항 <span> | Top3</span></h5>

                <div id="budgetChart" class="echart">
                  <!-- Apex Chart -->
                  <apexchart :options="sideChart3.chartOptions" :series="sideChart3.series"/>
                </div>

              </div>
            </div><!-- End Budget Report -->
          </div><!-- End Right side columns -->

        </div>
      </section>

    </main><!-- End #main -->


  </div>
</template>

<script>
import mixins from '@/mixins'
import jsonData from '@/assets/jsons/chart_data.json'

export default {
  name: "WantedMainView", //컴포넌트 이름
  components: {}, //다른 컴포넌트 사용 시 import(배열로 등록)
  mixins : [mixins],
  data() { //html과 js코드에서 사용할 데이터 변수 선언
    return {
      card : {
        updated : {
          today : 0,
          compare : 0
        },
        topRequirement : {
          lang : '',
          share : 0.0
        },
        topPrefer : {
          lang : '',
          share : 0.0
        }
      },
    };
  },
  setup() {
  }, //컴포지션 API
  created() {
    this.initCard()
    this.initChart()
  }, //컴포넌트가 생성되면 실행
  mounted() {

  }, //template에 정의된 html 코드가 랜더링된 후 실행
  unmounted() {
  }, //unmount가 완료된 후 실행
  methods: {
    // async apiCallByMixins() {
    //   // this.testHello = await this.$api('/api/v1/hello', 'get')
    // },
    initCard(){
      // 업데이트
      this.card.updated.today = jsonData.updatedCnt;
      this.card.updated.compare = jsonData.comparedCnt;

      // 자격요건
      this.card.topRequirement.lang = jsonData.topLangInfo.data.REQUIREMENT.lang
      this.card.topRequirement.lang = this.card.topRequirement.lang.toUpperCase()
      this.card.topRequirement.share = jsonData.topLangInfo.data.REQUIREMENT.share

      // 우대사항
      this.card.topPrefer.lang = jsonData.topLangInfo.data.PREFER.lang
      this.card.topPrefer.lang = this.card.topPrefer.lang.toUpperCase()
      this.card.topPrefer.share = jsonData.topLangInfo.data.PREFER.share
    },
    initChart() { // 차트 init
      // vue3-apex charts for pie example from https://apexcharts.com/vue-chart-demos/pie-charts/simple-pie-chart/

      // main chart
      let mainChartSeries = Object.values(jsonData.totalLangCnt.data.REQUIREMENT)
      let mainChartLabels = Object.keys(jsonData.totalLangCnt.data.REQUIREMENT)
      let mainChartColors = []
      mainChartLabels.forEach(key => mainChartColors.push(jsonData.langColor.data[key]))

      this.mainChart = {
        series: mainChartSeries,
        chartOptions: {
          colors :mainChartColors,
          chart: {
            type: 'pie'
          },
          labels: mainChartLabels,
          dataLabels: {
            formatter(val, opts) {
              const name = opts.w.globals.labels[opts.seriesIndex]
              return [name, val.toFixed(1) + '%']
            }
          }
        }
      }

      // side chart 의 날짜 만들기 function (langType = java, python ..., firstLang = langType 의 첫번째 언어)
      function createDates(langType, firstLang) {
        let mainDate = ''
        let leftDates = []

        Object.keys(jsonData.top3LangTrend.data[langType][firstLang]).forEach(function (dates, index, array){
              if (index == array.length - 1) { mainDate = dates.substr(5).replace('-', '/')} // 2021-04-01 -> 04/01
              else {
                let day = dates.substr(8)
                day = day[0] == '0' ? day.substr(1) : day  // 날짜 03 -> 3 으로 자름
                leftDates.push(day)
              }
            }
        )
        mainDate =  mainDate[0] == '0' ? mainDate.substr(1) : mainDate  // 04/01 -> 4/01
        mainDate = mainDate[mainDate.length - 2] == '0' ? mainDate.substr(0, mainDate.length-2) + mainDate.substr(mainDate.length - 1) : mainDate; // 4/01 -> 4/1

        let result = leftDates.reverse()
        result.unshift(mainDate)

        return result // ['4/01', '2', '3', '4', ...]
      }

      // json 기반으로 chartData 만듦
      function createChartDataList() {
        let langTypeList = Object.keys(jsonData.top3LangTrend.data) // [main, requirement, prefer]

        let result = []

        langTypeList.forEach(langType => {
          let dataSeries = []
          let dataColors = []

          let dataObj = jsonData.top3LangTrend.data[langType]

          Object.keys(dataObj).forEach(lang => { // [java, python, c]
            let obj = {name : lang, data : Object.values(dataObj[lang])} // data : [30, 25, 20]
            dataSeries.push(obj)
          })

          // 언어별 컬러코드
          Object.keys(dataObj).forEach(key => dataColors.push(jsonData.langColor.data[key]))

          let dataDates = createDates(langType, dataSeries[0].name)

          let chartData = {
            series: dataSeries,
            chartOptions: {
              chart: {
                height: 350,
                type: 'area',
                zoom: {
                  enabled: false
                },
                toolbar: {
                  show: false
                }
              },
              dataLabels: {
                enabled: false
              },
              colors: dataColors,
              markers:{
                size: 4
              },
              stroke: {
                curve: 'smooth',
                width: 2
              },
              grid: {
                row: {
                  colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
                  opacity: 0.5
                },
              },
              xaxis: {
                categories: dataDates,
              }
            }
          }

          result.push(chartData)

        })
        return result
      }

      let chartDataList = createChartDataList();

      this.sideChart1 = chartDataList[0]
      this.sideChart2 = chartDataList[1]
      this.sideChart3 = chartDataList[2]

    }
  }
}
</script>

<style scoped>

</style>

