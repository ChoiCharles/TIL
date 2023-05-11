import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

// 문제에서 요구하는 형태로 작성해 줬습니다.
export default new Vuex.Store({
  state: {
    orderList: [],
    menuList: [
      { 
        title: '아메리카노',
        price: 3000,
        selected: false, //초기값
        image: 'https://source.unsplash.com/featured/?americano'
      },
      { 
        title: '라떼',
        price: 4000,
        selected: false,
        image: 'https://source.unsplash.com/featured/?latte',
      },
      { 
        title: '카푸치노',
        price: 4500,
        selected: false,
        image: 'https://source.unsplash.com/featured/?capucchino',
      },
    ],
    sizeList: [
      {
        name: 'small',
        price: 500,
        selected: true, // 초기값 설정
      },
      {
        name: 'medium',
        price: 1000,
        selected: false,
      },
      {
        name: 'large',
        price: 1500,
        selected: false,
      },
    ],
    
  },
  getters: {
  },
  mutations: {
    SELECT_COFFEE: function (state, selectedMenu) {
      state.menuList = state.menuList.map((menu) => {
        // 사용자가 선택한 메뉴를 찾은 뒤,
        if (menu.title === selectedMenu.title) {
          menu.selected = true // 해당 객체의 selected 값을 바꿈
        } 
        else {
          menu.selected = false
        }
        return menu
      })
      
    },

    UPDATE_SIZE: function (state, selectedSize) {
      state.sizeList = state.sizeList.map((size) => {
        if (size.name === selectedSize.name) {
          size.selected = true
        } 
        else {
          size.selected = false
        }
        return size
      })
    },
    
    addOrder: function (state) {
      // selected값이 true인 것들만 찾아서 
      // menu 그리고 size 변수에 저장한다.
      const menu = state.menuList.find((menu) => menu.selected)
      const size = state.sizeList.find((size) => size.selected)

      // 새로운 객체를 만들어서 state의 orderList에
      // push 메서드를 이용해서 값 추가 한다.
      const order = { menu, size }
      state.orderList.push(order)
    },
  },
  actions: {
    selectCoffee(context, selectedMenu) {
      context.commit('SELECT_COFFEE', selectedMenu)
    },
    updateSize(context, updateSize) {
      context.commit('UPDATE_SIZE', updateSize)
    }
  },
  modules: {
  },
})