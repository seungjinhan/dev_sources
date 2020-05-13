"use strict";

class CacheManager {
  constructor() {
    if (!CacheManager.instance) {
      this._cache = [];
      CacheManager.instance = this;
    }
    return CacheManager.instance;
  }
}

const obj = new CacheManager();
obj._cache.push("####");
console.log(obj._cache);

const obj2 = new CacheManager();
obj2._cache.push("@@@@");
console.log(obj._cache);
