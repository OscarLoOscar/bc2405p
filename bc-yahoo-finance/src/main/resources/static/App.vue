<template>
  <div id="app">
    <h1 class="caption p-4">Stock Order Book</h1>
    <div class="order-book-container">

        <div class="order-form">
          <span class="button-header-stock-symbol">Stock Symbol :&nbsp;  
            <el-select v-model="stockSymbol" class="custom-dropdown">
              <el-option
                v-for="(option, index) in stockSymbolOptions"
                :key="index"
                :label="option"
                :value="option"
              ></el-option>
            </el-select>
          </span>

      <ul class="flex-container">
          <li>
          <!-- Action Dropdown -->
          <span class="button-header-action action-header">
            Action&nbsp;
            <el-select v-model="buySellSelectedOption" class="custom-dropdown action-header">
              <el-option
                v-for="(option, index) in buySellOptions"
                :key="index"
                :label="option"
                :value="index"
              ></el-option>
            </el-select>
          </span>
        </li>
    <!-- price -->
          <li>
          <span class="button-header-price price-header">Price&nbsp;
            <!-- <div class="custom-input-number price-header"> -->
              <div class="price-header">
              <el-input-number v-model="price_input" :min="0" :max="10000" :step="0.1"></el-input-number>
            </div>
          </span>
        </li>  
   
           <li>
          <!-- Order Type Dropdown -->
          <span class="button-header-type order-header">
            Type&nbsp;&nbsp;
            <el-select v-model="orderTypeSelectedOption" class="custom-dropdown order-header">
              <el-option
                v-for="(option, index) in orderTypeOptions"
                :key="index"
                :label="option"
                :value="index"
              ></el-option>
            </el-select>
          </span>
        </li>

          <!-- shares -->
        <li>
          <span class="button-header-qty quantity-header">Qty&nbsp;

            <!-- <div class="custom-input-number quantity-header"> -->
            <div class="quantity-header">
              <el-input-number v-model="quantity_input" :min="0" :max="1000" :step="1"></el-input-number>
            </div>
          </span>
        </li>
      </ul>
          <!-- Total Order value -->
          <!-- <el-row class="button-header">Total Order Value</el-row>
          <el-row>
            <div class="custom-input-order-value">
              <el-input-number v-model="total_order_value_input" :min="1" :max="10000000000"
                :controls="false"></el-input-number>
            </div> 
          </el-row> -->
          <el-row>

            <div class="place-order-button">
              <el-button type="primary" @click="placeOrder">Place Order</el-button>
            </div>
        
          </el-row>
        </div>

<!-- bid queue + ask queue -->
    <div>
  <div class="order-book-tables" bottom>
        <div class="order-book-buy"  >
          <el-table :data="buyOrders" :max-height="480" >
            <el-table-column label="Quantity" prop="quantity" align="center"></el-table-column>
            <el-table-column label="Buy (Bid)" prop="price" align="center"></el-table-column>
          </el-table>
        </div>
        <div class="order-book-sell"  >
          <el-table :data="sellOrders" :max-height="480" >
            <el-table-column label="Sell (Ask)" prop="price" align="center"></el-table-column>
            <el-table-column label="Quantity" prop="quantity" align="center"></el-table-column>
          </el-table>
        </div>
      </div>
      </div>

      <klineChart/></div>


    </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import * as echarts from 'echarts';
import 'echarts/theme/macarons'; // Optional: Add a theme for your chart
import klineChart from './model/klineChart.vue';
 // Import the KlineChart component

