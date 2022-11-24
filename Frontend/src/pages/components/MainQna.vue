<template>
  <div id="carousel">
    <div class="container">
      <div class="title">
        <h4>Q&A</h4>
      </div>
      <div class="row justify-content-center">
        <div class="col-12">
          <el-carousel>
            <el-carousel-item v-for="(article, i) in bestArticles" :key="i">
              <img class="d-block" src="img/navimg.png" alt="First slide" />
              <div class="carousel-caption d-none d-md-block">
                <h5>{{ article.content }}</h5>
              </div>
            </el-carousel-item>
          </el-carousel>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
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
