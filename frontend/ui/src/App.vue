<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <router-link to="/coin-calculator" class="navbar-brand">Coin Calculator</router-link>
    </nav>
    <div class="container mt-3">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: "app"
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
