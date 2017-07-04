package efgh.rakuten.catalog.entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import efgh.rakuten.catalog.exchange.Currencies;
import efgh.rakuten.catalog.exchange.ExchangeUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	BigInteger productId;

	@NonNull
	String productName;

	@NonNull
	BigDecimal productPrice;

	@NonNull
	String currencyCode = Currencies.EURO.getCode();
	
	BigDecimal eurosPrice;

	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(name="product_category", 
				joinColumns={@JoinColumn(name="categoryId")}, 
				inverseJoinColumns={@JoinColumn(name="productId")})
	private List<Category> categories;

	public void addParentCategory(Category newParentCategory) {
		if (getParentCategories() == null) {
			categories = new ArrayList<>();
		}
		if (!getCategories().stream().map(Category::getCategoryId).collect(Collectors.toList())
				.contains(newParentCategory.getCategoryId())) {
			getCategories().add(newParentCategory);
		}

	}

	public List<BigInteger> getParentCategories() {
		if (this.getCategories() != null) {
			return this.getCategories().stream().map(Category::getCategoryId).collect(Collectors.toList());
		}
		return null;
	}

	public void setParentCategories(List<BigInteger> parentIds) {
		if (parentIds != null) {
			for (BigInteger parentId : parentIds) {
				Category newParent = new Category();
				newParent.setCategoryId(parentId);
				addParentCategory(newParent);
			}
		}
	}

	public List<String> getCategoryPaths() {
		List<String> paths = new ArrayList<>();
		for (Category category : this.getCategories()) {
			paths.add(category.getCategoryFullPath());
		}
		return paths;
	}
	
	public void doCurrencyExchange(){
		BigDecimal eurosPrice = getProductPrice();
		if(!StringUtils.isEmpty(getCurrencyCode())){
			if(!getCurrencyCode().contentEquals(Currencies.EURO.getCode())){
				eurosPrice = getProductPrice().multiply(ExchangeUtil.getExchangeRate(currencyCode));
			}
		}
		setEurosPrice(eurosPrice);
	}
	

	public void fillNewValues(Product product) {
		setProductName(product.getProductName());
		setProductPrice(product.getProductPrice());
		setCurrencyCode(product.getCurrencyCode());
		setParentCategories(product.getParentCategories());
	}
}
