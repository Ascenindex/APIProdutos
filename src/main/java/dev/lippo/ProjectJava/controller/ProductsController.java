package dev.lippo.ProjectJava.controller;

import dev.lippo.ProjectJava.model.ProductsModel;
import dev.lippo.ProjectJava.serivce.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    // Método para mostrar todos os produtos
    @GetMapping("/showAllProducts")
    public List<ProductsModel> showProducts() {
        return productsService.getAllProducts();
    }

    // Método para criar um novo produto
    @PostMapping("/createProduct")
    public String postRoute(@RequestBody ProductsModel product) {
        return productsService.addProduct(product);
    }

    // Método para deletar um produto
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        return productsService.deleteProduct(id);
    }

    // Método para editar um produto
    @PutMapping("/edit/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductsModel updatedProduct) {
        return productsService.updateProduct(id, updatedProduct);
    }
}
