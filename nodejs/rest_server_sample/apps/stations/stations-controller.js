const router = require("express").Router();
const memroy = require("../../memory/memory").memory;
const { merge, find, search, findByLocation } = require("./stations-service");

const {
  return_fail,
  return_success,
  asyncWrapper,
  CODE
} = require("../../utils/api-util").api_common;

// json -> DB 머지.. 이제 사용하면 안됨!!!!
// router.post(
//   "/merge",
//   asyncWrapper(async (req, res) => {
//     if (sessionCheck(req) === false) {
//       return_fail(res, CODE.USER.NO_SESSION, "no_session", "");
//     } else {
//       merge().then(
//         data => return_success(res, 1001, data, ""),
//         err => return_fail(res, 9999, err, "")
//       );
//     }
//   })
// );

// 전체 조회
//api/stations
router.post(
  "/",
  asyncWrapper(async (req, res) => {
    lat = req.query.lat;
    lng = req.query.lng;
    findByLocation(-1, "ASC", lat, lng)
      .then(data => return_success(res, 1001, data, ""))
      .catch(err => return_fail(res, 9999, err, ""));
    // let data = memroy.get('stations-list').getList()
    // return_success(res, 1001, data, '')
  })
);

// no 조회
// /api/stations/12/DESC
router.post(
  "/---",
  asyncWrapper(async (req, res) => {
    id = req.query.id;
    sortType = req.query.sort_type;

    find(id, "id", sortType)
      .then(data => return_success(res, 1001, data, ""))
      .catch(err => return_fail(res, 9999, err, ""));

    // let data = memroy.get('stations-list').getOne(req.params.id)
    // return_success(res, 1001, data, '')
  })
);

router.post(
  "/---",
  asyncWrapper(async (req, res) => {
    searchField = req.query.search_field;
    searchWord = req.query.search_word;
    sortType = req.query.sort_type;

    search(searchField, searchWord, "no", sortType)
      .then(data => return_success(res, 1001, data, ""))
      .catch(err => return_fail(res, 9999, err, ""));

    // let data = memroy.get('stations-list').find(req.params.search_word)
    // return_success(res, 1001, data, '')
  })
);

module.exports = router;
