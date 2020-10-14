const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
   entry: './frontend/main.js',
   output: {
      path: path.join(__dirname, '/bundle'),
      filename: 'index_bundle.js'
   },
   devServer: {
      inline: true,
      port: 8001
   },
   module: {
      rules: [
         {
            test: /\.js$|jsx/,
            exclude: /node_modules/,
            loader: 'babel-loader',
            query: {
               presets: ["@babel/preset-env"]
            }
         },{
            test: /\.css$/,
            use: ['style-loader', 'css-loader']
          }
      ]
   },
   plugins:[
      new HtmlWebpackPlugin({
         template: 'frontend/index.html'
      })
   ]
}