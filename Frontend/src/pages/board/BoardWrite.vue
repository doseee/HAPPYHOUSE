<template>
  <div class="page-header clear-filter" filter-color="orange">
    <b-container>
      <board-input-item type="register" />
    </b-container>
  </div>
</template>

<script>
import BoardInputItem from "@/pages/board/item/BoardInputItem";

export default {
  name: "BoardWrite",
  components: {
    BoardInputItem,
  },
};
</script>

<style></style>
