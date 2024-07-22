package com.example.e_commerce.mapper;

import com.example.e_commerce.dto.UserDto.orderDto.OrderDto;
import com.example.e_commerce.dto.UserDto.orderDto.OrderProductsDto;
import com.example.e_commerce.entity.user.orders.Order;
import com.example.e_commerce.entity.user.orders.OrderProducts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static OrderDto orderToOrderDto(Order order){

        OrderDto res = new OrderDto();

        res.setAddressId(order.getAddress().getId());
        res.setOrderDate(order.getOrderDate());
        res.setCardNo(new BigInteger(order.getCardDetails().getCardNo()));
        res.setCardName(order.getCardDetails().getNameOnCard());
        res.setCardExpireMonth(order.getCardDetails().getExpireMonth());
        res.setCardExpireYear(order.getCardDetails().getExpireYear());
        res.setCardCcv(order.getCardCcv());
        res.setPrice(order.getPrice());

        List<OrderProductsDto> resOrderProducts = new ArrayList<>();

        for(OrderProducts or : order.getOrderProducts()){

            OrderProductsDto res1 = new OrderProductsDto();
            res1.setProductId(or.getProduct().getId());
            res1.setCount(or.getCount());
            res1.setDetail(or.getDetail());

            resOrderProducts.add(res1);
        }

        res.setProducts(resOrderProducts);

        return res;
    }


    public static List<OrderDto> orderListToOrderDtoList(List<Order> userAllOrders) {

        List<OrderDto> res = new ArrayList<>();

        for(Order or : userAllOrders){

            OrderDto trOrder = orderToOrderDto(or);
            res.add(trOrder);
        }

        return res;

    }
}
