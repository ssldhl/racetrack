package racetrack

class Registration {
	String name
	Date dateOfBirth
	String gender
	String address
	String city
	String state
	String zipcode
	String email
	Date dateCreated //Special name
	
    static constraints = {
    }

    static belongsTo = [race:Race]
}
