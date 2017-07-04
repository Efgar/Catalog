package efgh.rakuten.catalog.exchange;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Currencies {
	EURO("EUR");
	String code;
	
	public String getCode(){
		return code;
	}
}
