<template>
  <div class="page-container">
    <LogoutButton class="logout-button"/>
    <div class="content-container">
      <div class="left-column">
        <PointsGraph ref="pointsGraph" @svgClicked="handleSvgClick"/>
        <PointsForm ref="pointsForm" @pointAdded="handlePointAdded"/>
      </div>
      <div class="right-column">
        <PointsTable ref="pointsTable"/>
      </div>
    </div>
  </div>
</template>

<script>
import PointsTable from '../components/PointsTable.vue'
import PointsGraph from "@/components/PointsGraph.vue";
import PointsForm from "@/components/PointsForm.vue";
import LogoutButton from "@/components/LogoutButton.vue";
export default {
  name: 'PointsPage',
  components: {
    LogoutButton,
    PointsForm,
    PointsGraph,
    PointsTable
  },
  mounted() {
    this.$refs.pointsTable.loadPoints();
    this.$refs.pointsGraph.loadPoints();
  },
  methods: {
    handlePointAdded(data) {
      this.$refs.pointsTable.addPoint(data);
      this.$refs.pointsGraph.addPoint(data);
    },
    handleSvgClick({ event, rect }) {
      this.$refs.pointsForm.calcXYFromCoords(event, rect);
    }
  }
}
</script>

<style scoped>
.page-container {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

.logout-button {
  margin: 10px;
}

.content-container {
  display: flex;
  flex-direction: column;
  width: 100%;
}

.left-column, .right-column {
  width: 100%;
}

@media (max-width: 783px) {

}

@media (min-width: 784px) {
  .content-container {
    flex-direction: row;
    justify-content: space-between;
  }
  .left-column {
    width: 40%;
    display: flex;
    flex-direction: column;
  }
  .right-column {
    width: 60%;
  }
}
@media (min-width: 1103px) {

}
</style>
