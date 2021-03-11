<template>
  <div class="home">
    <h3>{{msg}}</h3>
  </div>

</template>

<script>

export default {
  name: 'home',
  data () {
    return {
      msg: '这是home'
    }
  }
}
</script>

<style scoped>
h3{
  background-color: aqua;
}

</style>
