<template>
  <div class="page-header clear-filter" filter-color="orange">
    <b-container class="bv-example-row mt-3">
      <notice-input-item type="modify" />
    </b-container>
  </div>
</template>

<script>
import NoticeInputItem from "@/pages/notice/item/NoticeInputItem";

export default {
  name: "NoticeModify",
  components: {
    NoticeInputItem,
  },
};
</script>

<style></style>
