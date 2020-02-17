const {
  stationsSave,
  find,
  search,
  deleteAll,
  findByLocation
} = require("./stations-repository");
const memroy = require("../../memory/memory").memory;

module.exports.merge = () => {
  return new Promise((resolve, reject) => {
    const datas = memroy.get("stations-list").getList();

    deleteAll().then(
      data =>
        datas.forEach(data => {
          stationsSave(data)
            .then(d => resolve(d))
            .catch(e => reject(e));
        }),
      err => reject(err)
    );
  });
};

// 검색하기
module.exports.find = (no, order_field, order_type) => {
  return new Promise((resolve, reject) =>
    find(no, order_field, order_type)
      .then(data => resolve(data))
      .catch(err => reject(err))
  );
};

// 위치 기반으로 조회
module.exports.findByLocation = (no, order_type, lat, lng) => {
  return new Promise((resolve, reject) =>
    findByLocation(no, order_type, lat, lng)
      .then(data => resolve(data))
      .catch(err => reject(err))
  );
};

module.exports.search = (
  search_field,
  search_word,
  order_field,
  order_type
) => {
  return new Promise((resolve, reject) =>
    search(search_field, search_word, order_field, order_type)
      .then(data => resolve(data))
      .catch(err => reject(err))
  );
};
