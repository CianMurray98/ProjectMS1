package ie.atu.productcatalogservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductService {
    private List<Product> productList = new ArrayList<>();
    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productList.add(product);
        return ResponseEntity.ok("Product added successfully");
    }
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        // Find the product by ID
        for (Product product : productList) {
            if (product.getId().equals(productId)) {
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProductDetails(
            @PathVariable Long productId,
            @RequestBody Product updatedProduct) {
        // Find the product by ID and update details
        for (Product product : productList) {
            if (product.getId().equals(productId)) {
                product.setName(updatedProduct.getName());
                product.setPrice(updatedProduct.getPrice());
                product.setQuantity(updatedProduct.getQuantity());
                return ResponseEntity.ok("Product details updated successfully");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long productId) {
        // Delete the product by ID
        productList.removeIf(product -> product.getId().equals(productId));
        return ResponseEntity.ok("Product deleted successfully");
    }
}