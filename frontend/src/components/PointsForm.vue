<template>
  <form class="login-form" @submit.prevent="handleSubmit">
    <div class="form-block">
      <label>X</label>
      <div>
        <label v-for="value in xAndRValues" :key="value" class="radio-label">
          <input type="radio" name="x" :value="value" v-model="selectedX"> {{ value }}
        </label>
      </div>
    </div>
    <div class="form-block">
        <label for="y">Y</label>
        <input class="input-field" id="y" name="y" required ref="y" type="number" min="-3" max="5" step="0.01" >
    </div>
    <div class="form-block">
      <label for="r">R</label>
      <div>
        <label v-for="value in xAndRValues" :key="value" class="radio-label">
          <input type="radio" name="r" :value="value" v-model="selectedR"> {{ value }}
        </label>
      </div>
    </div>
    <div class="form-block">
      <button class="button-submit" type="submit">{{ btnTitle }}</button>
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
.form-block {
  margin: 20px 0;
  text-align: left;
  display: flex;
  flex-direction: column;
}
.button-submit {
  padding: 7px;
  border-radius: 10px;
  border: none;
  outline: none;
  background-color: #3DA028;
  color: white;
  width: 90px;
}
.input-field {
  border-radius: 10px;
  padding: 7px;
}

@media (max-width: 783px) {
  .radio-label {
    display: inline-flex;
    align-items: center;
  }

  .radio-label input[type="radio"] {
    margin-top: 0px;
    margin-right: 2px;
    vertical-align: middle;
  }
}
</style>
