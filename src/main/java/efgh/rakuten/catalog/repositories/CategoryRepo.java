package efgh.rakuten.catalog.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import efgh.rakuten.catalog.entities.Category;

public interface CategoryRepo extends CrudRepository<Category, BigInteger> {
    List<Category> findByCategoryName(String categoryName);
}