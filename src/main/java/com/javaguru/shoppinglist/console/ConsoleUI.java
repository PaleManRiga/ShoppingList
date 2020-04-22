package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.service.Category;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.domain.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class ConsoleUI {

    private ProductService productService;

    public ConsoleUI(ProductService productService) {
        this.productService = productService;

    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Delete product by id");
                System.out.println("4. Exit");
                int userInput = scanner.nextInt();
                switch (userInput) {
                    case 1:
                        createProduct();
                        break;
                    case 2:
                        findProduct();
                        break;
                    case 3:
                        deleteProduct();
                        break;
                    case 4:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
                System.out.println(e.getMessage());
            }
        }
    }

    private void createProduct() {
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
        product.setCategory(Category.valueOf(category));

        Long id = productService.createProduct(product);
        System.out.println("Result: " + id);

    }

    private void findProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        Long id = scanner.nextLong();
        Product product = productService.findProductById(id);
        System.out.println(product);
    }

    private void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        long id = scanner.nextLong();
        productService.deleteProduct(id);
    }
}