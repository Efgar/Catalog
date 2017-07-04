package efgh.rakuten.catalog.services;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efgh.rakuten.catalog.entities.Product;
import efgh.rakuten.catalog.repositories.CategoryRepo;
import efgh.rakuten.catalog.repositories.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepo repo;
	
	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public List<Product> getProducts(BigInteger categoryId, String productName) {
		if (productName == null) {
			return repo.findByCategories_CategoryId(categoryId);
		} else {
			return repo.findByProductNameAndCategories_CategoryId(productName, categoryId);
		}
	}

	@Override
	public Product getProductyById(BigInteger categoryId, BigInteger productId) {
		return repo.findOne(productId);
	}

	@Override
	public void deleteProduct(BigInteger categoryId, BigInteger productId) {
		repo.delete(productId);
	}

	@Override
	public Product updateProduct(BigInteger categoryId, BigInteger productId, Product product) {
		Product productDB = repo.findOne(productId);
		productDB.fillNewValues(product);
		productDB.doCurrencyExchange();
		return repo.save(product);
	}

	@Override
	public Product addProduct(BigInteger categoryId, Product product) {
		product.addParentCategory(categoryRepo.findOne(categoryId));
		product.doCurrencyExchange();
		return repo.save(product);
	}
}
