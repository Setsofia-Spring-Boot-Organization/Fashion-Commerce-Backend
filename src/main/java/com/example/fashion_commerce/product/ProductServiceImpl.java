package com.example.fashion_commerce.product;

import com.example.fashion_commerce.cloudinary.CloudinaryConfig;
import com.example.fashion_commerce.exception.FashionCommerceException;
import com.example.fashion_commerce.exception.Message;
import com.example.fashion_commerce.exception.Error;
import com.example.fashion_commerce.generics.Response;
import com.example.fashion_commerce.product.requests.CreateNewProductRequest;
import com.example.fashion_commerce.product.requests.FilterProducts;
import com.example.fashion_commerce.product.responses.AllProductsRes;
import com.example.fashion_commerce.product.responses.CreatedProductRes;
import com.example.fashion_commerce.product.responses.GetNewCollectionRes;
import com.example.fashion_commerce.product.responses.ThisWeekProductsRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CloudinaryConfig cloudinaryConfig;
    private final ProductPredicates productPredicates;

    @Override
    public ResponseEntity<Response<CreatedProductRes>> createNewProduct(CreateNewProductRequest request) throws IOException {

        // sanitize the incoming request
        sanitizeRequest(request);

        // create the new product
        Product product = createProduct(request);

        Response<CreatedProductRes> response = new Response<>(
                HttpStatus.OK.value(),
                "product created successfully",
                new CreatedProductRes(product)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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

        if (request.getName().isEmpty() || request.getName().isBlank())
            emptyFields.add("name");

        if (request.getPrice().isEmpty() || request.getPrice().isBlank())
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
    private Product createProduct(CreateNewProductRequest request) throws IOException {

        List<String> images = cloudinaryConfig.uploadImageToCloudinary(request.getImages());

        Product product = new Product(
                request.getName(),
                request.getPrice(),
                request.getType(),
                request.getSizes(),
                request.getColors(),
                images,
                request.getCategories(),
                true,
                request.getDescription(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        //save the product
        try {
            return productRepository.save(product);
        } catch (Exception exception) {
            log.error("an error occur while saving the data {}", exception.getMessage());

            throw new FashionCommerceException(Error.ERROR_SAVING_DATA);
        }
    }



    @Override
    public ResponseEntity<Response<List<GetNewCollectionRes>>> getNewCollections() {

        LocalDateTime lastSevenDays = LocalDateTime.now().minusDays(3);

        List<Product> products = productRepository.findAllByCreatedAtAfter(lastSevenDays);
        List<GetNewCollectionRes> newCollections = new ArrayList<>();

        for (Product product : products) {
            newCollections.add(
                    new GetNewCollectionRes(
                            product.getId(),
                            product.getImages()
                    )
            );
        }

        Response<List<GetNewCollectionRes>> response = new Response<>(
                HttpStatus.OK.value(),
                "new collections",
                newCollections
        );

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



    @Override
    public ResponseEntity<Response<Product>> getProduct(String id) {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new FashionCommerceException(
                        Error.NO_PRODUCT_FOUND,
                        new Throwable(Message.THE_REQUESTED_PRODUCT_ID_IS_INCORRECT.label)
                )
        );

        Response<Product> productResponse = new Response<>(
                HttpStatus.OK.value(),
                "product details",
                product
        );

        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }



    @Override
    public ResponseEntity<Response<List<Product>>> filterProductsByGender(String gender) {

        // confirm the gender is valid
        if (!isValidGender(gender))
            throw new FashionCommerceException(
                    Error.INVALID_GENDER,
                    new Throwable(Message.THE_REQUESTED_GENDER_IS_INVALID.label)
            );

        List<Product> products = productRepository.findAllByCategoriesContains(gender);

        Response<List<Product>> productsResponse = new Response<>(
                HttpStatus.OK.value(),
                gender + " products",
                products
        );

        return ResponseEntity.status(HttpStatus.OK).body(productsResponse);
    }

    private boolean isValidGender(String gender) {
        List<String> genders = List.of("male", "female", "children");
        return genders.contains(gender);
    }



    @Override
    public ResponseEntity<Response<List<Product>>> searchProduct(String product) {

        List<Product> products = productRepository.findAllByNameContainingIgnoreCase(product.toLowerCase());

        Response<List<Product>> productsResponse = new Response<>(
                HttpStatus.OK.value(),
                "products with name " + product,
                products
        );

        return ResponseEntity.status(HttpStatus.OK).body(productsResponse);
    }



    @Override
    public ResponseEntity<Response<List<ThisWeekProductsRes>>> getThisWeekProducts() {

        LocalDateTime lastSevenDays = LocalDateTime.now().minusDays(7);


        List<Product> products = productRepository.findAllByCreatedAtAfter(lastSevenDays);
        List<ThisWeekProductsRes> newThisWeek = new ArrayList<>();

        for (Product product : products) {
            newThisWeek.add(
                    new ThisWeekProductsRes(
                            product.getId(),
                            product.getImages(),
                            product.getType(),
                            product.getName(),
                            product.getPrice()
                    )
            );
        }

        Response<List<ThisWeekProductsRes>> productsResponse = new Response<>(
                HttpStatus.OK.value(),
                "new products this week",
                newThisWeek,
                String.valueOf(newThisWeek.size())
        );

        return ResponseEntity.status(HttpStatus.OK).body(productsResponse);
    }



    @Override
    public ResponseEntity<Response<List<Product>>> filterProductsFromLastYear(boolean all, String gender) {

        LocalDateTime lastYear = LocalDateTime.now().minusYears(1).withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(1);
        List<Product> products;

        if (all) {
            products = productRepository.findAllByCreatedAtAfter(lastYear);
        } else {

            // confirm the gender is valid
            if (!isValidGender(gender))
                throw new FashionCommerceException(
                        Error.INVALID_GENDER,
                        new Throwable(Message.THE_REQUESTED_GENDER_IS_INVALID.label)
                );

            products = productRepository.findAllByCreatedAtAfterAndCategoriesContains(lastYear, gender.toLowerCase());
        }

        Response<List<Product>> productsResponse = new Response<>(
                HttpStatus.OK.value(),
                "products from " + lastYear.getYear(),
                products,
                String.valueOf(products.size())
        );

        return ResponseEntity.status(HttpStatus.OK).body(productsResponse);
    }



    @Override
    public ResponseEntity<Response<AllProductsRes>> getProducts() {

        List<Product> products = productRepository.findProductsByOrderByCreatedAtDesc();

        List<Product> availableProducts = new ArrayList<>();
        List<Product> unAvailableProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.isAvailable()) {
                availableProducts.add(product);
            } else {
                unAvailableProducts.add(product);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                Response.<AllProductsRes>builder()
                        .status(HttpStatus.OK.value())
                        .message("products")
                        .data(new AllProductsRes(
                                products,
                                availableProducts.size(),
                                unAvailableProducts.size()
                        ))
                        .total(String.valueOf(products.size()))
                        .build()
        );
    }



    @Override
    public ResponseEntity<Response<List<Product>>> filterAllProducts(FilterProducts filter) {

        List<Product> filteredProducts = productPredicates.globalProductFilter(filter);

        return ResponseEntity.status(HttpStatus.OK).body(
                Response.<List<Product>>builder()
                        .status(HttpStatus.OK.value())
                        .message("filtered products")
                        .data(filteredProducts)
                        .build()
        );
    }
}
