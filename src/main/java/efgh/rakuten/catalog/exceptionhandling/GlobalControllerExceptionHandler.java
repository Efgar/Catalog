package efgh.rakuten.catalog.exceptionhandling;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class GlobalControllerExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

	@ResponseStatus(HttpStatus.CONFLICT) // 409
	@ExceptionHandler(DataIntegrityViolationException.class)
	public @ResponseBody SimpleExceptionObject handleConflict(HttpServletRequest req,
			DataIntegrityViolationException e) {
		log.error("Conflict error.", e);
		return new SimpleExceptionObject(e);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(IllegalArgumentException.class)
	public @ResponseBody SimpleExceptionObject handleIllegalArgument(HttpServletRequest req,
			IllegalArgumentException e) {
		log.error("Illegal argument error.", e);
		return new SimpleExceptionObject(e);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
	@ExceptionHandler(Exception.class)
	public @ResponseBody SimpleExceptionObject handleUnexpected(HttpServletRequest req, Exception e) {
		log.error("Unexpected error.", e);
		return new SimpleExceptionObject(e);
	}
}