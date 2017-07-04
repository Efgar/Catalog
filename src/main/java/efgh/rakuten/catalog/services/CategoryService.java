package efgh.rakuten.catalog.services;

import java.math.BigInteger;
import java.util.List;

import efgh.rakuten.catalog.entities.Category;

public interface CategoryService {
	
	public List<Category> getCategories(String name);
	
	public Category getCategoryById(BigInteger categoryId);
	
	public Category addCategory(Category category);
	
	public Category updateCategory(BigInteger categoryId, Category category);
	
	public void deleteCategory(BigInteger categoryId);

}
