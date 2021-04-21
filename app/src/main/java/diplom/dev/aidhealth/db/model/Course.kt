package diplom.dev.aidhealth.db.model

class Course {
    var id: Int = 0
    //var procedure: Int = null
    //var medicament: Int = 0
    //var doctor: Int
    //var food: String ="" //в курс
    var title: String = ""
    var timeCheckSymptom = ""
    var timeHealthNotificationn = ""
    var notification: Int = 0
    var date = ""
    var descr = ""

    constructor(title: String, pack: Int, unity: Float, measurement: String ) {


    }

    constructor(){

    }
}