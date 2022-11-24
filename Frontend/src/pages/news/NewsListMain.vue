<template>
  <div style="background-color: #f6f6f6; opacity: 0.9; height: 300px">
    <div class="col-md-10 ml-auto mr-auto">
      <card type="list" plain>
        <b-container style="margin-top: 30px">
          <b-row class="mb-5">
            <b-col>
              <b-table-simple hover responsive class="mb-6">
                <tbody>
                  <news-list-item
                    v-for="(item, idx) in items.slice(0, 4)"
                    :key="idx"
                    v-bind="item" />
                </tbody>
              </b-table-simple>
            </b-col>
          </b-row>
        </b-container>
      </card>
    </div>
  </div>
</template>

<script>
import { getNewsList } from "@/api/news.js";
import NewsListItem from "@/pages/news/NewsListItem";
export default {
  name: "NewsList",
  components: {
    NewsListItem,
  },
  data() {
    return {
      items: [],
    };
  },
  created() {
    getNewsList((res) => {
      this.items = res.data.items;
    });
  },
};
</script>

<style></style>
