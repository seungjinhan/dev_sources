const bodyParser = require("body-parser");
const morgan = require("morgan");
const cors = require("cors");
const helmet = require("helmet");
const session = require("express-session");
const interceptor = require("express-interceptor");
const FileStore = require("session-file-store")(session);

module.exports = function CommonMiddleware(app) {
  app.use(bodyParser.json());
  app.use(morgan("common")); // https://www.npmjs.com/package/morgan
  app.use(cors()); // https://www.npmjs.com/package/cors
  app.use(helmet()); // https://www.npmjs.com/package/helmet
  app.use(
    session({
      secret: "---", // 쿠키를 임의로 변조하는것을 방지하기 위한 값
      resave: false, // 세션을 언제나 저장할지 정하는 값
      saveUninitialized: true, // 세션이 저장되기 전에 unitialized상태로 미리 만들어서 저장
      store: new FileStore()
    })
  );
};
