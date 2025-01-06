<template>
  <LogoutButton/>
  <PointsTable ref="pointsTable"/>
  <PointsGraph ref="pointsGraph" @svgClicked="handleSvgClick"/>
  <PointsForm ref="pointsForm" @pointAdded="handlePointAdded"/>
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

<style>
</style>
