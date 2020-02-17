const CommonMiddleware = require('./detail/common')

module.exports = function Middleware(app) {
    CommonMiddleware(app)
}