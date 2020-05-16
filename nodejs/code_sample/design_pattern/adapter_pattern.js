class Lion {
  run() {}
}

class KoreaLion extends Lion {
  run() {
    console.log("asian lion run");
  }
}

class TaiwanLion extends Lion {
  run() {
    console.log("taiwan lion run");
  }
}

class Hunter {
  hunting(lion) {
    lion.run();
  }
}

h = new Hunter();
h.hunting(new KoreaLion());
h.hunting(new TaiwanLion());

class BadDog {
  walk() {
    console.log("bad dog walk");
  }
}

class BadDogAdapter extends Lion {
  constructor(dog) {
    super();
    this.dog = dog;
  }
  run() {
    this.dog.walk();
  }
}
h.hunting(new BadDogAdapter(new BadDog()));
