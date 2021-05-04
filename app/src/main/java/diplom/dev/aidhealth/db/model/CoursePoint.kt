package diplom.dev.aidhealth.db.model

class CoursePoint {
    var id: Int= 0
    var courseID: Int= 0
    var datetime: String=""
    var statusPointID: Int = 0

    constructor(courseIF: Int, datetime: String, statusPointID: Int) {
        this.courseID= courseID
        this.datetime = datetime
        this. statusPointID = statusPointID
    }

}