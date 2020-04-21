package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.service.Category;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ShoppingCartProductService;
import com.javaguru.shoppinglist.service.ShoppingCartService;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class ConsoleUI {

    private ProductService productService;
    private ShoppingCartService shoppingCartService;
    private ShoppingCartProductService shoppingCartProductService;

    public ConsoleUI(ProductService productService, ShoppingCartService shoppingCartService, ShoppingCartProductService shoppingCartProductService) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
        this.shoppingCartProductService = shoppingCartProductService;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Delete product by id");
                System.out.println("4. Create shopping cart");
                System.out.println("5. Find shopping cart by id");
                System.out.println("6. Assign product to shopping cart");
                System.out.println("7. Exit");
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
                        createShoppingCart();
                        break;
                    case 5:
                        findShoppingCartById();
                        break;
                    case 6:
                        assignProductToShoppingCartById();
                        break;
                    case 7:
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
        Product product = productService.findProduct(id);
        System.out.println(product);
    }

    private void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        long id = scanner.nextLong();
        productService.deleteProduct(id);
    }

    private void createShoppingCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter shopping cart name: ");
        String cartName = scanner.nextLine();
        System.out.println("Enter shopping cart description: ");
        String cartDescription = scanner.nextLine();

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setName(cartName);
        shoppingCart.setDescription(cartDescription);

        Long shoppingCartId = shoppingCartService.createShoppingCart(shoppingCart);
        System.out.println("Result: " + shoppingCartId);
    }

    private void findShoppingCartById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter shopping cart id: ");
        long cartId = scanner.nextLong();
        ShoppingCart findShoppingCartResult = shoppingCartService.findShoppingCart(cartId);
        System.out.println(findShoppingCartResult);
    }

    private void assignProductToShoppingCartById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        long createdProductId = scanner.nextLong();
        System.out.println("Enter shopping cart id: ");
        long createdCartId = scanner.nextLong();
        System.out.println("Enter products amount in shopping cart : ");
        long productsAmount = scanner.nextLong();
        shoppingCartProductService.assignProductToShoppingCart(createdProductId, createdCartId, productsAmount);
    }
}