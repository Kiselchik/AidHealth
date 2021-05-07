package diplom.dev.aidhealth.db.model

class CoursePoint {
    var id: Int= 0
    var courseID: Int= 0
    var day: String = ""
    var time: String=""
    var statusPointID: Int = 0

    constructor(courseID: Int, day: String, time: String, statusPointID: Int) {
        this.courseID= courseID
        this.day = day
        this.time = time
        this. statusPointID = statusPointID
    }
    constructor(){

    }

}