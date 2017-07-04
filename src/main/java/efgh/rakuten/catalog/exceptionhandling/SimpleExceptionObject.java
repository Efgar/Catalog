package efgh.rakuten.catalog.exceptionhandling;

import org.springframework.dao.DataIntegrityViolationException;

import lombok.Data;

@Data
public class SimpleExceptionObject {

	String name;
	String detail;
	
	public SimpleExceptionObject(DataIntegrityViolationException e) {
		setName("Data integrity violation");
		setDetail(e.getMessage());
	}
	
	public SimpleExceptionObject(Exception e) {
		setName("Unexpected Error");
		setDetail(e.getMessage());
	}
	
}
