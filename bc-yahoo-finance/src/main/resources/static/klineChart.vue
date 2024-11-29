<template>
  <div>
    <div class="time-buttons">
      <button @click="setTimeInterval('1')" class="custom-button">1M</button>
      <button @click="setTimeInterval('5')" class="custom-button" >5M</button>
      <button @click="setTimeInterval('15')" class="custom-button">15M</button>
      <button @click="setTimeInterval('60')" class="custom-button">H</button>
      <button @click="setTimeInterval('D')" class="custom-button">D</button>
      <button @click="setTimeInterval('M')" class="custom-button">M</button>
      <button @click="setTimeInterval('W')" class="custom-button">W</button>
    </div>
    <div class="date-inputs">
      <label for="fromDate" class="custom-button">From Date:</label>
      <input type="date" id="fromDate" v-model="fromDate" @input="updateApiUrl" />
      <label for="toDate" class="custom-button">To Date:</label>
      <input type="date" id="toDate" v-model="toDate" @input="updateApiUrl" />
    </div>
    <div id="kline-chart" style="width: 600px; height: 600px;" ref="klineChart" class="chart-background"></div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import * as echarts from 'echarts';

export default {
  name: 'KlineChart',
  setup() {
    const klineChart = ref(null);
    const fromDate = ref('2022-01-01');
    const toDate = ref('Date.now');
    const time = ref('D') ;

    const setTimeInterval = (selectedTime) => {
      time.value = selectedTime || 'D'; // Default to '1 Day'
      initKlineChart(time.value);
    };

    const initKlineChart = (selectedTime) => {
      const myChart = echarts.init(klineChart.value);
      const symbol = 'TSLA';

      // Include fromDate and toDate in your apiUrl
      const apiUrl = `http://localhost:8085/api/v1/getCandleData?symbol=${symbol}&resolution=${selectedTime}&from=${fromDate.value}&to=${toDate.value}`;
      console.log("apiUrl : " + apiUrl);
      // ... Rest of your initKlineChart logic

function fetchData(){
fetch(apiUrl)
.then(response=>{
  if(!response.ok){
    throw new Error('Network response was not ok');
  }
  return response.json();
})
  .then(data => {
    // Handle the data here
    console.log(data);

    // Extract data
    var categoryData = data.t.map(timestamp => new Date(timestamp * 1000).toLocaleDateString()); // Convert timestamps to date strings
    var values = data.c.map((close, index) => [data.o[index], close, data.l[index], data.h[index]]);
    var vols = data.v;

    var ma10 = calculateMA(10, data);
    var ma50 = calculateMA(50, data);
    var ma100 = calculateMA(100, data);
    var ma250 = calculateMA(250, data);

    // ECharts option
    var option = {
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'cross'
        }
      },
      grid: [{
        left: '3%',
        top: '1%',
        height: '58%'
      }, {
        left: '3%',
        right: '10%',
        top: '65%',
        height: '10%'
      }, {
        left: '3%',
        right: '10%',
        top: '78%',
        height: '10%'
      }],
      xAxis: [{
        type: 'category',
        data: categoryData,
        scale: true,
        boundaryGap: false,
        axisLine: {
          onZero: false,
          lineStyle: {
            color: 'red',
          }
        },
        splitLine: {
          show: false
        },
        splitNumber: 20
      }, {
        type: 'category',
        gridIndex: 1,
        data: categoryData,
        axisLabel: {
          show: false
        },
      }, {
        type: 'category',
        gridIndex: 2,
        data: categoryData,
        axisLabel: {
          show: false
        },
      }],
      yAxis: [{
        scale: true,
        splitArea: {
          show: true
        },
        axisLine: {
          lineStyle: {
            color: 'red',
          }
        },
        position: 'right'
      }, {
        gridIndex: 1,
        splitNumber: 3,
        axisLine: {
          onZero: false,
          lineStyle: {
            color: 'red'
          }
        },
        axisTick: {
          show: false
        },
        splitLine: {
          show: false
        },
        axisLabel: {
          show: true
        },
        position: 'right'
      }, {
        gridIndex: 2,
        splitNumber: 4,
        axisLine: {
          onZero: false,
          lineStyle: {
            color: 'red'
          }
        },
        axisTick: {
          show: false
        },
        splitLine: {
          show: false
        },
        axisLabel: {
          show: true
        },
        position: 'right'
      }],
      dataZoom: [{
        type: 'inside',
        start: 100,
        end: 80
      }, {
        show: true,
        type: 'slider',
        y: '90%',
        xAxisIndex: [0, 1],
        start: 50,
        end: 100
      }, {
        show: false,
        xAxisIndex: [0, 2],
        type: 'slider',
        start: 20,
        end: 100
      }],
      series: [{
        name: 'Candlestick',
        type: 'candlestick',
        data: values,
        markPoint: {
          data: [{
            name: 'XX标点'
          }]
        },
        markLine: {
          silent: true,
          data: [{
            yAxis: 2222,
          }]
        }
      }, {
        name: 'MA10',
        type: 'line',
        data: ma10,
        smooth: true,
        lineStyle: {
          normal: {
            opacity: 1
          }
        }
      }, {
        name: 'MA50',
        type: 'line',
        data: ma50,
        smooth: true,
        lineStyle: {
          normal: {
            opacity: 2
          }
        }
      }, {
        name: 'MA100',
        type: 'line',
        data: ma100,
        smooth: true,
        lineStyle: {
          normal: {
            opacity: 3
          }
        }
      }, {
        name: 'MA250',
        type: 'line',
        data: ma250,
        smooth: true,
        lineStyle: {
          normal: {
            opacity: 4
          }
        }
      }, {
        name: 'Volume',
        type: 'bar',
        xAxisIndex: 1,
        yAxisIndex: 1,
        data: vols,
        itemStyle: {
          normal: {
            color: function (params) {
              var colorList;
              if (values[params.dataIndex][1] > values[params.dataIndex][0]) {
                colorList = '#ef232a';
              } else {
                colorList = '#14b143';
              }
              return colorList;
            },
          }
        }
      }]
    };

    // Set the option and render the chart
    myChart.setOption(option);
  })
  .catch(error => {
    console.error('Error fetching data:', error);
  });
}

// Set up a timer to call fetchData every, for example, 5 seconds (5000 milliseconds)
const interval = 100000; // 100 seconds
setInterval(fetchData, interval);

// Define calculateMA function
function calculateMA(dayCount, data) {
  var result = [];
  for (var i = 0, len = data.c.length; i < len; i++) {
    if (i < dayCount) {
      result.push('-');
      continue;
    }
    var sum = 0;
    for (var j = 0; j < dayCount; j++) {
      sum += data.c[i - j];
    }
    result.push((sum / dayCount).toFixed(2)); // Rounded to 2 decimal places
  }
  return result;
}

};

 

    const updateApiUrl = () => {
      initKlineChart(time.value);
    };

    onMounted(() => {
      initKlineChart(time.value);
    });

    return {
      klineChart,
      fromDate,
      toDate,
      setTimeInterval,
      updateApiUrl,
    };
  },
};
</script>

<style scoped>
.custom-button {
  /* 按钮的通用样式 */
  padding: 5px 10px;
  background-color: #007BFF;
  color: #312a2a;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin: 5px;
}

/* 不同按钮的特定样式 */
.custom-button:nth-child(odd) {
  /* 1 Minute 和 15 Minutes 按钮的样式 */
  background-color: #5bc0de;
}

.custom-button:nth-child(even) {
  /* 5 Minutes 和 1 Day 按钮的样式 */
  background-color: #d9534f;
}

.chart-background{
  background-color: #fff;
}</style>