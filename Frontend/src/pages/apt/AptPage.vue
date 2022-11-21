<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div class="content">
      <div class="row col-md-12 justify-content-center mb-4">
        <apt-search-form></apt-search-form>
        <apt-map></apt-map>
      </div>
    </div>
  </div>
</template>
<script>
import AptSearchForm from "@/pages/apt/AptSearchForm";
import AptMap from "@/pages/apt/AptMap";
export default {
  name: "AptPage",
  data() {
    return {
      modals: {
        mini: false,
      },
    };
  },
  components: {
    AptSearchForm,
    AptMap,
  },
};
</script>
<style></style>
