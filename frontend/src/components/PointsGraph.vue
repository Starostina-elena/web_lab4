<template>
  <div class="image-container">
    <svg width="300px" height="300px" @click="svgHandler" class="svgClass" id="graphSvg">
      <line x1="0" x2="300" y1="150" y2="150" stroke="#343548"></line>
      <line x1="150" x2="150" y1="0" y2="300" stroke="#343548"></line>
      <polygon points="150,0 145,10 155,10" stroke="#343548"></polygon>
      <polygon points="300,150 290,145 290,155" stroke="#343548"></polygon>
      <path d="M250,150 A100,100 90 0,1 150,250 L 150,150 Z" fill="#87CEEB"></path>
      <polygon points="150,150 150,100 50,150" fill="#87CEEB"></polygon>
      <polygon points="250,150 250,50 150,50 150,150" fill="#87CEEB"></polygon>

      <line x1="50" x2="50" y1="145" y2="155" stroke="#343548"></line>
      <line x1="100" x2="100" y1="145" y2="155" stroke="#343548"></line>
      <line x1="200" x2="200" y1="145" y2="155" stroke="#343548"></line>
      <line x1="250" x2="250" y1="145" y2="155" stroke="#343548"></line>

      <line x1="145" x2="155" y1="50" y2="50" stroke="#343548"></line>
      <line x1="145" x2="155" y1="100" y2="100" stroke="#343548"></line>
      <line x1="145" x2="155" y1="200" y2="200" stroke="#343548"></line>
      <line x1="145" x2="155" y1="250" y2="250" stroke="#343548"></line>

      <text x="290" y="140">X</text>
      <text x="155" y="10">Y</text>
      <text x="40" y="138">-R</text>
      <text x="85" y="138">-R/2</text>
      <text x="190" y="138">R/2</text>
      <text x="245" y="138">R</text>
      <text x="162" y="54">R</text>
      <text x="162" y="104">R/2</text>
      <text x="162" y="204">-R/2</text>
      <text x="162" y="254">-R</text>
    </svg>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
export default {
  name: 'PointsGraph',
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
            const svg = document.getElementById("graphSvg");
            data.forEach(point => {
              const circle = document.createElementNS('http://www.w3.org/2000/svg', 'circle');
              const x_coord = 150 + 50 * 2/ point.r * point.x;
              const y_coord = 150 - 50 * 2/ point.r * point.y
              circle.setAttribute("cx", x_coord.toString());
              circle.setAttribute("cy", y_coord.toString());
              circle.setAttribute("r", "2");
              circle.setAttribute("fill", point.result ? "green" : "orangered");
              svg.appendChild(circle);
            });
          })
          .catch(error => {
            console.error('Error:', error);
            this.$router.push('/');
          });
    },
    svgHandler(event) {
      const svg = document.getElementById("graphSvg");
      const rect = svg.getBoundingClientRect();
      this.$emit('svgClicked', { event, rect });
    },
    addPoint(data) {
      const svg = document.getElementById("graphSvg");
      const circle = document.createElementNS('http://www.w3.org/2000/svg', 'circle');
      const x_coord = 150 + 50 * 2/ data.r * data.x;
      const y_coord = 150 - 50 * 2/ data.r * data.y
      circle.setAttribute("cx", x_coord.toString());
      circle.setAttribute("cy", y_coord.toString());
      circle.setAttribute("r", "2");
      circle.setAttribute("fill", data.result ? "green" : "orangered");
      svg.appendChild(circle);
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
