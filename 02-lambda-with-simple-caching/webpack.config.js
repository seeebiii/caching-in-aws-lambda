const path = require('path');

module.exports = {
    entry: './src/index.js',
    mode: 'production',
    output: {
        path: path.resolve(__dirname, 'target'),
        filename: 'index.js',
        libraryTarget: 'umd'
    },
    target: 'node'
};