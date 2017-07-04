package efgh.rakuten.catalog.security.repo;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import efgh.rakuten.catalog.security.entities.Account;

public interface AccountRepo extends CrudRepository<Account, BigInteger> {

	Account findByUsername(String username);
}
