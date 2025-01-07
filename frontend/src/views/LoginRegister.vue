<template>
  <div class="about-me-container">
    <h1 id="about-me">Старостина Елена Петровна, P3219, ИСУ 414257. Вариант 19999</h1>
  </div>
  <div class="form-wrapper">
    <div id="login-form-container">
      <LoginForm serverUrl="http://localhost:34350/lab4/api/auth/login"
                 defaultErrMessage="Неверный логин или пароль"
                 btnTitle="Войти" class="login-register-form"/>
    </div>
    <div id="register-form-container">
      <LoginForm serverUrl="http://localhost:34350/lab4/api/auth/register"
                 defaultErrMessage="Пользователь с таким именем уже есть"
                 btnTitle="Зарегистрироваться" class="login-register-form"/>
    </div>
  </div>
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
h1 {
  background-color: #B6DEAE;
  padding-top: 20px;
  padding-bottom: 20px;
  border-radius: 10px;
  border: none;
  outline: none;
}
@media (max-width: 783px) {
  h1 {
    font-size: 20px;
  }
}

@media (min-width: 784px) {
  #login-form-container, #register-form-container {
    width: 48%;
  }
  .form-wrapper {
    display: flex;
    flex-direction: row;
    justify-content: center;
    gap: 20px;
  }
}
@media (min-width: 1103px) {
  #login-form-container {
    margin-left: 15%;
    margin-right: -15%;
  }
}
</style>
