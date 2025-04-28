package dev.lippo.ProjectJava.serivce;

import dev.lippo.ProjectJava.model.ProductsModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {
    private final List<ProductsModel> productsList = new ArrayList<>();

    public List<ProductsModel> getAllProducts() {
        return productsList;
    }

    public String addProduct(ProductsModel product){
        // Verifica se já existe um produto com o mesmo "id"
        for (ProductsModel existingProduct : productsList) {
            if (existingProduct.getId().equals(product.getId())) {
                return "Não pode criar com o mesmo id";
            }
        }
        productsList.add(product);
        return "Produto criado com sucesso";
    }

    // Método delete
    public ResponseEntity<String> deleteProduct(int id) {
        boolean removed = productsList.removeIf(productsModel -> productsModel.getId() == id);
        if (!removed){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produto não encontrado com ID " + id);
        }
        return ResponseEntity.ok("Produto com ID " + id + " deletado com sucesso!");
    }

    // Método de atualização
    public ResponseEntity<String> updateProduct(Long id, ProductsModel updatedProduct) {
        for (ProductsModel existingProduct : productsList) {
            if (existingProduct.getId().equals(id)) {
                existingProduct.setName(updatedProduct.getName());
                existingProduct.setPrice(updatedProduct.getPrice());
                return ResponseEntity.ok("Produto com ID " + id + " atualizado com sucesso!");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Produto não encontrado com ID " + id);
    }
}
