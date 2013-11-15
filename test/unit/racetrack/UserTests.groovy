package racetrack



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
@Mock(User)
class UserTests {

    void testSimpleConstraints() {
    	mockForConstraintsTests(User)
 		def user = new User(login:"someone", password:"blah", role:"SuperUser")
		// oops—role should be either 'admin' or 'user'
		// will the validation pick that up?
		assertFalse user.validate()
		assertEquals "inList", user.errors["role"]
	}

	void testUniqueConstraint(){
		def sudahal = new User(login:"sudahal", password:"foobar", role:"admin")
		def root = new User(login:"root", password:"foobar", role:"user")
		mockDomain(User, [sudahal, root])

		// def badUser = new User(login:"sudahal", password:"foobar", role:"user")
		// badUser.save()
		assertEquals 2, User.count()
		// assertEquals "unique", badUser.errors["login"]

		def goodUser = new User(login:"good", password:"password", role:"user")

		goodUser.save()
		assertEquals 3, User.count()
		assertNotNull User.findByLoginAndPassword("good", "password")
	}
}
