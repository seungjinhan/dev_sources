'use strict'

const fs = require('fs')
const sortUtil = require('../utils/sort')
const searchUtil = require('../utils/search')

class StationList {

    stationMap = new Map()
    stationArray = []
    constructor() {

        if (!StationList.instance) {

            console.log('Stations List 생성')
            StationList.instance = this

            let stationsList = fs.readFileSync(__dirname + '/stations-list.json')
            let stationsJson = JSON.parse(stationsList)

            for (let i = 0; i < stationsJson.length; i++) {

                const element = stationsJson[i];
                this.stationMap.set("" + element.key, element)
                this.stationArray.push(element)
            }
        }

        return StationList.instance
    }

    getList() {
        return this.stationArray
    }

    getOne(id) {
        return this.stationMap.get(id)
    }

    sort(field, sort_type) {
        let type = 'des'

        if (sort_type == 'asc') {
            type = 'asc'
            return this.stationArray.sort(sortUtil.ArraySortByAsc(field));
        }
        else {

            return this.stationArray.sort(sortUtil.ArraySortByDesc(field));
        }
    }

    find(field) {
        return searchUtil.searchJSonArray(this.stationArray, field)
    }
}

const _stationsList = new StationList();

module.exports.stationsList = _stationsList;