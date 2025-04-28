package dev.lippo.ProjectJava.serivce;

import dev.lippo.ProjectJava.model.ProductsModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {
    private final List<ProductsModel> productsList = new ArrayList<>();

    public List<ProductsModel> getAllProducts() {
        return productsList;
    }

    public String addProduct(ProductsModel product){
        for (ProductsModel existingProduct : productsList) {
            if (existingProduct.getId().equals(product.getId())) {
                return "Não pode criar com o mesmo id";
            }
        }
        productsList.add(product);
        return "Produto criado com sucesso";
    }


}
