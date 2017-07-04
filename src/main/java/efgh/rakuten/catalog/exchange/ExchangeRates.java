package efgh.rakuten.catalog.exchange;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Data;

@Data
class ExchangeRates {
	String base;
	Map<String, BigDecimal> rates;
}
