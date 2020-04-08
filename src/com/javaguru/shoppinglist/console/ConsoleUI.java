package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleUI {
    private ProductService productService = new ProductService();

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("1. Create task");
                System.out.println("2. Find task by id");
                System.out.println("3. Exit");
                int userInput = scanner.nextInt();
                switch (userInput) {
                    case 1:
                        createProduct();
                        break;
                    case 2:
                        findProduct();
                        break;
                    case 3:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
                System.out.println(e.getMessage());
            }
        }
    }

    void createProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();

        System.out.println("Enter product description: ");
        String description = scanner.nextLine();

        System.out.print("Enter product price: ");
        BigDecimal price = new BigDecimal(scanner.nextLine());

        System.out.print("Enter product discount: ");
        BigDecimal discount = new BigDecimal(scanner.nextLine());

        System.out.print("Enter product Category: ");
        String category = scanner.nextLine();

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setDiscount(discount);
        product.setCategory(category);

        Long id = productService.createProduct(product);
        System.out.println("Result: " + id);

    }

    private void findProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter task id: ");
        Long id = scanner.nextLong();
        Product product = productService.findProductById(id);
        System.out.println(product);
    }
}