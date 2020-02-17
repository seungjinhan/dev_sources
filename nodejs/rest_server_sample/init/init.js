'use strict'

const stationList = require('../data/stations-list').stationsList
const memory = require('../memory/memory').memory

module.exports = function init() {

    memory.set('stations-list', stationList)
}
