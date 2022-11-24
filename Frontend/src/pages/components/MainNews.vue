<template>
  <div id="carousel">
    <div class="container">
      <div class="title">
        <h4>부동산 뉴스</h4>
      </div>
      <div class="row justify-content-center">
        <div class="col-12">
          <news-list-main></news-list-main>
          <div></div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { Carousel, CarouselItem } from "element-ui";
import NewsListMain from "@/pages/news/NewsListMain";
export default {
  components: {
    [Carousel.name]: Carousel,
    [CarouselItem.name]: CarouselItem,
    NewsListMain,
  },
};
</script>
<style></style>
