package efgh.rakuten.catalog.services;

import java.math.BigInteger;
import java.util.List;

import efgh.rakuten.catalog.entities.Product;

public interface ProductService {

	List<Product> getProducts(BigInteger categoryId, String productName);

	Product getProductyById(BigInteger categoryId, BigInteger productId);

	void deleteProduct(BigInteger categoryId, BigInteger productId);

	Product updateProduct(BigInteger categoryId, BigInteger productId, Product product);

	Product addProduct(BigInteger categoryId, Product product);

}
