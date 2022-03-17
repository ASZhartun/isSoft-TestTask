package dao;

import dao.interfaces.EntityDao;
import entity.Order;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDao implements EntityDao<Order> {

    public static void main(String[] args) {
        final List<Order> read = new OrderDao().read();
        System.out.println();
    }

    @Override
    public List<Order> read() {
        final File file = new File(folder + "orders.csv");
        final FileReader reader;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            return null;
        }
        final BufferedReader fileReader = new BufferedReader(reader);
        final List<String> collect = fileReader.lines().collect(Collectors.toList());
        collect.remove(0);
        final ArrayList<Order> orders = new ArrayList<>();
        for (String item :
                collect) {
            final String[] split = item.split(",");
            orders.add(
                    new Order(split[0], split[1].split("T")[0])
            );
        }
        return orders;
    }


}
