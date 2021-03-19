package ua.com.shop.formatter;

import ua.com.shop.model.Product;

import java.util.Comparator;
import java.util.List;

public class ProductFormatter {

    public static String format(List<Product> products) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(headOfTable());
        stringBuilder.append(bodyOfTable(products));
        return stringBuilder.toString();
    }

    private static String headOfTable() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Product price");
        stringBuilder.append(" | ");
        stringBuilder.append("Product name");
        stringBuilder.append(" | ");
        stringBuilder.append("Product status\n");

        return stringBuilder.toString();
    }

    private static <T extends Product> String bodyOfTable(List<T> products) {
        StringBuilder stringBuilder = new StringBuilder();
        // default width values for columns
        int firstColumnWidth = "Product price".length();
        int secondColumnWidth = "Product name".length();

        int longestPrice = products.stream()
                .map(p -> String.valueOf(p.getPrice()).length())
                .max(Comparator.naturalOrder()).orElse(0);

        int longestName = products.stream()
                .map(p -> p.getName().length())
                .max(Comparator.naturalOrder()).orElse(0);

        if (longestPrice > firstColumnWidth) {
            firstColumnWidth = longestPrice;
        }
        if (longestName > secondColumnWidth) {
            secondColumnWidth = longestName;
        }

        int finalFirstColumnWidth = firstColumnWidth;
        int finalSecondColumnWidth = secondColumnWidth;
        products.forEach(p -> {
            stringBuilder.append(p.getPrice());
            stringBuilder.append(" ".repeat(finalFirstColumnWidth - String.valueOf(p.getPrice()).length()));
            stringBuilder.append(" | ");
            stringBuilder.append(p.getName());
            stringBuilder.append(" ".repeat(finalSecondColumnWidth - p.getName().length()));
            stringBuilder.append(" | ");
            stringBuilder.append(p.getStatus());
            stringBuilder.append("\n");
        });
        return stringBuilder.toString();
    }
}