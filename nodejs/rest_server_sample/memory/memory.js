class Memoy{
    
    constructor() {
        if (!Memoy.instance) {
            Memoy.instance = this

            this.box = new Map()
        }
        return Memoy.instance
    }

    get(key){
        return this.box.get(key)
    }

    set(key, value){
        this.box.set(key, value)
    }

    length(){
        this.box.length
    }
    
    keys(){
        this.box.keys()
    }

    value(){
        this.box.values()
    }
}

_memory = new Memoy()

module.exports.memory = _memory