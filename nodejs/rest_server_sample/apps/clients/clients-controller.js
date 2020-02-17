const request = require("request");
const router = require("express").Router();
const dotenv = require("dotenv");

//const { google } = require("googleapis");

const clientsService = require("./clients-service");

const { sessionSave, sessionHaveToLogin } = require("../../utils/session");

const {
  return_fail,
  return_success,
  asyncWrapper,
  CODE
} = require("../../utils/api-util").api_common;

dotenv.config();

// 이메일로 사용자 조회
// localhost:3000/api/users/find_by_email
router.post(
  "/---",
  asyncWrapper(async (req, res) => {
    const email = req.query.email;
    clientsService
      .findByEmail(email)
      .then(users => {
        if (users.length > 0) {
          return_success(res, CODE.RETURN.SUCCESS, users, "");
        } else {
          return_fail(res, CODE.USER.NO_USER, "", "");
        }
      })
      .catch(err => return_fail(res, CODE.RETURN.FAIL, err, ""));
  })
);

// 즐겨 찾기 추가
// /api/users/add_stations_like?stationsNo=1
router.post(
  "/---",
  asyncWrapper(async (req, res) => {
    sessionHaveToLogin(req, res).then(loginUser => {
      const _stationsNo = req.query.stations_no;
      if (_stationsNo === undefined) {
        return_fail(
          res,
          CODE.AJAX.PARAMS_ERROR,
          `stations_no is undefined`,
          ""
        );
      } else {
        stationsNo = parseInt(_stationsNo);

        if (!isNaN(stationsNo)) {
          const result = clientsService
            .addStationsLike(loginUser.email, stationsNo)
            .then(data => return_success(res, CODE.RETURN.SUCCESS, data, ""))
            .catch(err => return_fail(res, CODE.RETURN.FAIL, err, ""));
        } else {
          return_fail(res, CODE.AJAX.PARAMS_ERROR, _stationsNo, "");
        }
      }
    });
  })
);

// 즐겨찾기 제거
// /api/users/remove_stations_like?stationsNo=1
router.post(
  "/remove_stations_like",
  asyncWrapper(async (req, res) => {
    sessionHaveToLogin(req, res).then(loginUser => {
      const stationsNo = req.query.stations_no;
      const result = clientsService
        .removeStationsLike(loginUser.email, stationsNo)
        .then(data => return_success(res, CODE.RETURN.SUCCESS, data, ""))
        .catch(err => return_fail(res, CODE.RETURN.FAIL, err, ""));
    });
  })
);

// 앱 로그인 처리
// /api/users/app_login?email=1
router.post(
  "/---",
  asyncWrapper(async (req, res) => {
    const email = req.query.email;
    const code = req.query.code;
    const type = req.query.type;
    const nickname = req.query.nickname;

    clientsService
      .findByEmailAndCode(email, code)
      .then(user => {
        sessionSave(req, user);
        return_success(res, CODE.RETURN.SUCCESS, user, "");
      })
      .catch(err => {
        // 사용자가 없으면 가입처리 함
        if (err == CODE.USER.NO_USER) {
          clientsService
            .save(email, code, nickname, type)
            .then(newUser => {
              // 가입처리 하고 로그인처리 함
              sessionSave(req, newUser);
              return_success(res, CODE.RETURN.SUCCESS, newUser, "");
            })
            .catch(err => return_fail(res, CODE.RETURN.FAIL, err, ""));
        } else {
          return_fail(res, CODE.RETURN.FAIL, err, "");
        }
      });
    // .then(users => {
    //   // 사용자가 없으면 가입처리 해아 함
    //   if (users.length === 0) {
    //     clientsService
    //       .save(email, code, nickname, type)
    //       .then(newUser => {
    //         // 가입처리 하고 로그인처리 함
    //         sessionSave(req, newUser);
    //         return_success(res, CODE.RETURN.SUCCESS, newUser, "");
    //       })
    //       .catch(err => return_fail(res, CODE.RETURN.FAIL, err, ""));
    //     // 사용자가 있으면 로그인 처리 함
    //   } else {
    //     sessionSave(req, users);
    //     return_success(res, CODE.RETURN.SUCCESS, users, "");
    //   }
    // })
    // .catch(err => return_fail(res, CODE.RETURN.FAIL, err, ""));
  })
);

