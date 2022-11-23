import { apiInstance } from "@/api";

const api = apiInstance();

async function getNewsList(success) {
  api
    .get(`/news/list`)
    .then(success)
    .catch((err) => {
      console.log(err);
    });
}
export { getNewsList };
