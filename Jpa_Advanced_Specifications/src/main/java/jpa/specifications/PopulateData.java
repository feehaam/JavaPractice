package jpa.specifications;

import jakarta.annotation.PostConstruct;
import jpa.specifications.models.Order;
import jpa.specifications.models.OrderItem;
import jpa.specifications.models.Product;
import jpa.specifications.models.User;
import jpa.specifications.repositories.OrderRepository;
import jpa.specifications.repositories.ProductRepository;
import jpa.specifications.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class PopulateData {

    public PopulateData(ProductRepository productRepository, UserRepository userRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @PostConstruct
    public void init(){
        populate();
    }

    public void populate() {
        // Create Products
        for (int i = 1; i <= 15; i++) {
            String name = "Product " + i;
            String description = "This is a description for product " + i;
            double price = Math.random() * 3000; // Random price between 0 and 100
            double rating = Math.random() * 5; // Random rating between 0 and 5
            String thumbnailUrl = "https://via.placeholder.com/150"; // Placeholder image
            productRepository.save(new Product(name, description, price, rating, thumbnailUrl, LocalDateTime.now(), LocalDateTime.now()));
        }

        // Create Users from South Asian Countries (SARK)
        String[] countries = {"India", "Pakistan", "Bangladesh", "Nepal", "Sri Lanka"};
        for (int i = 1; i <= 5; i++) {
            String firstName = "User " + i + " First Name";
            String lastName = "User " + i + " Last Name";
            String email = "user" + i + "@example.com";
            LocalDate dob = LocalDate.now().minusYears(20 + (int) (Math.random() * 30)); // Random DoB between 20 and 50 years ago
            String country = countries[(int) (Math.random() * countries.length)];
            String city = "City " + i;
            userRepository.save(new User(firstName, lastName, email, dob, country, city));
        }

        // Create Orders
        createOrders(1, 2);
        createOrders(4, 1);
        createOrders(5, 3);
        createOrders(3, 1);
    }

    private void createOrders(int userId, int numOrders) {
        User user = userRepository.findById((long) userId).orElseThrow();
        List<Product> allProducts = productRepository.findAll();
        for (int i = 1; i <= numOrders; i++) {
            LocalDateTime orderTime = LocalDateTime.now().minusDays(i);
            String tracId = "O" + user.getId() + "-" + i;
            double totalAmount = 0;
            String voucher = generateVoucher();
            String offer = generateOffer();
            double deliveryCharge = random.nextInt(75) + 30.0;
            Order order = new Order(tracId, null, orderTime, voucher, null,
                    null, offer, null,
                    deliveryCharge, null);
            List<OrderItem> orderItems = new java.util.ArrayList<>();
            for (int j = 0; j < (int) (Math.random() * 5 + 1); j++) {
                Product randomProduct = allProducts.get((int) (Math.random() * allProducts.size()));
                int quantity = (int) (Math.random() * 5 + 1);
                totalAmount += randomProduct.getPrice() * quantity;
                orderItems.add(new OrderItem(randomProduct, quantity, randomProduct.getPrice(), order));
            }

            double voucherReduction = totalAmount / 100 * generateVoucherReduction(voucher, totalAmount);
            double offerReduction = totalAmount / 100 * generateOfferReduction(offer, totalAmount);
            order.setVoucherReduction(voucherReduction);
            order.setOfferReduction(offerReduction);
            order.setOrderItems(orderItems);
            order.setTotalAmount(totalAmount);
            order.setFinalAmount(Math.max(0, totalAmount - voucherReduction - offerReduction + deliveryCharge));
            order.setUser(user);
            orderRepository.save(order);
        }
    }

    static Random random = new Random();
    private String generateVoucher(){
        List<String> vouchers = List.of("NEW-USER", "CRAZY-DEAL", "SUMMER-2024");
        if (random.nextInt(10) > 3){
            return vouchers.get(random.nextInt(3));
        }
        return null;
    }

    private double generateVoucherReduction(String voucher, double price){
        if(voucher == null) return 0;
        return random.nextInt(4) * 10;
    }

    private String generateOffer(){
        List<String> vouchers = List.of("EID", "ELECTRONICS", "LUCKY-SPIN");
        if (random.nextInt(10) > 3){
            return vouchers.get(random.nextInt(3));
        }
        return null;
    }

    private double generateOfferReduction(String offer, double price){
        if(offer == null) return 0;
        return random.nextInt(3) * 10;
    }
}
