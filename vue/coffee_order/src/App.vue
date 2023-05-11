<template>
  <div id="app">
    <h1>Coffee Order App</h1>
    <br>
    <div class="row p-3 bg-light rounded-4" style="margin-left: 20%; margin-right: 20%;">
      <div class="col-sm-6">
        <div class="card border border-0">
          <div class="card-body">
            <h2 class="card-title mt-1">1. 음료를 고르세요</h2>
            <div class="row">
              <div class="col-sm-12" style="margin-top:3%" v-for="(menu, index) in menuList" :key="index" @click="selectCoffee(menu)">
                <div class="card border border-2 rounded-4">
                  <div :class="{select: menu.selected}"  class="card-body p-0 rounded-4">
                    <h2 class="card-title">
                      <div class="row">
                        <img class="col-lg-4 col-12 rounded-4 ms-2 mt-2 d-flex justify-content-center" :src="menu.image" alt="" style="width:25%;">
                        <div class="col-lg-8 col-12 d-flex flex-column mb-0 p-0 align-self-center">
                          <h3 :class="{f_color: menu.selected}" class="p-2 m-0">{{ menu.title }}</h3>
                          <h3 :class="{f_color: menu.selected}" class="p-2 m-0">{{ menu.price }}원</h3>
                        </div>
                      </div>
                    </h2>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-sm-6">
        <div class="card border border-0">
          <div class="card-body mb-auto">
            <h2 class="card-title mt-1">2. 사이즈를 고르세요</h2>
            <div class="row" >
              <div :class="{sizeList: !isActive}"  class="col-sm-12" style="margin-top:3%" v-for="(size, index) in sizeList" :key="index" @click="updateSize(size)">
                <div class="card border border-2 rounded-4">
                  <div :class="{select: size.selected}" class="card-body rounded-4">
                    <h2 class="card-title">
                      <span class="d-flex justify-content-between">
                        <h3 :class="{f_color: size.selected}" class="m-0">{{size.name}}</h3>
                        <h3 :class="{f_color: size.selected}" class="m-0">{{ size.price }}원</h3>
                      </span>
                    </h2>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>

export default {
  name: 'App',
  data() {
    return {
      isActive: false
    }
  },
  computed: {
    orderList() {
      return this.$store.state.orderList
    },
    menuList() {
      return this.$store.state.menuList
    },
    sizeList() {
      return this.$store.state.sizeList
    }
  },
  methods: {
    selectCoffee(menu) {
      const selectedMenu = menu
      this.isActive = true
      this.$store.dispatch('selectCoffee', selectedMenu)
    },
    updateSize(size) {
      const updateSize = size
      this.$store.dispatch('updateSize', updateSize)
    }
  }
}
</script>

<style>
#app {
  font-family: 'Dongle', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.select {
  background-color: rgba(0, 158, 0, 0.596);
}
.f_color {
  color:white;
}
.sizeList {
  display: none;
}
@font-face {
  font-family:'Dongle';
  src: url('assets/fonts/Dongle-Regular.ttf') format('truetype');
  font-weight: 400;
}
@font-face {
  font-family:'Dongle';
  src: url('assets/fonts/Dongle-Light.ttf') format('truetype');
  font-weight: 300;
}
@font-face {
  font-family:'Dongle';
  src: url('assets/fonts/Dongle-Bold.ttf') format('truetype');
  font-weight: 700;
}
</style>
