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

import efgh.rakuten.catalog.entities.Product;
import efgh.rakuten.catalog.services.ProductService;

@RestController
@RequestMapping("category/{categoryId}/product")
public class ProductController {

	@Autowired
	ProductService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Product> getProducts(@PathVariable BigInteger categoryId, @RequestParam(required = false) String productName) {
		return service.getProducts(categoryId, productName);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productId}")
	public @ResponseBody Product getProductyById(@PathVariable BigInteger categoryId, @PathVariable BigInteger productId) {
		return service.getProductyById(categoryId, productId);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{productId}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable BigInteger categoryId, @PathVariable BigInteger productId) {
		service.deleteProduct(categoryId, productId);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{productId}")
	public @ResponseBody Product updateProduct(@PathVariable BigInteger categoryId, @PathVariable BigInteger productId, @RequestBody Product product) {
		return service.updateProduct(categoryId, productId, product);
	}

	@RequestMapping(method = RequestMethod.POST, value = "")
	public @ResponseBody Product saveProduct(@PathVariable BigInteger categoryId, @RequestBody Product product) {
		return service.addProduct(categoryId, product);
	}

	
	
}
