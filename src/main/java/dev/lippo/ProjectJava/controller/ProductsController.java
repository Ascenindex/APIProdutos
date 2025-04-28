package dev.lippo.ProjectJava.controller;

import dev.lippo.ProjectJava.model.ProductsModel;
import dev.lippo.ProjectJava.serivce.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")

public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/showALlProducts")
    public List<ProductsModel> showProducts(){
        return productsService.getAllProducts();
    }

    @PostMapping("/createProduct")
    public String postRoute(@RequestBody ProductsModel product) {
        productsService.addProduct(product);
        return "Product added successfully!";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        boolean removed = productsService.getAllProducts().removeIf(productsModel -> productsModel.getId() == id);
        if (!removed){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with ID "+ id);
        }
        return ResponseEntity.ok("User with ID " + id+ " deleted successfully!");
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductsModel updatedProduct) {
        Optional<ProductsModel> existingProduct = productsService.getAllProducts().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();

        if (!existingProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with ID " + id);
        }

        ProductsModel product = existingProduct.get();
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());

        return ResponseEntity.ok("Product with ID " + id + " updated successfully!");
    }

}
