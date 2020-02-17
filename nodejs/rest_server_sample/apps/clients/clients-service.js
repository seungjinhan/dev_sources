const Clients = require("./clients-model");
const clientRepository = require("./clients-repository");
const ClientsError = require("./clients-error");
const { now } = require("../../utils/date-util");
const { CODE } = require("../../utils/api-util").api_common;

module.exports.save = (email, code, nickname, user_type) => {
  return new Promise((resolve, reject) => {
    client = new Clients();
    client
      .email(email)
      .likeStations("")
      .isOpen(true)
      .code(code)
      .nickname(nickname)
      .userType(user_type);

    // 저장후 =>이메일 조회해서 반환
    clientRepository.save(client).then(
      res => {
        this.findByEmail(email)
          .then(user => resolve(user))
          .catch(err => reject(err));
      },
      err => reject(err)
    );
  });
};

// 이메일로 사용자 조회
module.exports.findByEmail = email => {
  return new Promise((resolve, reject) =>
    clientRepository
      .findByEmail(email)
      .then(data => runCheckUserLengthForEmail(resolve, reject, data))
      .catch(err => reject(err))
  );
};

module.exports.findByEmailAndCode = (email, code) => {
  return new Promise((resolve, reject) =>
    clientRepository
      .findByEmailAndCode(email, code)
      .then(data => runCheckUserLengthForEmail(resolve, reject, data))
      .catch(err => reject(err))
  );
};

// 충전소 즐겨찾기 추가
module.exports.addStationsLike = (email, stationsNo) => {
  return new Promise((resolve, reject) =>
    clientRepository
      .addStationsLike(email, stationsNo)
      .then(_ =>
        this.findByEmail(email)
          .then(user => resolve(user))
          .catch(err => reject(err))
      )
      .catch(err => reject(err))
  );
};

// 충전소 즐겨찾기 제거
// 변경된 정보의 사용자 반환
module.exports.removeStationsLike = (email, stationsNo) => {
  return new Promise((resolve, reject) =>
    clientRepository
      .removeStationsLike(email, stationsNo)
      .then(_ => {
        this.findByEmail(email)
          .then(user => resolve(user))
          .catch(err => reject(err));
      })
      .catch(err => reject(err))
  );
};

// 이메일 호출 결과에 따른 사용자 처리
function runCheckUserLengthForEmail(resolve, reject, result) {
  if (result.length === 1) {
    resolve(result[0]);
  } else if (result.length === 0) {
    reject(CODE.USER.NO_USER);
  } else if (result.length > 1) {
    reject(CODE.USER.MULTI_EMAIL_USER);
  }
}

// module.exports.app_save = email => {
//   return new Promise((resolve, reject) => {
//     this.findByEmail(email).then(
//       users => {
//         // 이메일의 사용자가 있으면 reject처리
//         if (users.length > 0) {
//           reject(CODE.USER.ALREADY_EXIST_USER);
//         }
//         // 없으면 저장
//         else {
//           _save(email).then(
//             newUser => resolve(newUser),
//             err => reject(err)
//           );
//         }
//       },
//       err => reject(err)
//     );
//   });
// };

// // 사용자 저장
// // 이메일이 있다면 - 로그인처리
// module.exports.save = email => {
//   return new Promise((resolve, reject) => {
//     // 이메일 체크
//     clientRepository.findByEmail(email).then(
//       users => {
//         // 이미 이메일이 있으면 로그인 처리
//         if (users.length > 0) {
//           resolve(users[0]);
//         }
//         // 없으면 저장
//         else {
//           _save(email).then(
//             newUser => resolve(newUser),
//             err => reject(err)
//           );
//         }
//       },
//       err => reject(err)
//     );
//   });
// };
