// vue.config.js
const webpack = require('webpack');

module.exports = {
    // https://cli.vuejs.org/config/#devserver-proxy
    devServer: {
        port: 4000,
        proxy: {
            '/api': {
                target: 'http://localhost:4100',
                ws: true,
                changeOrigin: true
            }
        }
    },
    configureWebpack: {
        plugins: [
            new webpack.DefinePlugin({
                '__VUE_PROD_HYDRATION_MISMATCH_DETAILS__': false
            })
        ]
    }
};