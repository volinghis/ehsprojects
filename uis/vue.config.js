// vue.config.js
// const CompressionPlugin = require('compression-webpack-plugin')
const path = require('path')// 引入path模块
function resolve (dir) {
  return path.join(__dirname, dir)// path.join(__dirname)设置绝对路径
}

module.exports = {
  outputDir: './src/main/resources/static',
  productionSourceMap: false,
  transpileDependencies: ['resize-detector'],
  css: {
    loaderOptions: {
      sass: {
        prependData: `@import "~@/styles/variables.scss";`
      }
    }
  },
  devServer: {
    proxy: {
      '/ehs': {
        target: 'http://127.0.0.1:18087/ehs',
        changeOrigin: true,
        pathRewrite: {
          '^/ehs': '' // rewrite path
        }
      }

    }
  },
  configureWebpack: config => {
    // if (process.env.NODE_ENV !== 'production') {
    //   config.entry.app = ['./src/mocks/index.js', './src/main.js']
    // }
    config.resolve.alias = {
      '@': resolve('./src'),
      '@components': resolve('./src/components')
    }
  }

}
