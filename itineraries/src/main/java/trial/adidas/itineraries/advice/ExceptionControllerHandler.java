package trial.adidas.itineraries.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class ExceptionControllerHandler {

    @ExceptionHandler(InstanceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponseBody instanceAlreadyExistsHandler(HttpServletRequest request,
                                                          InstanceAlreadyExistsException e) {

        return new ErrorResponseBody(new Date(), HttpStatus.CONFLICT.value(),
                "Resource Already Exists",
                e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(InstanceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponseBody instanceNotFoundHandler(HttpServletRequest request,
                                                     InstanceNotFoundException e) {

        return new ErrorResponseBody(new Date(), HttpStatus.NOT_FOUND.value(),
                "Resource Does Not Exists",
                e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(IllegalAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseBody illegalAccessException(HttpServletRequest request,
                                                    IllegalAccessException e) {

        return new ErrorResponseBody(new Date(), HttpStatus.BAD_REQUEST.value(),
                "Illegal Access to Resource",
                e.getMessage(), request.getRequestURI());
    }
}
