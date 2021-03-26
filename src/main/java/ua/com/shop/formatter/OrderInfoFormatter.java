package ua.com.shop.formatter;

import ua.com.shop.model.OrderInfo;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class OrderInfoFormatter {

    public static String format(List<OrderInfo> orders) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(headOfTable());
        stringBuilder.append(bodyOfTable(orders));
        return stringBuilder.toString();
    }

    private static String headOfTable() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order id");
        stringBuilder.append(" | ");
        stringBuilder.append("Total sum");
        stringBuilder.append(" | ");
        stringBuilder.append("Product name");
        stringBuilder.append(" | ");
        stringBuilder.append("Quantity");
        stringBuilder.append(" | ");
        stringBuilder.append("Order date created at\n");
        return stringBuilder.toString();
    }

    private static String bodyOfTable(List<OrderInfo> orders) {
        StringBuilder stringBuilder = new StringBuilder();

        // default columns width value
        int firstColumnWidth = "Order id".length();
        int secondColumnWidth = "Total sum".length();
        int thirdColumnWidth = "Product name".length();
        int fourthColumnWidth = "Quantity".length();

        int longestId = String.valueOf(orders.stream()
                .map(OrderInfo::getOrderId)
                .max(Comparator.naturalOrder())
                .orElse(0L)).length();

        int longestTotalSum = String.valueOf(orders.stream()
                .map(OrderInfo::getTotalSum)
                .max(Comparator.naturalOrder())
                .orElse(0)).length();

        int longestName = orders.stream()
                .map(o -> o.getProductName().length())
                .max(Comparator.naturalOrder())
                .orElse(0);

        int longestQuantity = String.valueOf(orders.stream()
                .map(OrderInfo::getQuantity)
                .max(Comparator.naturalOrder())
                .orElse(0)).length();

        if (longestId > firstColumnWidth) {
            firstColumnWidth = longestId;
        }
        if (longestTotalSum > secondColumnWidth) {
            secondColumnWidth = longestTotalSum;
        }
        if (longestName > thirdColumnWidth) {
            thirdColumnWidth = longestName;
        }
        if (longestQuantity > fourthColumnWidth) {
            fourthColumnWidth = longestQuantity;
        }

        int finalFirstColumnWidth = firstColumnWidth;
        int finalSecondColumnWidth = secondColumnWidth;
        int finalThirdColumnWidth = thirdColumnWidth;
        int finalFourthColumnWidth = fourthColumnWidth;
        orders.forEach(orderInfo -> {
            stringBuilder.append(orderInfo.getOrderId());
            stringBuilder.append(
                    " ".repeat(finalFirstColumnWidth - String.valueOf(orderInfo.getOrderId()).length()));
            stringBuilder.append(" | ");

            stringBuilder.append(orderInfo.getTotalSum());
            stringBuilder.append(
                    " ".repeat(finalSecondColumnWidth - String.valueOf(orderInfo.getTotalSum()).length()));
            stringBuilder.append(" | ");

            stringBuilder.append(orderInfo.getProductName());
            stringBuilder.append(
                    " ".repeat(finalThirdColumnWidth - orderInfo.getProductName().length()));
            stringBuilder.append(" | ");

            stringBuilder.append(orderInfo.getQuantity());
            stringBuilder.append(
                    " ".repeat(finalFourthColumnWidth - String.valueOf(orderInfo.getQuantity()).length()));
            stringBuilder.append(" | ");

            stringBuilder.append(
                    orderInfo.getCreatedAt().format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            ));
            stringBuilder.append("\n");
        });
        return stringBuilder.toString();
    }
}
