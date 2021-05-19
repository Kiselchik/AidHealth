package diplom.dev.aidhealth.db.model

import android.app.Notification
import android.provider.ContactsContract

class Course {
    var id: Int = 0
    var email: String = ""
    var procedure: Int = 0 //
    var medicament: Int = 0 //
    var doctor: Int = 0 //
    var food: String ="" //добавить
    var title: String = "" //
    var timeCheckSymptom = "" //
    var timeHealthNotification = "" //
    var notification = "" //
    var date = ""
    var descr = ""
    var diagnosisSymptomID: Int = 0 //добавить

    constructor(email: String, procedure: Int, medicament: Int, doctor: Int,
    food: String, title: String, timeCheckSymptom: String, timeHealthNotification: String, notification: String,
    date: String, descr: String, diagnosisSymptomID: Int) {
        this.email = email
        this.procedure = procedure
        this.medicament = medicament
        this.doctor = doctor
        this.food = food
        this.title = title
        this.timeCheckSymptom = timeCheckSymptom
        this.timeHealthNotification = timeHealthNotification
        this.notification = notification
        this.date = date
        this.descr = descr
        this.diagnosisSymptomID = diagnosisSymptomID

    }

    constructor(){

    }
}