const DatabaseError = require("../../database/database-error");
const { CODE } = require("../../utils/api-util").api_common;
const { query } = require("../../utils/db-util");

// 저장하기
module.exports.save = ______ => {
  return new Promise((resolve, reject) => {
    const SQL = `INSERT INTO ______
                        (
                        created,
                        updated,
                        message,
                        is_open,
                        stations_no,
                        clients_id)
                        VALUES(
                        NOW(),
                        NOW(),
                        '${______.message}',
                        ${______.isOpen},
                        ${______.stationsNo},
                        ${______.clientId})`;

    query(SQL, resolve, reject, CODE.DATABASE.INSERT_ERROR);
  });
};

module.exports.list = (stationsNo, clientId) => {
  return new Promise(function(resolve, reject) {
    let where = "";
    if (stationsNo > 0) {
      where += ` AND stations_no = ${stationsNo} `;
    }
    if (clientId > 0) {
      where += ` AND client_id=${clientId} `;
    }
    const SQL = `SELECT * FROM ______ WHERE is_open=true ${where}`;
    query(SQL, resolve, reject, CODE.DATABASE.SELECT_ERROR);
  });
};

// 메세지 내용 수정
module.exports.update = (id, message) => {
  return new Promise(function(resolve, reject) {
    const SQL = `UPDATE ______ SET message = '${message}' WHERE id = ${id}  AND is_open=true`;

    query(SQL, resolve, reject, CODE.DATABASE.UPDATE_ERROR);
  });
};

// 리뷰 제거
module.exports.remove = id => {
  return new Promise(function(resolve, reject) {
    const SQL = `UPDATE ______ SET is_open = false WHERE id = ${id}`;
    query(SQL, resolve, reject, CODE.DATABASE.UPDATE_ERROR);
  });
};
