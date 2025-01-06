<template>
  <img alt="Vue logo" src="../assets/logo.png">
  <LoginForm serverUrl="http://localhost:34350/lab4/api/auth/login"
             defaultErrMessage="Неверный логин или пароль"
             btnTitle="Войти"/>
  <LoginForm serverUrl="http://localhost:34350/lab4/api/auth/register"
             defaultErrMessage="Пользователь с таким именем уже есть"
             btnTitle="Зарегистрироваться"/>
</template>

<script>
import LoginForm from '../components/LoginForm.vue'

export default {
  name: 'LoginRegister',
  components: {
    LoginForm
  }
}
</script>

<style>
</style>
