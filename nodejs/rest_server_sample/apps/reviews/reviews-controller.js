const router = require("express").Router();

const memroy = require("../../memory/memory").memory;

const { save, update, remove, list } = require("./reviews-service");

const { sessionHaveToLogin } = require("../../utils/session");

const {
  return_fail,
  return_success,
  asyncWrapper,
  CODE
} = require("../../utils/api-util").api_common;

//api/reviews/save
router.post(
  "/---",
  asyncWrapper(async (req, res) => {
    sessionHaveToLogin(req, res).then(loginUser => {
      stationsNo = req.query.stations_no;
      message = req.query.message;
      save(req, stationsNo, message)
        .then(data => return_success(res, 1001, "", ""))
        .catch(err => return_fail(res, 9999, err, ""));
    });
  })
);

router.post(
  "/update",
  asyncWrapper(async (req, res) => {
    sessionHaveToLogin(req, res).then(loginUser => {
      id = req.query.id;
      message = req.query.message;

      update(id, message)
        .then(data => return_success(res, 1001, "", ""))
        .catch(err => return_fail(res, 9999, err, ""));
    });
  })
);

router.post(
  "/---",
  asyncWrapper(async (req, res) => {
    sessionHaveToLogin(req, res).then(loginUser => {
      id = req.query.id;

      remove(id, message)
        .then(data => return_success(res, 1001, data, ""))
        .catch(err => return_fail(res, 9999, err, ""));
    });
  })
);

router.post(
  "/---",
  asyncWrapper(async (req, res) => {
    sessionHaveToLogin(req, res).then(loginUser => {
      stationsNo = req.query.stations_no;
      clientId = req.query.client_id;

      list(stationsNo, clientId)
        .then(data => return_success(res, 1001, data, ""))
        .catch(err => return_fail(res, 9999, err, ""));
    });
  })
);

module.exports = router;
