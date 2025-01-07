<template>
  <table id="history">
    <tr id="table-first-row">
      <th>X</th>
      <th>Y</th>
      <th>R</th>
      <th>Время</th>
      <th>Результат</th>
    </tr>
  </table>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
export default {
  name: 'PointsTable',
  computed: {
    ...mapGetters(['getUsername', 'getPassword', 'getCreatorId'])
  },
  methods: {
    ...mapActions(['saveUser']),
    loadPoints() {
      const requestBody = {
        creatorId: this.getCreatorId,
        username: this.getUsername,
        password: this.getPassword
      };
      fetch('http://localhost:34350/lab4/api/points/', {
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
            data.forEach(point => {
              const row = document.createElement('tr');
              row.innerHTML = `
                <td>${point.x}</td>
                <td>${point.y}</td>
                <td>${point.r}</td>
                <td>${new Date(point.date).toLocaleString() }</td>
                <td>${point.result ? 'Да' : 'Нет'}</td>
              `;
              document.getElementById('history').appendChild(row);
            });
          })
          .catch(error => {
            console.error('Error:', error);
            this.$router.push('/');
          });
    },
    addPoint(data) {
      const row = document.createElement('tr');
      row.innerHTML = `
        <td>${data.x}</td>
        <td>${data.y}</td>
        <td>${data.r}</td>
        <td>${new Date(data.date).toLocaleString() }</td>
        <td>${data.result ? 'Да' : 'Нет'}</td>
      `;
      const table = document.getElementById('history');
      table.insertBefore(row, table.rows[1]);
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#history {
  width: 100%;
  border-collapse: collapse;
}

#history th, #history td {
  border: 1px solid #ddd;
  padding: 8px;
}

#history th {
  font-weight: normal;
  background-color: #f2f2f2;
  border-radius: 10px;
}
</style>
