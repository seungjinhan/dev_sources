const db = require("../database/db");
const DatabaseError = require("../database/database-error");

// 쿼리를 처리 하는 유틸리티
module.exports.query = (SQL, resolve, reject, errorCode) => {
  try {
    console.log(SQL);
    db.query(SQL, function(err, rows, fields) {
      if (err) {
        e = new DatabaseError(errorCode, SQL, err);
        console.error(e);
        reject(e);
      } else {
        resolve(rows);
      }
    });
  } catch (error) {
    e = new DatabaseError(errorCode, SQL, error);
    console.error(e);
    reject(e);
  }
};
