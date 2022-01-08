<template>
  <div id="app" class="container">
    <div class="row main-container mx-auto">
      <div class="row">
        <div class="col">
          <label>Amount to spend</label>
        </div>
        <div class="col">
          <label>Coins to receive</label>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <input
              class="currency-input"
              type="number"
              name="input-one"
              id="input-one"
              v-model="amountOne"
              @input="calculateResults()"
          />
          <select
              class="select-input"
              name="first-currency"
              id="first-currency"
              @change="calculateResults()"
              v-model="currencyOne"
          >
            <option value="EUR">EUR</option>
            <option value="USD">USD</option>
          </select>
        </div>
        <div class="col">
          <input
              class="currency-input"
              type="number"
              id="amount-two"
              placeholder="0"
              v-model="amountTwo"
              disabled
          />
          <select
              class="select-input"
              id="currency-two"
              @change="calculateResults()"
              v-model="currencyTwo"
          >
            <option value="BTC">BTC</option>
          </select>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <small id="price-availability">Price will be updated in: {{ this.countDown }} seconds...</small>
        </div>
        <div class="col">
          <small id="last-update-date">Lastly Updated: {{ this.lastUpdateDate }}</small>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CoinCalculatorService from "@/services/CoinCalculatorService";

export default {
  components: {},
  data() {
    return {
      data: [],
      currencyOne: "USD",
      currencyTwo: "BTC",
      amountOne: 1,
      amountTwo: 0,
      countDown : 10,
      lastUpdateDate: "-"
    };
  },
  methods: {
    countDownTimer() {
      if(this.countDown > 0) {
        console.log('COUNTER: ' + this.countDown)
        setTimeout(() => {
          this.countDown -= 1
          if(this.countDown === 0) {
            this.calculateResults();
          }
          this.countDownTimer()
        }, 1000)
      }
    },
    calculateResults() {
      if (this.amountOne < 25) {
        this.amountOne = 25;
      }
      if (this.amountOne > 5000) {
        this.amountOne = 5000;
      }
      const request = {
        fiatCurrency: this.currencyOne,
        fiatAmount: this.amountOne,
        coinType: this.currencyTwo
      };
      CoinCalculatorService.convert(request)
          .then((data) => {
            console.log(data.data.date);
            this.data = data;
            if (this.data && this.data.data && this.data.data.lastUpdatedAt) {
              this.lastUpdateDate = this.data.data.lastUpdatedAt;
            }
            console.log('LAST UPDATED AT: ' + this.lastUpdateDate);
            this.amountTwo = this.data.data.coinAmount;
          })
          .catch(e => {
            console.log(e);
          });
      this.countDown = 10;
    },
  },
  mounted() {
    this.calculateResults();
    this.countDownTimer()
  },
};
</script>

<style>
html {
  background: #f4f4f4;
}

#app {
  display: flex;
  flex-direction: column;
  align-content: center;
  align-items: center;
  /* justify-content: center; */
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  width: 100%;
  height: 100%;
  /* background: #f4f4f4f4; */
  /* border:1px solid red */
}


.select-input {
  padding: 5px;
  margin: 5px;
  border: 1px solid #CCC;
}

.currency-input{
  width: 70%;
  margin-right: 5px;
  font-size: 20px;
  height: 40px;
  border-radius: 5px;
  border: 1px solid #CCC;
  padding-left: 10px;
}

.main-container{
  width: 800px;
  position: absolute;
  border-radius: 10px;
  padding: 50px;
  top: 15%;
  left: 50%;
  transform: translate(-50%,-40%);
  background: white;
  box-shadow: 0 12px 10px -6px $blue-dark;
}

.row{
  width: 100%;
}
</style>
