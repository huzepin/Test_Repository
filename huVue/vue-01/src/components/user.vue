<template>
  <div>
    <h3>{{msg}}</h3>
  </div>
</template>

<script>
export default {
  name: 'user',
  data () {
    return {
      msg: '这是user'
    }
  }
}
</script>

<style scoped>
h3{
  background-color:red;
}
</style>
