package efgh.rakuten.catalog.exchange;

import java.math.BigDecimal;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class ExchangeUtil {

	final static String URI = "http://api.fixer.io/latest?base={CURRENCY_CODE}&symbols={EURO_CODE}";

	public static BigDecimal getExchangeRate(String currencyCode) {
		Object[] uriVariables = {currencyCode, Currencies.EURO.getCode()};
		RestTemplate restTemplate = new RestTemplate();
		BigDecimal exchangeRate;
		ExchangeRates result = null;
		try {
			result = restTemplate.getForObject(URI, ExchangeRates.class, uriVariables);
			exchangeRate = result.getRates().get(Currencies.EURO.getCode());
		} catch (RestClientException e) {
			throw new IllegalArgumentException("Invalid currency");
		}
		if(exchangeRate == null){
			throw new IllegalArgumentException("Invalid currency");
		}
		return exchangeRate;
	}
}
