package service;

import dao.CartDao;
import dao.OrderDao;
import dao.ProductDao;
import entity.Cart;
import entity.Order;
import entity.Product;

import java.util.*;


public class Engine {
    public static void main(String[] args) {
        final long l = System.currentTimeMillis();
        final String leaderName = new Engine().getDecision();
        System.out.println("Time was spent: " + (-l + System.currentTimeMillis()));
        System.out.println(leaderName);
    }

    private final static String CUSTOM_DATE = "2021-01-21";

    private final OrderDao orderDao = new OrderDao();
    private final ProductDao productDao = new ProductDao();
    private final CartDao cartDao = new CartDao();

    /**
     * Basic operations for get result name.
     *
     * @return String name - name of the best successful product.
     */
    public String getDecision() {
        List<Order> orders = orderDao.read(); // id order and date
        List<Cart> carts = cartDao.read();// id order, id product, quantity product
        List<Product> products = productDao.read(); // id product, name product, price product
        orders = filterOrdersByDate(orders, CUSTOM_DATE);
        carts = filterCartsByOrders(carts, orders);
        carts = getCalculatedCarts(carts, products);
        carts.sort((o1, o2) -> o2.getTotal() - o1.getTotal());
        return getName(carts, products);
    }

    /**
     * Get list of all orders in specifying date.
     *
     * @param orders     all orders from data source.
     * @param customDate  specifying date
     * @return orders of specifying date.
     */
    public List<Order> filterOrdersByDate(List<Order> orders, String customDate) {
        return orders.stream().filter((item) -> item.getDate().equals(customDate)).toList();
    }

    /**
     * All carts that was ordered in specifying date.
     *
     * @param carts   all carts from data source.
     * @param orders  orders that was registered in specified date.
     * @return carts of specified date.
     */
    public List<Cart> filterCartsByOrders(List<Cart> carts, List<Order> orders) {
        final ArrayList<Cart> specifiedCarts = new ArrayList<>();
        for (Order order : orders) {
            String temp = order.getId();
            specifiedCarts.addAll(carts.stream().filter((cart -> cart.getOrderId().equals(temp))).toList());
        }
        return specifiedCarts;
    }

    /**
     * Calculate total value of all carts.
     *
     * @param carts     carts of specified date.
     * @param products  all products from data source.
     * @return carts with calculating total values.
     */
    public List<Cart> getCalculatedCarts(List<Cart> carts, List<Product> products) {
        for (Cart cart : carts) {
            cart.setTotal(cart.getQuantity() * getPrice(cart.getProductId(), products));
        }
        return carts;
    }

    /**
     * Get price from products data source by product_id.
     *
     * @param productId  specifying id of product.
     * @param products   all products from data source.
     * @return price of product.
     */
    private Integer getPrice(String productId, List<Product> products) {
        return products.stream().filter((item) -> item.getId().equals(productId)).findFirst().orElse(new Product()).getPrice();
    }

    /**
     * Get name of most value product from specifying cart set.
     *
     * @param carts     specifying cart set
     * @param products  all products from data source.
     * @return String name of most value product in specifying date.
     */
    public String getName(List<Cart> carts, List<Product> products) {
        final TreeMap<Product, Integer> totals = getTotals(carts, products);

        final Set<Map.Entry<Product, Integer>> entries = totals.entrySet();
        final String name = entries.stream().sorted((item1, item2) -> item2.getValue() - item1.getValue()).toList().get(0).getKey().getName();
        System.out.println();
        return name;
    }

    /**
     * Get map with all products in the date with total values that was got from carts in specifying date.
     *
     * @param carts     cart set.
     * @param products  all products from data source.
     * @return map with key: products and values: total of each other product.
     */
    private TreeMap<Product, Integer> getTotals(List<Cart> carts, List<Product> products) {
        final TreeMap<Product, Integer> totals = new TreeMap<>();
        products
                .forEach((item) -> totals.put(item, 0));
        carts.forEach((cart) -> {
            final Set<Product> namings = totals.keySet();
            namings.forEach((product) -> {
                if (product.getId().equals(cart.getProductId())) {
                    totals.put(product, totals.get(product) + cart.getTotal());
                }
            });
        });
        return totals;
    }

}
