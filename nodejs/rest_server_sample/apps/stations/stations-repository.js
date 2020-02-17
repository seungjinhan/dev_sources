const { CODE } = require("../../utils/api-util").api_common;
const { query } = require("../../utils/db-util");

// 저장하기
module.exports.______Save = function save(______) {
  return new Promise(function(resolve, reject) {
    const SQL = `INSERT INTO ______ 
                                (
                                created,
                                updated,
                                no,
                                id,
                                chargerType,
                                local,
                                title,
                                address,
                                addressDetail,
                                phone,
                                portNum,
                                USType,
                                EUType,
                                freePrice,
                                freePriceDetail,
                                openTime,
                                closeTime,
                                kw,
                                tip,
                                direction)
                            VALUES
                            (
                            NOW(),
                            NOW(),
                            '${______.key}',
                            '${______.id}',
                            '${______.chargerType}',
                            '${______.local}',
                            '${______.title}',
                            '${______.address}',
                            '${______.addressDetail}',
                            '${______.phone}',
                            ${______.portNum},
                            ${______.USType},
                            ${______.EUType},
                            '${______.freePrice}',
                            '${______.freePriceDetail}',
                            '${______.openTime}',
                            '${______.closeTime}',
                            ${______.kw},
                            '${______.tip}',
                            '${______.direction}')`;

    query(SQL, resolve, reject, CODE.DATABASE.INSERT_ERROR);
  });
};

module.exports.deleteAll = () => {
  return new Promise((resolve, reject) => {
    const SQL = "DELETE FROM ______ WHERE no > -1";
    query(SQL, resolve, reject, CODE.DATABASE.DELETE_ERROR);
  });
};
module.exports.find = (no, order_field, _order_type) => {
  return new Promise((resolve, reject) => {
    let WHERE = ` 1=1 `;

    let order_type = "DESC";
    if (_order_type.toUpperCase() === "ASC") {
      order_type = "ASC";
    }

    if (no > 0) {
      WHERE += ` AND no=${no}`;
    }

    const SQL = `SELECT * FROM ______ WHERE ${WHERE} order by ${order_field} ${order_type}`;

    query(SQL, resolve, reject, CODE.DATABASE.SELECT_ERROR);
  });
};

module.exports.findByLocation = (no, _order_type, lat, lng) => {
  return new Promise((resolve, reject) => {
    let WHERE = ` 1=1 `;

    let order_type = "DESC";
    if (_order_type.toUpperCase() === "ASC") {
      order_type = "ASC";
    }

    if (no > 0) {
      WHERE += ` AND no=${no}`;
    }

    const SQL = `SELECT *,  ( pow(lat - ${lat} , 2) + pow(lng - ${lng} , 2)) AS temp FROM ______ WHERE ${WHERE} ORDER BY temp ${order_type}`;

    query(SQL, resolve, reject, CODE.DATABASE.SELECT_ERROR);
  });
};

module.exports.search = (
  search_field,
  search_word,
  order_field,
  _order_type
) => {
  return new Promise((resolve, reject) => {
    let WHERE = ` 1=1 `;

    let order_type = "DESC";
    if (_order_type.toUpperCase() === "ASC") {
      order_type = "ASC";
    }

    WHERE += ` AND ${search_field} like '%${search_word}%' `;

    const SQL = `SELECT * FROM ______ WHERE ${WHERE} order by ${order_field} ${order_type}`;
    query(SQL, resolve, reject, CODE.DATABASE.SELECT_ERROR);
  });
};
