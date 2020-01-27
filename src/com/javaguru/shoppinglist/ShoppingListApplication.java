package com.javaguru.shoppinglist;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ShoppingListApplication {

    public static void main(String[] args) {
        Map<Long, Product> productRepository = new HashMap<>();
        Long productIdSequence = 0L;
        ProductValidationService service = new ProductValidationService();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Delete product by id");
                System.out.println("4. Edit product");
                System.out.println("5. Exit");
                Integer userInput = Integer.valueOf(scanner.nextLine());
                switch (userInput) {
                    case 1:
                        Product product = new Product();
                        System.out.println("Enter product name: ");
                        product.setName(scanner.nextLine());
                        System.out.println("Enter product price: ");
                        BigDecimal price = new BigDecimal(scanner.nextLine());
                        product.setPrice(price);
                        System.out.println("Enter product discount: ");
                        BigDecimal discount = new BigDecimal(scanner.nextLine());
                        product.setDiscount(discount);
                        System.out.println("Enter  product category: ");
                        product.setCategory(scanner.nextLine());
                        System.out.println("Enter product description");
                        product.setDescription(scanner.nextLine());
                        service.validate(product);
                        product.setId(productIdSequence);
                        productRepository.put(productIdSequence, product);
                        productIdSequence++;
                        System.out.println("Product ID: " + product.getId());
                        break;
                    case 2:
                        System.out.println("Enter product id: ");
                        long id = scanner.nextLong();
                        Product findProductResult = productRepository.get(id);
                        System.out.println(findProductResult);
                        break;
                    case 3:
                        System.out.println("Enter product id: ");
                        id = scanner.nextLong();
                        productRepository.remove(id);
                        break;
                    case 4:
                        Product newProduct = new Product();
                        System.out.println("Enter product id");
                        id = scanner.nextLong();
                        scanner.nextLine();
                        findProductResult = productRepository.get(id);
                        System.out.println(findProductResult);
                        System.out.println("Enter product name: ");
                        newProduct.setName(scanner.nextLine());
                        System.out.println("Enter product price: ");
                        price = new BigDecimal(scanner.nextLine());
                        newProduct.setPrice(price);
                        System.out.println("Enter product discount: ");
                        discount = new BigDecimal(scanner.nextLine());
                        newProduct.setDiscount(discount);
                        System.out.println("Enter  product category: ");
                        newProduct.setCategory(scanner.nextLine());
                        System.out.println("Enter product description");
                        newProduct.setDescription(scanner.nextLine());
                        service.validate(newProduct);
                        newProduct.setId(id);
                        productRepository.replace(id,newProduct);
                        break;
                        case 5:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
            }
        }
    }
}
