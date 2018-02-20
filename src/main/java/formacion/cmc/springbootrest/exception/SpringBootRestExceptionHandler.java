package formacion.cmc.springbootrest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import formacion.cmc.springbootrest.constants.Constants;

@ControllerAdvice
public class SpringBootRestExceptionHandler extends ResponseEntityExceptionHandler {
    
    private static final Logger log = LoggerFactory.getLogger(SpringBootRestExceptionHandler.class);
    
    private final MessageSource messages;

    public SpringBootRestExceptionHandler(MessageSource messages) {
        this.messages = messages;
    }
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        
        log.error(ex.getMessage(), ex);
        
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                messages.getMessage(Constants.MESSAGE_RESOURCE_NOT_FOUND, null,
                        LocaleContextHolder.getLocale()),
                ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
    }

}
