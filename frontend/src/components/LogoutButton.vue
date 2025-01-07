<template>
  <form class="logout-form" @submit.prevent="handleSubmit">
    <div class="form-block">
      <p class="username">{{ username }}</p>
    </div>
    <br>
    <div class="form-block">
      <button class="button-submit" type="submit">Выйти</button>
    </div>
  </form>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
export default {
  name: 'LogoutButton',
  computed: {
    ...mapGetters(['getUsername'])
  },
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
.form-block {
  margin: 20px 0;
  text-align: right;
  flex-direction: column;
}
.button-submit {
  padding: 7px;
  border-radius: 10px;
  border: none;
  outline: none;
  background-color: #3DA028;
  color: white;
  width: 60px;
}
</style>
