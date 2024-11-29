import { ref, onMounted } from 'vue';
import axios from 'axios';
import klineChart from './model/klineChart.vue';

export default {
  name: 'App',
  components: {
    klineChart,
  },
  setup() {
    const priceInput = ref(256);
    const quantityInput = ref(100);
    const stockSymbol = ref('TSLA');
    const stockSymbolOptions = ['TSLA', 'AAPL', 'MSFT'];

    const buySellSelectedOption = ref(0);
    const buySellOptions = ['BUY', 'SELL'];

    const orderTypeSelectedOption = ref(0);
    const orderTypeOptions = ['MARKET', 'LIMIT'];

    const buyOrders = ref([]);
    const sellOrders = ref([]);

    const retrieveQueue = async () => {
      const symbol = stockSymbol.value;
      try {
        const responseBuy = await axios.get(`http://localhost:8085/transactions/bidQueue?stockId=${symbol}`);
        const responseAsk = await axios.get(`http://localhost:8085/transactions/askQueue?stockId=${symbol}`);
        sellOrders.value = responseAsk.data;
        buyOrders.value = responseBuy.data;
      } catch (err) {
        console.error(err);
      }
    };

    const placeOrder = () => {
      if (isNaN(priceInput.value) || isNaN(quantityInput.value) || priceInput.value <= 0 || quantityInput.value <= 0) {
        console.error('Invalid price or quantity');
        return;
      }
      const symbol = stockSymbol.value;
      const action = buySellOptions[buySellSelectedOption.value];
      const orderType = orderTypeOptions[orderTypeSelectedOption.value];
      const data = new URLSearchParams({ action, orderType, price: priceInput.value, quantity: quantityInput.value });

      axios.post(`http://localhost:8085/sumit/trade/symbol/${symbol}`, data, {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      }).then(() => {
        console.log('Order placed successfully');
      }).catch(error => {
        console.error('Error placing order:', error);
      });
    };

    onMounted(() => {
      retrieveQueue();
      setInterval(() => retrieveQueue(), 2000);
    });

    return {
      stockSymbol,
      priceInput,
      quantityInput,
      buyOrders,
      sellOrders,
      stockSymbolOptions,
      buySellSelectedOption,
      buySellOptions,
      orderTypeSelectedOption,
      orderTypeOptions,
      placeOrder,
    };
  },
};