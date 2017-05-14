import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Bogdan on 13-May-17.
 */
@Controller
@EnableAutoConfiguration
@Api(value="Test", description="Hai sa testam")
public class TestController {
    @RequestMapping(value = "/sal", method = RequestMethod.GET)
    public void welcomePage() {
        System.out.println("Salut!");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestController.class, args);
    }
}
