import grails.util.GrailsUtil

class BootStrap {

    def init = { servletContext ->
    	switch(GrailsUtil.environment){
    		case "development":

    			def sushil = new racetrack.Runner(firstName:"Sushil", lastName:"Dahal", dateOfBirth:new Date() - 365*22,
    			gender:"M", address:"Kalimati, Kathmandu", city: "Kathmandu", 
    			state:"KTM", zipcode:"00977", email:"sudahal@deerwalk.com")
    			sushil.save()
    			if(sushil.hasErrors()){
    				println sushil.errors
    			}

    			def valley_race = new racetrack.Race(name:"Valley Race", startDate:(new Date() + 90), city:"Kathmandu", 
    			state:"KTM", distance:5.0, cost:20.0, maxRunners:350)
				valley_race.save()
				if(valley_race.hasErrors()){
					println valley_race.errors
				}

				def reg = new racetrack.Registration(paid:false, runner:sushil, race:valley_race)
				reg.save()
				if(reg.hasErrors()){
					println reg.errors
				}
			break

			case "production": break
    	}
    }

    def destroy = {
    }
}