export default {
  name: 'App',
  components: {
    klineChart,
  },
  setup() {
      const    price_input= ref(256);
      const   quantity_input= ref(100);
      const   total_order_value_input=ref( price_input*quantity_input);
      const stockSymbol = ref('TSLA');
      const stockSymbolOptions = ['TSLA','AAPL','MSFT'];

    // Buy Sell Option
    const buySellSelectedOption = ref(0);
    const buySellOptions = ['BUY', 'SELL'];
    const buySellSelectOption = (buySellIndex) => {
      buySellSelectedOption.value = buySellIndex;
    };

    // Order Type Option
    const orderTypeSelectedOption = ref(0);
    const orderTypeOptions = ['MARKET', 'LIMIT'];
    const orderTypeSelectOption = (orderTypeIndex) => {
      orderTypeSelectedOption.value = orderTypeIndex;
    };
    
    const buyOrders = ref([]);
    const sellOrders = ref([]);

    const retrieveQueue = async () => {
const symbol = stockSymbol.value;
      try {
        const responseBuy = await axios.get(
          `http://localhost:8085/transactions/bidQueue?stockId=${symbol}`
        );
        const responseAsk = await axios.get(
          `http://localhost:8085/transactions/askQueue?stockId=${symbol}`
        );
        sellOrders.value = responseAsk.data;
        buyOrders.value = responseBuy.data;
      } catch (err) {
        console.log(err);
      }
    };

    // Function to place an order
    const placeOrder = () => {
  // Access the form data directly from the setup context
   // Ensure that form.price_input and form.quantity_input are valid
   if (isNaN(price_input.value) || isNaN(quantity_input.value) || price_input.value <= 0 || quantity_input.value <= 0) {
        console.error('Invalid price or quantity');
        return;
      }
      const symbol= stockSymbol.value;
      const action= buySellOptions[buySellSelectedOption.value]; // Use the selected option index to get the value
      const orderType= orderTypeOptions[orderTypeSelectedOption.value]; // Use the selected option index to get the value
      const price= price_input.value;
      const quantity= quantity_input.value;
    // Include other request data here

  // Construct the URL with the selected parameters
  const url = `http://localhost:8085/sumit/trade/symbol/${symbol}`;

  // Create an Axios configuration object
 // Create the data object
 const data = new URLSearchParams();
  data.append('action', action);
  data.append('orderType', orderType);
  data.append('price', price);
  data.append('quantity', quantity);

  // Send the POST request using Axios
 // Make the POST request with the URL and data
  axios.post(url, data, {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    },
  })
    .then(() => {
      console.log('Order placed successfully');
    })

    .catch(error => {
      console.error('Error placing order:', error);
    });
};    // Fetch data initially
    onMounted(() => {
      retrieveQueue();
    });

    // Fetch data periodically every 10 seconds
    setInterval(() => {
      retrieveQueue();
    }, 2000);

    // Update buyOrders and sellOrders when buys and asks data change
    const updateOrders = () => {
      buyOrders.value = buyOrders.value.map(buy => ({ price: buy.price, share: buy.quantity }));
      sellOrders.value = sellOrders.value.map(ask => ({ price: ask.price, share: ask.quantity }));
    };

    // Watch for changes in buys and asks data and update orders accordingly
    onMounted(() => {
      updateOrders();
    });

    return {
      stockSymbol,
      price_input,
      quantity_input,
      total_order_value_input,
      buyOrders,
      sellOrders,
      stockSymbolOptions,
      buySellSelectedOption,
      buySellOptions,
      buySellSelectOption,
      orderTypeSelectedOption,
      orderTypeOptions,
      orderTypeSelectOption,
      placeOrder,
      updateOrders,
      klineChart
    };
  },
};
</script>

<style>
body::before {
  content: '';
  background-image: url('https://asseco.com/files/public/_processed_/csm_baner_wyniki_small_612a732bf5.jpg');
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  opacity: 0.9;
  /* Adjust the opacity level (0.0 to 1.0) */
  z-index: -1;
}

.flex-container {
  display: grid;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: left;
  list-style: none;
  padding: 0;
  margin-left: -50px;
  /* grid-template-columns: repeat(3,1fr);
  grid-template-rows: 1fr 2fr 2fr 1fr;
  grid-template-areas: 
  "action-header"
  "price-header"
  "order-header"
  "quantity-header"
  ; */
}

.caption {
  text-align: center;
  font-size: 33px;
  font-weight: bold;
  color: rgb(237, 230, 230);
  width: 100%;
}
.order-book-container {
  display: flex;
  justify-content: space-between;
  border-radius: 10px;
  border-style: groove;
}

