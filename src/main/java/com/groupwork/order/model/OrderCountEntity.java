package com.groupwork.order.model;

import com.groupwork.order.datasource.dto.Address;
import lombok.Data;

import java.util.List;

@Data
public class OrderCountEntity {

    private Long orderId;
    private Double totalMoney;
    private List<Address> userAdderss;
    private List<OrderFood> orderFoods;

}
