package diplom.dev.aidhealth.db.model

class CourseWithStatus {
    var statusTitle = ""
    var pointDay =""
    var pointTime = ""
    var courseID = 0
    var pointID = 0

    constructor(statusTitle: String, pointDay: String, pointTime: String, courseID: Int, pointID: Int){
        this. statusTitle = statusTitle
        this.pointDay = pointDay
        this.pointTime = pointTime
        this.courseID = courseID
        this.pointID = pointID
    }
    constructor(){

    }
}

