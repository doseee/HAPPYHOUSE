<template>
  <div id="carousel">
    <div class="container">
      <div class="title">
        <h4>TOP3 인기 게시글</h4>
      </div>
      <div class="row justify-content-center">
        <div class="col-12">
          <board-list-main></board-list-main>
          <div></div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import BoardListMain from "@/pages/board/BoardListMain";
import { Carousel, CarouselItem } from "element-ui";
import { getBestArticle } from "@/api/board.js";
export default {
  data() {
    return {
      bestArticles: [],
    };
  },
  components: {
    [Carousel.name]: Carousel,
    [CarouselItem.name]: CarouselItem,
    BoardListMain,
  },
  beforeCreate() {
    getBestArticle((res) => {
      if (res.data.result == "success") {
        this.bestArticles = res.data.data;
      } else {
        alert("게시글 가져오기에 실패하였습니다.");
      }
    });
  },
};
</script>
<style></style>
