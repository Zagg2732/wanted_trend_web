<template>
  <HeaderTitle />
  <router-view/>
</template>

<script>
import HeaderTitle from "@/layouts/HeaderTitle";

export default {
  components: {HeaderTitle}
}
</script>

<style>

/* css */
@import "assets/templates/css/style.css";

@import "assets/templates/vendor/boxicons/css/boxicons.min.css";
@import "assets/templates/vendor/quill/quill.snow.css";
@import "assets/templates/vendor/quill/quill.bubble.css";
@import "assets/templates/vendor/remixicon/remixicon.css";
@import "assets/templates/vendor/simple-datatables/style.css";


#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
