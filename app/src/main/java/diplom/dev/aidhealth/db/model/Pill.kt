package diplom.dev.aidhealth.db.model

class Pill {
    var id: Int = 0//
    var email: String = ""//
    var title: String = ""//
    var pack: Int = 0//
    var unity: Float = 0f //
    var measurement: String = "" //добавить
    //добавить сюда описание (для чего лекарство)
   // var lost: String = "" //Float

    constructor(title: String, pack: Int, unity: Float, measurement: String, email: String ) {
        this.title = title
        this.pack = pack
        this.unity = unity
        this.measurement = measurement
        this.email = email

    }

    constructor(){

    }
}