const AuthenticationError = require('../errors/errors').AuthenticationError
const AccessDeniedError = require('../errors/errors').AccessDeniedError

function errorLogger(err, req, res, next) {

    if (err.message) {
        console.log(err.message)
    }

    if (err.stack) {
        console.log(err.message)
    }
    next(err)
}

function authenticationErrorHandler(err, req, res, next) {

    if (err instanceof AuthenticationError) {
        return res.sendStatus(401)
    }
    next(err)
}

function accessDeniedErrorHandler(err, req, res, next) {

    if (err instanceof AccessDeniedError) {
        return res.sendStatus(403)
    }
    next(err)
}

function genericErrorHandler(err, req, res, next) {

    res.sendStatus(500)
    next(err)
}

module.exports = function ErrorHandlingMiddleware(app) {
    app.use([
        errorLogger,
        authenticationErrorHandler,
        accessDeniedErrorHandler,
        genericErrorHandler
    ])
}