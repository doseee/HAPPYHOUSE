<template>
  <div class="page-header clear-filter" filter-color="orange">
    <b-container>
      <b-row>
        <b-col>
          <h3>글작성</h3>
        </b-col>
      </b-row>
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
