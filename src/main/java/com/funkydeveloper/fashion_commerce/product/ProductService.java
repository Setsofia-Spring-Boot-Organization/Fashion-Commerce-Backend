package com.funkydeveloper.fashion_commerce.product;

import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.product.requests.CreateNewProductRequest;
import com.funkydeveloper.fashion_commerce.product.responses.AllProductsRes;
import com.funkydeveloper.fashion_commerce.product.responses.CreatedProductRes;
import com.funkydeveloper.fashion_commerce.product.responses.GetNewCollectionRes;
import com.funkydeveloper.fashion_commerce.product.responses.ThisWeekProductsRes;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    /**
     * This method creates a new product based on the provided details in the request.
     *
     * @param request the {@link CreateNewProductRequest} object containing the details of the product to be created
     *        such as name, price, available sizes, colors, images, availability status, and description
     * @return a {@link ResponseEntity} containing the response with the status of the product creation
     */
    ResponseEntity<Response<CreatedProductRes>> createNewProduct(CreateNewProductRequest request);

    /**
     * This method retrieves the new product collection.
     *
     * @return a {@link ResponseEntity} containing a {@link Response} with the details of the new product collection
     */
    ResponseEntity<Response<List<GetNewCollectionRes>>> getNewCollections();

    ResponseEntity<Response<Product>> getProduct(String id);

    ResponseEntity<Response<List<Product>>> filterProductsByGender(String gender);

    ResponseEntity<Response<List<Product>>> searchProduct(String product);

    ResponseEntity<Response<List<ThisWeekProductsRes>>> getThisWeekProducts();

    ResponseEntity<Response<List<Product>>> filterProductsFromLastYear(boolean all, String gender);

    ResponseEntity<Response<AllProductsRes>> getProducts();
}