.order-book-buy {
  width: 47%;
  margin-left: 30px;

}

.order-book-sell {
  width: 47%;
  margin-left: -40px;

}

/* Styling for the order form and panel */
.order-form {
  width: 400px;
  height:200px;
  display: flex;
  flex-direction: column;
  align-items: left;
  /* Center content horizontally */
  margin-left: 30px;
  background-color: #333;
  /* Light gray background */
  border: 2px solid #b2bfe0;
  border-radius: 5px;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  /* Box shadow for depth */
}
.order-form ul {
  padding: 0;
  border-collapse: collapse;
  display: flex;
  /*div 換行*/
  flex-wrap: wrap;
  /*主軸對齊*/
  justify-content: space-evenly;
  /*行對齊*/
  align-content: space-between;
  /* padding: 50px 180px 90px 50px; */
  height: 118px;
  margin-bottom: -10px;

    }

    .order-form .ul li{
      justify-content: space-evenly;
      align-content: space-between;
    }

    .custom-dropdown{
    width: 130px;
    align-items: center;
    }

    .action-header{
  margin-left: 7px;
  margin-right: 0px; /* Adjust this value to control the left margin */
  display: grid;
 align-items: center;
}
.price-header{
  margin-right: -9px;
  margin-left: 0px; /* Adjust this value to control the left margin */
  margin-top: 0px;
  display: grid;
  align-items: center;
}

.quantity-header {
  margin-right: -8px;
  margin-left: 0px; /* Adjust this value to control the left margin */
  margin-top: -5px;
  display: grid;
  align-items: center;
}

.order-header {
  margin-left: 5px;
  margin-right: 0px; /* Adjust this value to control the left margin */
  display: grid;
 align-items: center;
}

.order-panel {
  width: 97%;
  /* Make the panel 100% width within the order-form */
  margin-bottom: 10px;
}

.order-book-tables {
  width: 500px;
  height: auto;
  display: flex;
  justify-content: space-between;
  margin-left: -40px;
}

.order-panel label {
  font-weight: bold;
  color: #333;
  /* Dark text color */
}

.order-panel select,
.order-panel input {
  padding: 10px;
  border: 1.8px solid #e46c25;
  border-radius: 10px;
  width: 100%;
  /* Full width input fields */
  margin-bottom: 20px;
}

.button-header-stock-symbol {
  color: white;
  font-size: 18px;
  display: flex;
  justify-content: space-between;
  align-items: top;
   margin-top: -1px; 
   /* padding-left: -10px;
   padding-bottom: 5px;
   padding-right: 10px;
   padding-bottom: 5px; */
}
.button-header-action {
  width: 130px;
  color: white;
  font-size: 15px;
  display: flex;
  justify-content: space-between;
  align-items: top;
   margin-top: 1px; 
    padding-left: 5px;
  /* padding-bottom: 5px;
   padding-right: 10px;
   padding-bottom: 5px; */
}
.button-header-price {
  color: white;
  font-size: 15px;
  display: flex;
  justify-content: space-between;
  align-items: top;
   margin-top: 1px; 
   padding-left: 0px;
   padding-bottom: 5px;
   padding-bottom: 5px;
}
.button-header-type {
  width: 150px;
  color: white;
  font-size: 15px;
  display: flex;
  justify-content: space-between;
  align-items: top;
   margin-top: 1px; 
   /* padding-left: 5px;
   padding-bottom: 5px;
   padding-right: 10px;
   padding-bottom: 5px; */
}
.button-header-qty {
  color: white;
  font-size: 15px;
  display: flex;
  justify-content: space-between;
  align-items: top;
   margin-top: 1px; 
   padding-left: 5px;
   padding-bottom: 5px;
   padding-right: -20px;
   padding-bottom: 5px;
}

