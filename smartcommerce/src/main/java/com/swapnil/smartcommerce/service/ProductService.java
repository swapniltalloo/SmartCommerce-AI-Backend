package com.swapnil.smartcommerce.service;
import org.springframework.cache.annotation.Cacheable;
import com.swapnil.smartcommerce.entity.Product;
import com.swapnil.smartcommerce.exception.ResourceNotFoundException;
import com.swapnil.smartcommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import java.util.List;
import com.swapnil.smartcommerce.dto.ProductDTO;
import com.swapnil.smartcommerce.entity.Category;
import com.swapnil.smartcommerce.repository.CategoryRepository;
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @CacheEvict(value = "products", allEntries = true)
    public Product addProduct(ProductDTO productDTO) {

        Category category = categoryRepository.findById(
                productDTO.getCategoryId()
        ).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Category not found with id: "
                                + productDTO.getCategoryId()
                )
        );

        Product product = new Product();

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());

        product.setCategory(category);

        return productRepository.save(product);
    }
    @Cacheable("products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));
    }
    @CacheEvict(value = "products", allEntries = true)
    public Product updateProduct(Long id, Product updatedProduct) {

        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct != null) {

            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setQuantity(updatedProduct.getQuantity());

            return productRepository.save(existingProduct);
        }

        return null;
    }@CacheEvict(value = "products", allEntries = true)
    public String deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));

        productRepository.delete(product);

        return "Product deleted successfully";
    }
}