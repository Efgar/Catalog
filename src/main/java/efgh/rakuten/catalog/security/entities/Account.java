package efgh.rakuten.catalog.security.entities;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	BigInteger id;
	@NonNull
	String username;
	@NonNull
	String password;
}
