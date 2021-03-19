package ua.com.shop.formatter;

import ua.com.shop.model.ProductInfo;
import ua.com.shop.model.ProductStatus;

import java.util.Comparator;
import java.util.List;

public class ProductInfoFormatter {

    public static String format(List<ProductInfo> products) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(headOfTable());
        stringBuilder.append(bodyOfTable(products));
        return stringBuilder.toString();
    }

    private static String headOfTable() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Product id");
        stringBuilder.append(" | ");
        stringBuilder.append("Product name");
        stringBuilder.append(" | ");
        stringBuilder.append("Product price");
        stringBuilder.append(" | ");
        stringBuilder.append("Product status");
        stringBuilder.append(" | ");
        stringBuilder.append("Total ordered quantity\n");

        return stringBuilder.toString();
    }

    private static String bodyOfTable(List<ProductInfo> products) {
        long firstColumnWidth = "Product id".length();
        long secondColumnWidth = "Product name".length();
        long thirdColumnWidth = "Product price".length();
        long fourthColumnWidth = "Product satus".length();

        StringBuilder stringBuilder = new StringBuilder();

        long longestId = products.stream()
                .map(productInfo -> productInfo.getProduct().getId())
                .max(Comparator.naturalOrder())
                .orElse(0L);

        long longestName = products.stream()
                .map(p -> p.getProduct().getName().length())
                .max(Comparator.naturalOrder())
                .orElse(0);

        long longestPrice = products.stream()
                .map(p -> String.valueOf(p.getProduct().getPrice()).length())
                .max(Comparator.naturalOrder())
                .orElse(0);

        long longestStatus = ProductStatus.get(2).toString().length();

        if (longestId > firstColumnWidth) {
            firstColumnWidth = longestId;
        }
        if (longestName > secondColumnWidth) {
            secondColumnWidth = longestName;
        }
        if (longestPrice > thirdColumnWidth) {
            thirdColumnWidth = longestPrice;
        }

        long finalFirstColumnWidth = firstColumnWidth;
        long finalSecondColumnWidth = secondColumnWidth;
        long finalThirdColumnWidth = thirdColumnWidth;
        products.forEach(p -> {
                stringBuilder.append(p.getProduct().getId());
                stringBuilder.append(" ".repeat(
                        (int) (finalFirstColumnWidth - String.valueOf(p.getProduct().getId()).length()))
                );
                stringBuilder.append(" | ");
                stringBuilder.append(p.getProduct().getName());
                stringBuilder.append(" ".repeat(
                        (int) (finalSecondColumnWidth - p.getProduct().getName().length()))
                );
                stringBuilder.append(" | ");
                stringBuilder.append(p.getProduct().getPrice());
                stringBuilder.append(" ".repeat(
                        (int) (finalThirdColumnWidth - String.valueOf(p.getProduct().getPrice()).length()))
                );
                stringBuilder.append(" | ");
                stringBuilder.append(p.getProduct().getStatus());
                stringBuilder.append(" ".repeat(
                        (int) (longestStatus - p.getProduct().getStatus().toString().length()))
                );
                stringBuilder.append(" | ");
                stringBuilder.append(p.getTotalQuantity());
                stringBuilder.append("\n");
            });
        return stringBuilder.toString();
    }
}
