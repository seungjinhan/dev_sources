const { return_no_session } = require("./api-util").api_common;

module.exports.sessionCheck = req =>
  req.session.user === undefined ? false : true;

module.exports.sessionGetUser = req => req.session.user;

module.exports.sessionSave = (req, user) => (req.session.user = user);

// 로그인 체크 - 성공시 사용자 객체 반환
module.exports.sessionHaveToLogin = (req, res) => {
  return new Promise((resolve, _) => {
    if (this.sessionCheck(req) === false) {
      return_no_session(res, "");
    } else {
      resolve(this.sessionGetUser(req));
    }
  });
};
