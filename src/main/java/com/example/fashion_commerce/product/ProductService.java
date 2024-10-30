package com.example.fashion_commerce.product;

import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.product.requests.CreateNewProductRequest;
import com.example.fashion_commerce.product.requests.FilterProducts;
import com.example.fashion_commerce.product.requests.UpdateProduct;
import com.example.fashion_commerce.product.responses.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    /**
     * This method creates a new product based on the provided details in the request.
     *
     * @param request the {@link CreateNewProductRequest} object containing the details of the product to be created
     *        such as name, price, available sizes, colors, images, availability status, and description
     * @return a {@link ResponseEntity} containing the response with the status of the product creation
     */
    ResponseEntity<Response<CreatedProductRes>> createNewProduct(CreateNewProductRequest request) throws IOException;

    /**
     * This method retrieves the new product collection.
     *
     * @return a {@link ResponseEntity} containing a {@link Response} with the details of the new product collection
     */
    ResponseEntity<Response<List<Product>>> getNewCollections();

    ResponseEntity<Response<Product>> getProduct(String id);

    ResponseEntity<Response<List<Product>>> filterProductsByGender(String gender);

    ResponseEntity<Response<List<Product>>> searchProduct(String product);

    ResponseEntity<Response<List<ThisWeekProductsRes>>> getThisWeekProducts();

    ResponseEntity<Response<List<Product>>> filterProductsFromLastYear(boolean all, String gender);

    ResponseEntity<Response<AllProductsRes>> getProducts();

    ResponseEntity<Response<List<Product>>> filterAllProducts(FilterProducts filter);

    ResponseEntity<Response<FilterOptions>> getFilterOptions();

    ResponseEntity<Response<Product>> updateProduct(String id, List<MultipartFile> images, UpdateProduct request) throws IOException;

    ResponseEntity<Response<?>> deleteProduct(String id);
}
