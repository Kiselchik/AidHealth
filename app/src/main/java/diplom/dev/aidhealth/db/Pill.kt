package diplom.dev.aidhealth.db

class Pill {
    var id: Int = 0
   // var food: String ="" //в курс
    var title: String = ""
    var pack: Int = 0
    var unity: Float = 0f //Float
    var measurement: String = ""
   // var lost: String = "" //Float

    constructor(title: String, pack: Int, unity: Float, measurement: String ) {
        this.title = title
        this.pack = pack
        this.unity = unity
        this.measurement = measurement

    }

    constructor(){

    }
}