.button-wrapper .el-button {
  font-size: 15px;
  padding: 28px 60px;
  width: 133px;
  height: 65px;
  border: 1.5px solid #e7dfdf;
  border-radius: 20px;
  text-align: left;
  background-color: grey;
  color: #1b439b;
  transition: border-color 0.2s;
  margin-top: -5px;
}

  .selected {
    border-color: #1b439b;
    border-width: 1.5px;
    color: #1b439b;
}

/* .custom-input-number {
  display: flex;
  align-items: center;
  justify-content: center;
  /* Align items to the right */
  /* border: 1.5px solid #1b439b;
  border-radius: 25px; */

  /* margin-bottom: 10px;
  margin-top: 5px; */

  /* background-color: #f7f7f7;
  color: #333;
  font-size: 18px; */
  /* Increase the font size for a larger input box */
  /* width: 140px; */
  /* Width of the container */
  /* height: 35px; */
  /* Height of the container */
  /* } */ 


.custom-input-number .el-input {
  border: none;
  /* Remove the border from the inner input */
  margin: 0;
  /* Remove any margin */
  padding: 0;
  /* Remove any padding */
}

.custom-input-number .el-input-number__increase {
  background-color: #4771b8;
  color: white;
  border: 1.5px solid #3850b9;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  margin-right: -35px;
  margin-top: -2px;
  font-size: 20px;
  font-weight: 300;
}

.custom-input-number .el-input-number__decrease {
  background-color: #4771b8;
  color: white;
  border: 1.5px solid #3850b9;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  margin-left: -35px;
  margin-top: -2px;
  font-size: 20px;
  font-weight: bold;
}

.custom-input-order-value {
  width: 200px;
  margin-bottom: 15px;
  margin-top: 15px;
}

.custom-input-order-value .el-input {
  border: none;
  box-shadow: none;
}

.custom-input-order-value .el-input-number__input {
  border: 1.5px solid #1543b7;
  border-radius: 25px;
  background-color: #f7f7f7;
  color: #333;
  font-size: 30px;
  max-width: 600px;
  height: 70px;
  text-align: left;
  /* Align the text input to the left */

}

.place-order-button {
  display: flex;
  justify-content: center;
}

.place-order-button .el-button {
  background-color: #1b439b;
  /* Text color */
  border: none;
  /* Remove the button border */
  border-radius: 25px;
  font-size: 15px;
  width: 180px;
  height: 35px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: -5px;
  margin-top: 10px;
  margin-left: 100px;
}

.place-order-button .el-button:active {
  background-color: #1b439b; /* Change the background color when the button is pressed */
  color: white; /* Change the text color when the button is pressed */
}

.button-wrapper .el-button:active{
  /* Define the style for the "Action" row when clicked */
  background-color: #1b439b;
  color: white;
}

.button-wrapper .el-button {
  font-size: 15px;
  padding: 20px 40px;
  width: auto;
  height: auto;
  border: 1.5px solid #e7dfdf;
  border-radius: 20px;
  text-align: left;
  background-color: #f6f9f7;
  color: #1b439b;
  transition: border-color 0.2s;
}

/* Selected effect for Action buttons */
.button-wrapper .el-button.action.selected {
  border-color: #1b439b;
  border-width: 1.5px;
  color: #1b439b;
}
.button-wrapper .el-button.order-type.selected {
  border-color: #1b439b;
  border-width: 1.5px;
  color: #1b439b;
}

/* Styles for the button on hover */
.button-wrapper .el-button:hover {
  /* Your styles for the hover state (when the mouse is over the button) */
  /* Example styles: */
  background-color: #1b439b;
  color: white;
  border-color: #1b439b;
}

.custom-stock-input {
  /* Control the box size */
  width: 200px; /* Adjust the width as needed */
  height: 40px; /* Adjust the height as needed */
 display: flex;
  align-items: center;
  /* Control the background color */
  background-color: #f7f7f7; /* Background color */
  border: 2px solid #e46c25; /* Border color */
  border-radius: 10px; /* Border radius */
  padding: 10px; /* Padding inside the input */
  font-size: 16px; /* Font size */
  color: #333; /* Text color */
}
</style>
