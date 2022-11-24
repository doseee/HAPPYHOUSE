<template>
  <div>
    <div class="page-header clear-filter" filter-color="orange">
      <parallax
        class="page-header-image"
        style="background-image: url('img/city.jpg')">
      </parallax>
      <div class="container">
        <div class="container">
          <div class="row">
            <div class="col-md-6">
              <main-qna></main-qna>
            </div>
            <div class="col-md-6">
              <main-news></main-news>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { Parallax } from "@/components";
import MainQna from "./components/MainQna";
import MainNews from "./components/MainNews";
export default {
  name: "index",
  bodyClass: "index-page",
  components: {
    Parallax,
    MainQna,
    MainNews,
  },
};
(function () {
  var w = window;
  if (w.ChannelIO) {
    return (window.console.error || window.console.log || function () {})(
      "ChannelIO script included twice.",
    );
  }
  var ch = function () {
    ch.c(arguments);
  };
  ch.q = [];
  ch.c = function (args) {
    ch.q.push(args);
  };
  w.ChannelIO = ch;
  function l() {
    if (w.ChannelIOInitialized) {
      return;
    }
    w.ChannelIOInitialized = true;
    var s = document.createElement("script");
    s.type = "text/javascript";
    s.async = true;
    s.src = "https://cdn.channel.io/plugin/ch-plugin-web.js";
    s.charset = "UTF-8";
    var x = document.getElementsByTagName("script")[0];
    x.parentNode.insertBefore(s, x);
  }
  if (document.readyState === "complete") {
    l();
  } else if (window.attachEvent) {
    window.attachEvent("onload", l);
  } else {
    window.addEventListener("DOMContentLoaded", l, false);
    window.addEventListener("load", l, false);
  }
})();
ChannelIO("boot", {
  pluginKey: "d2d07e63-a51a-4430-a67e-c98a96950f2e",
});
</script>
<style></style>
