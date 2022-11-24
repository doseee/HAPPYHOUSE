<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div class="content">
      <div class="container">
        <div class="col-md-10 ml-auto mr-auto">
          <card type="list" plain>
            <p>NEWS</p>
            <b-container>
              <b-row>
                <b-col>
                  <h3>부동산 뉴스</h3>
                </b-col>
              </b-row>
              <b-row>
                <b-col>
                  <b-table-simple hover responsive class="mb-6">
                    <b-thead>
                      <b-tr>
                        <b-th>목록</b-th>
                      </b-tr>
                    </b-thead>
                    <tbody>
                      <news-list-item
                        v-for="(item, idx) in items"
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
