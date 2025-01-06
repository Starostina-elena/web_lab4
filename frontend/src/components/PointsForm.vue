<template>
  <form class="login-form" @submit.prevent="handleSubmit">
    <div class="form-block">
      <label>X</label>
      <div>
        <label v-for="value in xAndRValues" :key="value">
          <input type="radio" name="x" :value="value" v-model="selectedX"> {{ value }}
        </label>
      </div>
    </div>
    <div class="form-block">
        <label for="y">Y</label>
        <input id="y" name="y" required ref="y" type="number" min="-3" max="5" step="0.01" >
    </div>
    <div class="form-block">
      <label for="r">R</label>
      <div>
        <label v-for="value in xAndRValues" :key="value">
          <input type="radio" name="r" :value="value" v-model="selectedR"> {{ value }}
        </label>
      </div>
    </div>
    <div class="form-block">
      <button type="submit">{{ btnTitle }}</button>
    </div>
  </form>
</template>

<script>
import {mapActions, mapGetters} from 'vuex'
export default {
  name: 'PointsForm',
  props: {
    serverUrl: {
      type: String,
      default: 'http://localhost:34350/lab4/api/points/add'
    },
    btnTitle: {
      type: String,
      default: 'Проверить'
    }
  },
  computed: {
    ...mapGetters(['getUsername', 'getPassword', 'getCreatorId'])
  },
  data() {
    return {
      xAndRValues: [-5, -4, -3, -2, -1, 0, 1, 2, 3],
      selectedX: null,
      selectedR: null,
    };
  },
  methods: {
    ...mapActions(['saveUser']),
    handleSubmit(event) {
      event.preventDefault();
      if (this.selectedR === null) {
        alert('Выберите R');
        return;
      }
      if (this.selectedX === null) {
        alert('Выберите X');
        return;
      }
      this.formWork(this.selectedX, this.$refs.y.value)
    },
    calcXYFromCoords(event, rect) {
      if (this.selectedR === null) {
        alert('Выберите R');
        return;
      }
      const x = ((event.clientX - rect.left - 150) * (this.selectedR / 2) / (50)).toFixed(2);
      const y = (((-1) * (event.clientY - rect.top - 150)) * (this.selectedR / 2) / (50)).toFixed(2);
      this.formWork(x, y)
    },
    formWork(x, y) {
      const requestBody = {
        creatorId: this.getCreatorId,
        username: this.getUsername,
        password: this.getPassword,
        x: x,
        y: y,
        r: this.selectedR
      };
      fetch(this.serverUrl, {
        method: "POST",
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
      })
          .then(response => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            return response.json();
          })
          .then(data => {
            this.$emit('pointAdded', data);
            //this.$router.push('/points');
          })
          .catch(error => {
            console.error('Error:', error);
            this.$router.push('/');
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
