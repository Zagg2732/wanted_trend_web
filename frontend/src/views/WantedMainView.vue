<template>
  <div>
    <main id="main" class="main">
      <section class="section dashboard">
        <div class="row">

          <!-- Left side columns -->
          <div class="col-lg-8">
            <div class="row">

              <!-- Card -->
              <div class="col-xxl-4 col-md-6">
                <div class="card info-card sales-card">
                  <div class="card-body">
                    <h5 class="card-title">업데이트 공고 <span>| {{ recentDate }}</span></h5>

                    <div class="d-flex align-items-center">
                      <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                        <i class="bi bi-pin-angle"></i>
                      </div>
                      <div class="mx-auto">
                        <h6>{{card.updated.today}}</h6>
                        <span class="text-muted small pt-2 ps-1">전일대비 </span><span
                          class="text-success small pt-1 fw-bold">{{card.updated.compare}}</span> <span
                          class="text-muted small pt-2 ps-1">{{card.updated.text}}</span>
                      </div>
                    </div>
                  </div>

                </div>
              </div>
              <!-- Card -->

              <!-- Card -->
              <div class="col-xxl-4 col-md-6">
                <div class="card info-card revenue-card">
<!--                  <div class="filter">-->
<!--                    <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-list"></i></a>-->
<!--                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">-->
<!--                      <li class="dropdown-header text-start">-->
<!--                        <h6>Filter</h6>-->
<!--                      </li>-->

<!--                      <li><a class="dropdown-item" href="#">Today</a></li>-->
<!--                      <li><a class="dropdown-item" href="#">Week</a></li>-->
<!--                      <li><a class="dropdown-item" href="#">Month</a></li>-->
<!--                    </ul>-->
<!--                  </div>-->
                  <div class="card-body">
                    <h5 class="card-title">자격요건<span> | {{ recentDate }}</span></h5>

                    <div class="d-flex align-items-center">
                      <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                        <i class="bi bi-stack"></i>
                      </div>
                      <div class="mx-auto">
                        <h6>{{card.topRequirement.lang}}</h6>
                        <span class="text-success small pt-1 fw-bold">{{card.topRequirement.share}}%</span>

                      </div>
                    </div>
                  </div>

                </div>
              </div>
              <!-- End Card -->

              <!-- Card -->
              <div class="col-xxl-4 col-xl-12">

                <div class="card info-card customers-card">
<!--                  <div class="filter">-->
<!--                    <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-list"></i></a>-->
<!--                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">-->
<!--                      <li class="dropdown-header text-start">-->
<!--                        <h6>Filter</h6>-->
<!--                      </li>-->

