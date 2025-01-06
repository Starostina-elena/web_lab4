<template>
  <form class="logout-form" @submit.prevent="handleSubmit">
    <div class="form-block">
      <button type="submit">Выйти</button>
    </div>
  </form>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: 'LogoutButton',
  props: {
    serverUrl: {
      type: String,
      default: 'http://localhost:34350/lab4/api/auth/login'
    }
  },
  methods: {
    ...mapActions(['clearUser']),
    handleSubmit(event) {
      event.preventDefault();
      this.clearUser();
      this.$router.push('/');
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
a {
  color: #42b983;
}
</style>
