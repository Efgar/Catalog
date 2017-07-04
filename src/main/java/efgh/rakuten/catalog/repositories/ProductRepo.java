package efgh.rakuten.catalog.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import efgh.rakuten.catalog.entities.Product;

public interface ProductRepo extends CrudRepository<Product, BigInteger> {
	
	
	List<Product> findByProductNameAndCategories_CategoryId(String productName, BigInteger categoryId);

	List<Product> findByCategories_CategoryId(BigInteger categoryId);

}