<!--                      <li><a class="dropdown-item" href="#">Today</a></li>-->
<!--                      <li><a class="dropdown-item" href="#">Week</a></li>-->
<!--                      <li><a class="dropdown-item" href="#">Month</a></li>-->
<!--                    </ul>-->
<!--                  </div>-->
                  <div class="card-body">
                    <h5 class="card-title">우대사항 <span>| {{ recentDate }}</span></h5>

                    <div class="d-flex align-items-center">
                      <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-stack-overflow" viewBox="0 0 16 16">
                          <path d="M12.412 14.572V10.29h1.428V16H1v-5.71h1.428v4.282h9.984z"/>
                          <path d="M3.857 13.145h7.137v-1.428H3.857v1.428zM10.254 0 9.108.852l4.26 5.727 1.146-.852L10.254 0zm-3.54 3.377 5.484 4.567.913-1.097L7.627 2.28l-.914 1.097zM4.922 6.55l6.47 3.013.603-1.294-6.47-3.013-.603 1.294zm-.925 3.344 6.985 1.469.294-1.398-6.985-1.468-.294 1.397z"/>
                        </svg>
                      </div>
                      <div class="mx-auto">
                        <h6>{{card.topPrefer.lang}}</h6>
                        <span class="text-danger small pt-1 fw-bold">{{card.topPrefer.share}}%</span>

                      </div>
                    </div>

                  </div>
                </div>

              </div>
              <!-- End Card -->

              <!-- Main pie-chart -->
              <div class="col-12">
                <div class="card">
                  <div class="card-body">
                    <h5 class="card-title">Total <span>/자격요건</span></h5>

                    <!-- Line Chart -->
                    <div id="reportsChart">
                      <!-- Apex Chart -->
                      <apexchart :options="mainChart.chartOptions" :series="mainChart.series"/>
                    </div>
                  </div>

                </div>
              </div>
              <!-- End Main pie-chart -->

            </div>
          </div>
          <!-- End Left side columns -->

          <!-- Right side columns -->
          <div class="col-lg-4">

            <!-- Sidebar charts -->
            <div class="card">
              <div class="card-body-chart">
                <h5 class="card-title-chart">주요업무 <span>| Top3</span></h5>
                <!-- Apex Chart -->
                <apexchart :options="sideChart1.chartOptions" :series="sideChart1.series"/>
              </div>
            </div>
            <!-- End Sidebar charts -->

            <!-- Sidebar charts -->
            <div class="card">
              <div class="card-body-chart">
                <h5 class="card-title-chart">자격요건 <span>| Top3</span></h5>
                <!-- Apex Chart -->
                <apexchart :options="sideChart2.chartOptions" :series="sideChart2.series"/>
              </div>
            </div>
            <!-- End Sidebar charts -->

            <!-- Sidebar charts -->
            <div class="card">
              <div class="card-body-chart">
                <h5 class="card-title-chart">우대사항 <span> | Top3</span></h5>

                <div id="budgetChart" class="echart">
                  <!-- Apex Chart -->
                  <apexchart :options="sideChart3.chartOptions" :series="sideChart3.series"/>
                </div>

              </div>
            </div>
            <!-- End Sidebar charts -->
          </div>
          <!-- End Right side columns -->

        </div>
      </section>

    </main><!-- End #main -->

  </div>
</template>

<script>
import mixins from '@/mixins'

export default {
  name: "WantedMainView", //컴포넌트 이름
  components: {}, //다른 컴포넌트 사용 시 import(배열로 등록)
  mixins : [mixins],
  data() { //html과 js코드에서 사용할 데이터 변수 선언
    return {
      recentDate : '',
      card : {
        updated : {
          today : 0,    // 오늘 업데이트 공고
          compare : 0,  // 전날과 비교한 업데이트 공고 증감
          text : ''     // 증가 / 감소 text
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
    this.init()
  }, //컴포넌트가 생성되면 실행
  mounted() {
  }, //template에 정의된 html 코드가 랜더링된 후 실행
  unmounted() {
  }, //unmount가 완료된 후 실행
  methods: {
    init(){
      this.$api('/api/v1/json', 'get').then((str) => {  // json file call
        const jsonData = JSON.parse(JSON.stringify(str))
        this.initCard(jsonData)
        this.initChart(jsonData)
      })
    },
    // 페이지 상단 3개의 카드 정보
    initCard(jsonData){
      // 데이터 날짜
      this.recentDate = jsonData.date

      // 업데이트
      this.card.updated.today = jsonData.updatedCnt
      this.card.updated.compare = Math.abs(jsonData.comparedCnt)
      this.card.updated.text = jsonData.comparedCnt >= 0 ? '증가' : '감소'

      // 자격요건
      this.card.topRequirement.lang = jsonData.topLangInfo.data.REQUIREMENT.lang
      this.card.topRequirement.lang = this.card.topRequirement.lang.toUpperCase()
      this.card.topRequirement.share = jsonData.topLangInfo.data.REQUIREMENT.share

      // 우대사항
      this.card.topPrefer.lang = jsonData.topLangInfo.data.PREFER.lang
      this.card.topPrefer.lang = this.card.topPrefer.lang.toUpperCase()
      this.card.topPrefer.share = jsonData.topLangInfo.data.PREFER.share
    },
    // 페이지 중앙, 우측 차트 정보
    initChart(jsonData) { // 차트 init
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
      function createDates(jsonData, langType, firstLang) {
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
      function createChartDataList(jsonData) {
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

          let dataDates = createDates(jsonData, langType, dataSeries[0].name)

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

      let chartDataList = createChartDataList(jsonData);

      this.sideChart1 = chartDataList[0]
      this.sideChart2 = chartDataList[1]
      this.sideChart3 = chartDataList[2]
    }
  }
}
</script>

<style scoped>

</style>

