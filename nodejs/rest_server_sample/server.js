const express = require('express')
const dotenv = require('dotenv')
const Middleware = require('./middleware/middleware')
const ErrorHandlingMiddleware = require('./middleware/error-handling.js')

const StationController = require('./apps/stations/stations-controller')
const UserController = require('./apps/clients/clients-controller')
const ReviewsController = require('./apps/reviews/reviews-controller')

const init = require('./init/init')

dotenv.config()

const PORT = process.env.PORT || 3000;

const app = express()

// 초기화
init()

// 미들웨어 세팅
Middleware(app)

app.use('/api/stations', StationController)
app.use('/api/users', UserController)
app.use('/api/reviews', ReviewsController)

// 에러세팅
ErrorHandlingMiddleware(app)

app.listen(PORT, () => {
    console.log(`Server listening on port ${PORT}`)
})