package efgh.rakuten.catalog.controllers;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import efgh.rakuten.catalog.entities.Category;
import efgh.rakuten.catalog.services.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	CategoryService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Category> getCategories(@RequestParam(required = false) String categoryName) {
		return service.getCategories(categoryName);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{categoryId}")
	public @ResponseBody Category getCategoryById(@PathVariable BigInteger categoryId) {
		return service.getCategoryById(categoryId);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{categoryId}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteCategory(@PathVariable BigInteger categoryId) {
		service.deleteCategory(categoryId);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{categoryId}")
	public @ResponseBody Category updateCategory(@PathVariable BigInteger categoryId, @RequestBody Category category) {
		return service.updateCategory(categoryId, category);
	}

	@RequestMapping(method = RequestMethod.POST, value = "")
	public @ResponseBody Category saveCategory(@RequestBody Category category) {
		return service.addCategory(category);
	}
}
