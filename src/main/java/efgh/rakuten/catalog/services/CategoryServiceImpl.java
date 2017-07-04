package efgh.rakuten.catalog.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efgh.rakuten.catalog.entities.Category;
import efgh.rakuten.catalog.repositories.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo repository;

	@Override
	public List<Category> getCategories(String categoryName) {
		if (categoryName == null) {
			List<Category> list = new ArrayList<>();
			repository.findAll().forEach(list::add);
			return list;
		} else {
			return repository.findByCategoryName(categoryName);
		}
	}

	@Override
	public Category getCategoryById(BigInteger categoryId) {
		return repository.findOne(categoryId);
	}

	@Override
	public Category addCategory(Category category) {
		return repository.save(category);
	}

	@Override
	public Category updateCategory(BigInteger categoryId, Category category) {
		Category categoryDB = repository.findOne(categoryId);
		categoryDB.fillNewValues(category);

		return repository.save(categoryDB);
	}

	@Override
	public void deleteCategory(BigInteger categoryId) {
		repository.delete(categoryId);
	}

}
