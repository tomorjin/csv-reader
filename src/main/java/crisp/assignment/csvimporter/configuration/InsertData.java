package crisp.assignment.csvimporter.configuration;

import crisp.assignment.csvimporter.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class InsertData {
    private Integer defaultOrderId;
    private String defaultOrderDate;
    private String defaultOrderProductId;
    private String defaultOrderProductName;
    private BigDecimal defaultOrderQuantity;
    private String defaultOrderUnit;

    private int orderIdIdx = -1;
    private int orderDateYearIdx = -1;
    private int orderDateMonthIdx = -1;
    private int orderDateDayIdx = -1;
    private int orderDateHourIdx = -1;
    private int orderDateMinuteIdx = -1;
    private int orderDateSecondIdx = -1;
    private int orderProductIdIdx = -1;
    private int orderProductNameIdx = -1;
    private int orderQuantityIdx = -1;
    private int orderUnitIdx = -1;


    public InsertData() {
        try {
            defaultOrderId = Config.getInt("default.order.id");
        } catch (Exception ex) {
        }
        defaultOrderDate = Config.getString("default.order.date");
        defaultOrderProductId = Config.getString("default.order.productId");
        defaultOrderProductName = Config.getString("default.order.productName");
        try {
            defaultOrderQuantity = new BigDecimal(Config.getString("default.order.quantity"));
        } catch (Exception ex) {
        }
        defaultOrderUnit = Config.getString("default.order.unit");

        try {
            orderIdIdx = Integer.parseInt(Config.getString("order.id"));
        } catch (Exception ex) {
        }
        try {
            orderDateYearIdx = Integer.parseInt(Config.getString("order.date.year"));
        } catch (Exception ex) {
        }
        try {
            orderDateMonthIdx = Integer.parseInt(Config.getString("order.date.month"));
        } catch (Exception ex) {
        }
        try {
            orderDateDayIdx = Integer.parseInt(Config.getString("order.date.day"));
        } catch (Exception ex) {
        }
        try {
            orderDateHourIdx = Integer.parseInt(Config.getString("order.date.hour"));
        } catch (Exception ex) {
        }
        try {
            orderDateMinuteIdx = Integer.parseInt(Config.getString("order.date.minute"));
        } catch (Exception ex) {
        }
        try {
            orderDateSecondIdx = Integer.parseInt(Config.getString("order.date.second"));
        } catch (Exception ex) {
        }
        try {
            orderProductIdIdx = Config.getInt("order.productId");
        } catch (Exception ex) {
        }
        try {
            orderProductNameIdx = Config.getInt("order.productName");
        } catch (Exception ex) {
        }
        try {
            orderQuantityIdx = Config.getInt("order.quantity");
        } catch (Exception ex) {
        }
        try {
            orderUnitIdx = Config.getInt("order.unit");
        } catch (Exception ex) {
        }

    }

    public void insert(String[] columns) {
        for (int i = 0; i < columns.length; i++)
            columns[i] = columns[i].replaceAll(Config.getString("allowed.characters.regx"), "").trim();

        Order order = new Order();
        order.setOrderId((orderIdIdx != -1) ? Integer.parseInt(columns[orderIdIdx]) : defaultOrderId);
        if (orderDateYearIdx != -1 && orderDateMonthIdx != -1 && orderDateDayIdx != -1) {
            order.setOrderDate(LocalDateTime.of(
                    Integer.parseInt(columns[orderDateYearIdx]),
                    Integer.parseInt(columns[orderDateMonthIdx]),
                    Integer.parseInt(columns[orderDateDayIdx]),
                    Integer.parseInt(columns[orderDateDayIdx]),
                    (orderDateHourIdx != -1) ? Integer.parseInt(columns[orderDateHourIdx]) : 0,
                    (orderDateMinuteIdx != -1) ? Integer.parseInt(columns[orderDateMinuteIdx]) : 0,
                    (orderDateSecondIdx != -1) ? Integer.parseInt(columns[orderDateSecondIdx]) : 0
            ));
        } else {
            order.setOrderDate(LocalDateTime.parse(defaultOrderDate));
        }
        order.setProductId((orderProductIdIdx != -1) ? columns[orderProductIdIdx] : defaultOrderProductId);
        order.setProductName((orderProductNameIdx != -1) ? columns[orderProductNameIdx] : defaultOrderProductName);
        order.setQuantity((orderQuantityIdx != -1) ? new BigDecimal(columns[orderQuantityIdx]) : defaultOrderQuantity);
        order.setUnit((orderUnitIdx != -1) ? columns[orderUnitIdx] : defaultOrderUnit);

        // order object is ready to use.
    }
}
