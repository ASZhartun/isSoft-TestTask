package dao;

import dao.interfaces.EntityDao;
import entity.Cart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartDao implements EntityDao<Cart> {

    @Override
    public List<Cart> read() {
        final File file = new File(folder + "order_items.csv");
        final FileReader reader;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            return null;
        }
        final BufferedReader fileReader = new BufferedReader(reader);
        final List<String> collect = fileReader.lines().collect(Collectors.toList());
        collect.remove(0);
        final ArrayList<Cart> carts = new ArrayList<>();
        for (String item :
                collect) {
            final String[] split = item.split(",");
            carts.add(
                    new Cart(split[0], split[1], Integer.parseInt(split[2]))
            );
        }
        return carts;
    }
}
