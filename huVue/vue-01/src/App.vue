<template>
  <div id="app">
    <nav class="top-menu">
      <ul>
        <li v-for="item in menuList" v-bind:key="item">
          <router-link :to="item.url">{{item.name}}</router-link>
        </li>
      </ul>
    </nav>
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: 'app',
  data: function () {
    return {
      menuList: [
        {name: '首页', url: '/home'},
        {name: '用户', url: '/user'},
        {name: '产品', url: '/product'}
      ]
    }
  }
}
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }

  .top-menu ul, .top-menu li{
    list-style: none;
  }

  .top-menu{
    overflow: hidden;
  }

  .top-menu li{
    float: left;
    width: 100px;
  }
</style>
