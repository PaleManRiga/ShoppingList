package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleUI {

    private ProductService productService = new ProductService();

    public void execute() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Delete product by id");
                System.out.println("4. Exit");
                Integer userInput = Integer.valueOf(scanner.nextLine());
                switch (userInput) {
                    case 1:
                        System.out.println("Enter product name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter product price: ");
                        BigDecimal price = new BigDecimal(scanner.nextLine());
                        System.out.println("Enter product discount: ");
                        BigDecimal discount = new BigDecimal(scanner.nextLine());
                        System.out.println("Enter product category");
                        String category = scanner.nextLine();
                        System.out.println("Enter product description: ");
                        String description = scanner.nextLine();

                        Product product = new Product();
                        product.setName(name);
                        product.setPrice(price);
                        product.setDiscount(discount);
                        product.setCategory(category);
                        product.setDescription(description);
                        Long productId = productService.createProduct(product);

                        System.out.println("Result: " + productId);
                        break;
                    case 2:
                        System.out.println("Enter product id: ");
                        long id = scanner.nextLong();
                        Product findProductResult = productService.findProduct(id);
                        System.out.println(findProductResult);
                        break;
                    case 3:
                        System.out.println("Enter product id: ");
                        long deleteId = scanner.nextLong();
                        productService.deletProduct(deleteId);
                        break;
                    case 4:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error! " + e.getMessage() + " Please try again.");
            }
        }
    }
}