package dao;

import dao.interfaces.EntityDao;
import entity.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDao implements EntityDao<Product> {
    @Override
    public List<Product> read() {
        final File file = new File(folder + "products.csv");
        final FileReader reader;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            return null;
        }
        final BufferedReader fileReader = new BufferedReader(reader);
        final List<String> collect = fileReader.lines().collect(Collectors.toList());
        collect.remove(0);
        final ArrayList<Product> products = new ArrayList<>();
        for (String item : collect) {
            final String[] split = item.split(",");
            products.add(new Product(split[0], split[1], Integer.parseInt(split[2])));
        }
        return products;
    }
}
