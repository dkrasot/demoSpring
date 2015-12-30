package demo.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//NOT_FOUND = HTML 404 (was RuntimeEx = HTML 500)
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Tweet Not Found")
public class TweetNotFoundException extends RuntimeException {
}
