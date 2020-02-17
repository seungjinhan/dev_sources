class ErrorBase extends Error {

    constructor(code, message, error) {

        super(error);

        this.code = code;
        this.message = message;
        this.error = error
    }
}

module.exports = ErrorBase;