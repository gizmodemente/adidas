package trial.adidas.connections.advice;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseBody {
    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
    
    public ErrorResponseBody(Date timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
