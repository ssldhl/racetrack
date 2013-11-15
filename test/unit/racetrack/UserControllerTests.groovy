package racetrack



import org.junit.*
import grails.test.mixin.*
import org.codehaus.groovy.grails.plugins.codecs.*
import racetrack.SHACodec

@TestFor(UserController)
@Mock(User)
class UserControllerTests {

    @Before
    void setUp() {
        String.metaClass.encodeAsBase64 = {->
            Base64Codec.encode(delegate)
        }
        String.metaClass.encodeAsSHA = {->
            SHACodec.encode(delegate)
        }
    }


    void testIndex() {
        controller.index()
        assert "/user/list" == response.redirectedUrl
    }

    void testShow() {
        def sudahal = new User(login:"sudahal", password:"foobar", role:"user")
        def anwesh = new User(login:"anwesh", password:"foobar", role:"user")
        mockDomain(User, [sudahal, anwesh])
        controller.params.id = 2
        def map = controller.show()
        assertEquals "anwesh", map.userInstance.login
    }

    void testAuthenticate(){
        def sudahal = new User(login:"sudahal", password:"foobar".encodeAsSHA())
        mockDomain(User, [sudahal])
        controller.params.login = "sudahal"
        controller.params.password = "foobar"
        controller.authenticate()
        assertNotNull controller.session.user
        assertEquals "sudahal", controller.session.user.login
        controller.params.password = "password"
        controller.authenticate()
        assertTrue controller.flash.message.startsWith("Sorry, sudahal")
    }
}
