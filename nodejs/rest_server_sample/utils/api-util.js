const return_success = (res, code = 1001, data, message = "") => {
  res.send({ success: true, code: code, data: data, message: message });
};
const return_fail = (res, code = 9999, data, message = "") => {
  res.send({
    success: false,
    code: code,
    data: data instanceof Error ? data.code : data,
    message: message
  });
};

const return_no_session = (res, message = "") => {
  return_fail(res, CODE.USER.NO_SESSION, "no_session", message);
};

const asyncWrapper = require("./async/async-wrapper").AsyncWrapper;

const CODE = {
  USER: {
    NO_SESSION: "NO_SESSION",
    NO_USER: "NO_USER",
    MULTI_EMAIL_USER: "MULTI_EMAIL_USER",
    ALREADY_EXIST_USER: "ALREADY_EXIST_USER",
    GOOGLE_LOGIN_ERROR: "GOOGLE_LOGIN_ERROR",
    MULTI_USER_EMAIL: "MULTI_USER_EMAIL"
  },
  DATABASE: {
    SELECT_ERROR: "SELECT_ERROR",
    INSERT_ERROR: "INSERT_ERROR",
    UPDATE_ERROR: "UPDATE_ERROR",
    DELETE_ERROR: "DELETE_ERROR"
  },
  RETURN: {
    SUCCESS: "1001",
    FAIL: "9999"
  },
  AJAX: {
    PARAMS_ERROR: "PARAMS_ERROR"
  }
};
module.exports.api_common = {
  return_success,
  return_no_session,
  return_fail,
  asyncWrapper,
  CODE
};
