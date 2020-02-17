'use strict'

class Reviews {

    constructor() {
        return this;
    }

    clientId(clientId) {
        this.clientId = clientId;
        return this;
    }

    message(message) {
        this.message = message;
        return this;
    }

    isOpen(isOpen) {
        this.isOpen = isOpen;
        return this;
    }

    stationsNo(stationsNo) {
        this.stationsNo = stationsNo;
        return this;
    }
}

module.exports = Reviews;