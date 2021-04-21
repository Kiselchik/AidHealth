package diplom.dev.aidhealth.db

class Doctor {
    var id: Int = 0
    var firstName: String =""
    var lastName: String = ""
    var position: String = ""
    var address: String = ""

    constructor(firstName: String, lastName: String, position: String, address: String){
        this.firstName = firstName
        this.lastName = lastName
        this. position = position
        this.address = address
    }

    constructor(){

    }
}