// 사용자 세션 체크
// /api/users/check_session
router.post(
  "/check_session",
  asyncWrapper(async (req, res) => {
    sessionHaveToLogin(req, res).then(user =>
      return_success(res, CODE.RETURN.SUCCESS, user, "")
    );
  })
);

module.exports = router;

// 앱에서 사용자 가입 처리
// /api/users/app_join?email=1
// router.post(
//   "/app_join",
//   asyncWrapper(async (req, res) => {
//     const email = req.query.email;
//     const result = clientsService.app_save(email);
//     result.then(
//       data => return_success(res, CODE.RETURN.SUCCESS, data, ""),
//       err => return_fail(res, CODE.RETURN.FAIL, err, "")
//     );
//   })
// );

// const CLIENT_ID = process.env.GOOGLE_CLIENT_ID;
// const CLIENT_SECRET = process.env.GOOGLE_CLIENT_SECRET;
// const REDIRECT_URL = process.env.GOOGLE_REDIRECT_URL;

// const oAuth2Client = new google.auth.OAuth2(
//   CLIENT_ID,
//   CLIENT_SECRET,
//   REDIRECT_URL
// );

//var authed = false;

// 이메일을 조회
// function getUsersEmailFromGoogle(auth) {
//   return new Promise(function(resolve, reject) {
//     const gmail = google.gmail({ version: "v1", auth });
//     gmail.users.getProfile(
//       {
//         auth: auth,
//         userId: "me"
//       },
//       (err, data) => {
//         if (err) {
//           reject(new ClientsError(CODE.USER.GOOGLE_LOGIN_ERROR, "", err));
//         }
//         resolve(data["data"]["emailAddress"]);
//       }
//     );
//   });
// }

// // 구글 로그인
// // localhost:3000/api/user/auth/google
// router.get(
//   "/auth/google",
//   asyncWrapper(async (req, res) => {
//     if (!authed) {
//       // Generate an OAuth URL and redirect there
//       const url = oAuth2Client.generateAuthUrl({
//         access_type: "offline",
//         scope: "https://www.googleapis.com/auth/gmail.readonly"
//       });
//       res.redirect(url);
//     } else {
//       res.send(`logined in ${sessionGetUser(req)}`);
//     }
//   })
// );

// // localhost:3000/api/user/auth/google/callback
// router.get(
//   "/auth/google/callback",
//   asyncWrapper(async (req, res) => {
//     const code = req.query.code;

//     if (code) {
//       // Get an access token based on our OAuth code
//       oAuth2Client.getToken(code, function(err, tokens) {
//         if (err) {
//           console.log("Error authenticating");
//           console.log(err);
//         } else {
//           console.log("Successfully authenticated");
//           oAuth2Client.setCredentials(tokens);
//           authed = true;
//           /**
//                 {
//                   access_token: 'ya29.Il-5B69iKvE9muEfC5y3TM2LGsC5cbPcW6SrFVhgGAGgKiscHnMIL0gJKlX2sYzebDDP0v4OL74Y68o1X_ps-_vn-Sue8U9dPh6p-OJb_PmA2sctK1eYs8xIf6gc2DgSUQ',
//                   refresh_token: '1//0exr_y8sNDkmCCgYIARAAGA4SNwF-L9IrTgKVGA2HXpOT8p86AYXKhiEvtlkZezVOEiSZPsSm5KpX7id23U5DBA6TuM4IN9m6Zhs',
//                   scope: 'https://www.googleapis.com/auth/gmail.readonly',
//                   token_type: 'Bearer',
//                   expiry_date: 1578808640754
//                 }
//                 */

//           // 사용자 이메일을 가져옴
//           getUsersEmailFromGoogle(oAuth2Client).then(
//             gmail => {
//               // 새로운 사용자 저장
//               clientsService.save(gmail).then(
//                 user => {
//                   sessionSave(req, user);
//                   return_success(res, CODE.RETURN.SUCCESS, user, "");
//                 },
//                 err => {
//                   return_fail(res, CODE.RETURN.FAIL, err, "");
//                 }
//               );
//             },
//             gmailErr => return_fail(res, CODE.RETURN.FAIL, gmailErr, "")
//           );
//         }
//       });
//     }
//   })
// );
