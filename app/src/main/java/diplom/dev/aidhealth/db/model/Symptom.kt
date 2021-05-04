package diplom.dev.aidhealth.db.model

class Symptom {
    var id: Int = 0
    var email: String = ""
    var title: String = ""
    var units: Int = 0
    var ball: Int = 0

    constructor(email: String, title: String, units: Int, ball: Int){
        this.email = email
        this.title = title
        this.units = units
        this.ball = ball
    }
    constructor(){

    }
}