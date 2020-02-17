const { save, update, remove, list } = require("./reviews-repository");

const { sessionGetUser } = require("../../utils/session");

const Reviews = require("./reviews-model");

// 리뷰저장
module.exports.save = (req, stationsNo, message) => {
  return new Promise((resolve, reject) => {
    user = sessionGetUser(req);

    const review = new Reviews()
      .clientId(user.id)
      .isOpen(true)
      .stationsNo(stationsNo)
      .message(message);

    save(review)
      .then(data => resolve(data))
      .catch(err => reject(err));
  });
};

module.exports.update = (id, message) => {
  return new Promise((resolve, reject) => {
    update(id, message)
      .then(data => resolve(data))
      .catch(err => reject(err));
  });
};

module.exports.remove = no => {
  return new Promise((resolve, reject) => {
    remove(no)
      .then(data => resolve(data))
      .catch(err => reject(err));
  });
};

module.exports.list = (stationsNo, clientId) => {
  return new Promise((resolve, reject) => {
    list(stationsNo, clientId)
      .then(data => resolve(data))
      .catch(err => reject(err));
  });
};
