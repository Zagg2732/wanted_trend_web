const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  outputDir: '../src/main/resources/static',
  publicPath: '/',
  devServer:{
    proxy: {
      port: 3000,
      '^/api' : {
        target: {
          host: 'localhost',
          protocol: 'http',
          port: 8080
        },
        ws: true,
        changeOrigin: true
      }
    }
  }

})
