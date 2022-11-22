<template>
  <div class="page-header clear-filter" filter-color="orange">
    <b-container class="bv-example-row mt-3">
      <board-input-item type="modify" />
    </b-container>
  </div>
</template>

<script>
import BoardInputItem from "@/pages/board/item/BoardInputItem";

export default {
  name: "BoardModify",
  components: {
    BoardInputItem,
  },
};
</script>

<style></style>
