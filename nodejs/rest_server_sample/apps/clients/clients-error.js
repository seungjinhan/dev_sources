const ErrorBase = require("../../errors/base/ErrorBase");

class ClientsError extends ErrorBase {
  constructor(code, message, error) {
    super(code, message, error);
    this.name = this.constructor.name;
    Error.captureStackTrace(this, this.constructor);
  }
}

module.exports = ClientsError;
