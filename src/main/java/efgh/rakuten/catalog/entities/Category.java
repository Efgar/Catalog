package efgh.rakuten.catalog.entities;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "parentCategory", "categoryName" }) })
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	BigInteger categoryId;

	@NonNull
	String categoryName;

	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "parentCategory")
	private Category parentCategory;
	
	
	@JsonIgnore
	@ManyToMany(mappedBy="categories")
	private List<Product> products;

	public Category(String categoryName, Category newParentCategory) {
		setCategoryName(categoryName);
		addParentCategory(newParentCategory);
	}

	public void addParentCategory(Category newParentCategory) {
		parentCategory = newParentCategory;
	}

	public BigInteger getParentCategory() {
		if (parentCategory != null) {
			return parentCategory.getCategoryId();
		}
		return null;
	}

	public void setParentCategory(BigInteger parentId) {
		if (parentId != null) {
			this.parentCategory = new Category();
			this.parentCategory.setCategoryId(parentId);
		}
	}

	@JsonIgnore
	public String getCategoryFullPath() {
		if (parentCategory == null) {
			return categoryName;
		}
		return parentCategory.getCategoryFullPath() + "/" + categoryName;
	}

	public void fillNewValues(Category newData) {
		setCategoryName(newData.getCategoryName());
		setParentCategory(newData.getParentCategory());
	}
}
