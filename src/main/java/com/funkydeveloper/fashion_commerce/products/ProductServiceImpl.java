package com.funkydeveloper.fashion_commerce.products;

import com.funkydeveloper.fashion_commerce.exception.Error;
import com.funkydeveloper.fashion_commerce.exception.FashionCommerceException;
import com.funkydeveloper.fashion_commerce.exception.Message;
import com.funkydeveloper.fashion_commerce.generics.Response;
import com.funkydeveloper.fashion_commerce.products.requests.CreateNewProductRequest;
import com.funkydeveloper.fashion_commerce.products.responses.CreatedProduct;
import com.funkydeveloper.fashion_commerce.products.responses.GetNewCollection;
import com.funkydeveloper.fashion_commerce.products.responses.ThisWeekProducts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ResponseEntity<Response<CreatedProduct>> createNewProduct(CreateNewProductRequest request) {

        // sanitize the incoming request
        sanitizeRequest(request);

        // create the new product
        Product product = createProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Response.<CreatedProduct>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("product created successfully")
                        .data(new CreatedProduct(product))
                        .build()
        );
    }

    /**
     * This method validates the product creation request by checking if any required fields are empty or blank.
     * If any of the required fields such as name or price are empty, it throws a {@link FashionCommerceException}.
     *
     * @param request the {@link CreateNewProductRequest} object containing product details to be validated
     * @throws FashionCommerceException if any required fields (name or price) are empty or blank
     */
    private void sanitizeRequest(CreateNewProductRequest request) {
        List<String> emptyFields = new ArrayList<>();

        if (request.name().isEmpty() || request.name().isBlank())
            emptyFields.add("name");

        if (request.price().isEmpty() || request.price().isBlank())
            emptyFields.add("price");

        if (!emptyFields.isEmpty())
            throw new FashionCommerceException(
                    Error.NO_EMPTY_FIELDS_ALLOWED,
                    new Throwable(Message.THE_FOLLOWING_FIELDS_ARE_EMPTY.label + emptyFields)
            );
    }

    /**
     * This method creates a new {@link Product} based on the provided product details and saves it to the repository.
     * It constructs a new product entity, assigns values such as name, price, sizes, colors, images, genders, availability,
     * description, and timestamps, and attempts to save the product. If an error occurs while saving, a
     * {@link FashionCommerceException} is thrown.
     *
     * @param request the {@link CreateNewProductRequest} object containing product details
     * @return the saved {@link Product} entity
     * @throws FashionCommerceException if an error occurs while saving the product
     */
    private Product createProduct(CreateNewProductRequest request) {
        Product product = Product.builder()
                .name(request.name())
                .price(request.price())
                .categories(request.categories())
                .sizes(request.sizes())
                .colors(request.colors())
                .images(request.images())
                .genders(request.genders())
                .isAvailable(true)
                .description(request.description())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        //save the product
        try {
            return productRepository.save(product);
        } catch (Exception exception) {
            log.error("an error occur while saving the data {}", exception.getMessage());

            throw new FashionCommerceException(Error.ERROR_SAVING_DATA);
        }
    }



    @Override
    public ResponseEntity<Response<List<GetNewCollection>>> getNewCollections() {

        LocalDateTime lastSevenDays = LocalDateTime.now().minusDays(3);

        List<Product> products = productRepository.findAllByCreatedAtAfter(lastSevenDays);
        List<GetNewCollection> newCollections = new ArrayList<>();

        for (Product product : products) {
            newCollections.add(
                    new GetNewCollection(
                            product.getId(),
                            product.getImages()
                    )
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                Response.<List<GetNewCollection>>builder()
                        .status(HttpStatus.OK.value())
                        .message("new collections")
                        .data(newCollections)
                        .build()
        );
    }



    @Override
    public ResponseEntity<Response<Product>> getProduct(String id) {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new FashionCommerceException(
                        Error.NO_PRODUCT_FOUND,
                        new Throwable(Message.THE_REQUESTED_PRODUCT_ID_IS_INCORRECT.label)
                )
        );

        return ResponseEntity.status(HttpStatus.OK).body(
                Response.<Product>builder()
                        .status(HttpStatus.OK.value())
                        .message("product details")
                        .data(product)
                        .build()
        );
    }



    @Override
    public ResponseEntity<Response<List<Product>>> filterProductsByGender(String gender) {

        // confirm the gender is valid
        if (!isValidGender(gender))
            throw new FashionCommerceException(
                    Error.INVALID_GENDER,
                    new Throwable(Message.THE_REQUESTED_GENDER_IS_INVALID.label)
            );

        List<Product> products = productRepository.findAllByGendersContains(gender);

        return ResponseEntity.status(HttpStatus.OK).body(
                Response.<List<Product>>builder()
                        .status(HttpStatus.OK.value())
                        .message(gender + " products")
                        .data(products)
                        .build()
        );
    }

    private boolean isValidGender(String gender) {
        List<String> genders = List.of("male", "female", "children");
        return genders.contains(gender);
    }



    @Override
    public ResponseEntity<Response<List<Product>>> searchProduct(String product) {

        List<Product> products = productRepository.findAllByNameContainingIgnoreCase(product.toLowerCase());

        return ResponseEntity.status(HttpStatus.OK).body(
                Response.<List<Product>>builder()
                        .status(HttpStatus.OK.value())
                        .message("products with name " + product)
                        .data(products)
                        .build()
        );
    }



    @Override
    public ResponseEntity<Response<List<ThisWeekProducts>>> getThisWeekProducts() {

        LocalDateTime lastSevenDays = LocalDateTime.now().minusDays(7);


        List<Product> products = productRepository.findAllByCreatedAtAfter(lastSevenDays);
        List<ThisWeekProducts> newThisWeek = new ArrayList<>();

        for (Product product : products) {
            newThisWeek.add(
                    new ThisWeekProducts(
                            product.getId(),
                            product.getImages(),
                            product.getCategories(),
                            product.getName(),
                            product.getPrice()
                    )
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                Response.<List<ThisWeekProducts>>builder()
                        .status(HttpStatus.OK.value())
                        .message("new products this week")
                        .data(newThisWeek)
                        .total(newThisWeek.size())
                        .build()
        );
    }
}
