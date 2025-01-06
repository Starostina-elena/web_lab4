<template>
  <form class="login-form" @submit.prevent="handleSubmit">
    <div class="form-block">
        <label for="username">Имя пользователя</label>
        <input id="username" name="username" required ref="username">
    </div>
    <div class="form-block">
        <label for="password">Пароль</label>
        <input type="password" id="password" name="password" required ref="password">
    </div>
    <div class="form-block">
      <button type="submit">{{ btnTitle }}</button>
    </div>
    <div class="form-block">
      <p id="login-message">{{ errorMessage }}</p>
    </div>
  </form>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: 'LoginForm',
  props: {
    serverUrl: {
      type: String,
      default: 'http://localhost:34350/lab4/api/auth/login'
    },
    defaultErrMessage: {
      type: String,
      default: 'Неверный логин или пароль'
    },
    btnTitle: {
      type: String,
      default: 'Войти'
    }
  },
  data() {
    return {
      errorMessage: ''
    };
  },
  methods: {
    ...mapActions(['saveUser']),
    handleSubmit(event) {
      event.preventDefault();
      const username = this.$refs.username.value;
      const password = this.$refs.password.value;
      fetch(this.serverUrl, {
        method: "POST",
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
      })
          .then(response => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            return response.json();
          })
          .then(data => {
            this.errorMessage = '';
            this.saveUser({ username, password, creatorId: data.id });
            this.$router.push('/points');
          })
          .catch(error => {
            console.error('Error:', error);
            this.errorMessage = this.defaultErrMessage;
          });
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
