<template>
  <b-tr>
    <b-th v-html="title" @click="moveLink"> </b-th>
  </b-tr>
</template>

<script>
export default {
  name: "NewsListItem",
  props: {
    title: String,
    link: String,
    description: String,
  },
  methods: {
    moveLink() {
      window.open(this.link);
    },
  },
};
</script>

<style>
b {
  font-weight: normal;
}
</style>
