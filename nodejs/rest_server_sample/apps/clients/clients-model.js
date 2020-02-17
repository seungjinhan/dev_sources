"use strict";

class Clients {
  constructor() {
    return this;
  }

  id(id) {
    this.id = id;
    return this;
  }

  email(email) {
    this.email = email;
    return this;
  }

  created(created) {
    this.created = created;
    return this;
  }

  updated(updated) {
    this.updated = updated;
    return this;
  }

  likeStations(likeStations) {
    this.likeStations = likeStations;
    return this;
  }

  isOpen(isOpen) {
    this.isOpen = isOpen;
    return this;
  }

  code(code) {
    this.code = code;
    return this;
  }

  nickname(nickname) {
    this.nickname = nickname;
    return this;
  }

  userType(userType) {
    this.userType = userType;
    return this;
  }
}

module.exports = Clients;
