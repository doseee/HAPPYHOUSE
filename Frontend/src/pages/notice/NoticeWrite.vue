<template>
  <div class="page-header clear-filter" filter-color="orange">
    <b-container>
      <notice-input-item type="register" />
    </b-container>
  </div>
</template>

<script>
import NoticeInputItem from "@/pages/notice/item/NoticeInputItem";

export default {
  name: "NoticeWrite",
  components: {
    NoticeInputItem,
  },
};
</script>

<style></style>
