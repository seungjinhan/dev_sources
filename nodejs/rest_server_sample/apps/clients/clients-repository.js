const { CODE } = require("../../utils/api-util").api_common;
const { query } = require("../../utils/db-util");

// 사용자 저장
module.exports.save = client => {
  return new Promise(function(resolve, reject) {
    const SQL = `INSERT INTO ______
                        (
                        created,
                        updated,
                        email,
                        like_stations,
                        is_open,
                        code,
                        nickname,
                        user_type)
                        VALUES
                        (
                        NOW(),
                        NOW(),
                        '${client.email}',
                        '${client.likeStations}',
                         ${client.isOpen}, 
                         '${client.code}' ,
                         '${client.nickname}', 
                         '${client.userType}' 
                         )`;
    query(SQL, resolve, reject, CODE.DATABASE.INSERT_ERROR);
  });
};

// 이메일로 조회
module.exports.findByEmail = function findByEmail(email) {
  return new Promise(function(resolve, reject) {
    const SQL = `SELECT * FROM ______ WHERE email='${email}'`;
    query(SQL, resolve, reject, CODE.DATABASE.SELECT_ERROR);
  });
};

module.exports.findByEmailAndCode = (email, code) => {
  return new Promise(function(resolve, reject) {
    const SQL = `SELECT * FROM ______ WHERE email='${email}' AND code='${code}'`;
    query(SQL, resolve, reject, CODE.DATABASE.SELECT_ERROR);
  });
};

// 충전소 즐겨찾기 추가
module.exports.addStationsLike = (email, stationsNo) => {
  return new Promise(function(resolve, reject) {
    const SQL = `UPDATE ______ 
                        SET like_stations = 
                                IF( INSTR(like_stations, '#${stationsNo}@')>0 , 
                                    like_stations ,
                                    CONCAT(like_stations , '#${stationsNo}@')) 
                        WHERE email='${email}'`;
    query(SQL, resolve, reject, CODE.DATABASE.UPDATE_ERROR);
  });
};

// 충전소 즐겨찾기 제거
module.exports.removeStationsLike = (email, stationsNo) => {
  return new Promise(function(resolve, reject) {
    const SQL = `UPDATE ______ SET like_stations = REPLACE( like_stations , '#${stationsNo}@' , '' ) WHERE email='${email}'`;
    query(SQL, resolve, reject, CODE.DATABASE.UPDATE_ERROR);
  });
};
