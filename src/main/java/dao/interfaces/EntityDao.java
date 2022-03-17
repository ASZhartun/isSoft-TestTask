package dao.interfaces;

import java.util.List;

public interface EntityDao <T>{
    public static String folder = "E:\\isSoft-TestTask\\src\\main\\resources\\";

    List<T> read();
}
