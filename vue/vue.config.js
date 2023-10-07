const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/user/*': {
        target: 'http://localhost:80',
        ws: true,
        changeOrigin: true
      }
    }
  }
})
