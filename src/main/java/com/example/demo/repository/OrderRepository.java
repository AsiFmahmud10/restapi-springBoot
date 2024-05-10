package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.OrderhistoryResponse;
import com.example.demo.entity.Item;
import com.example.demo.entity.MyUser;
import com.example.demo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	@Query( value = " select * from (select temp.customer_id,temp.quantity,temp.total_price,food.name,food.price from"
			+ "(select * from "
			+ "(select orders .id as order_id ,orders .customer_id ,ORDERS_ITEMS .items_id   from orders join orders_items on orders.id = orders_items.orders_id "
			+ ") as res "
			+ "join items on res.items_id = items.id ) as temp   join food on food.ID  = temp.food_id ) as final_tab where final_tab.customer_id = :customerId   ",nativeQuery = true)
	List<Object[]> findOrderHistory(long customerId);
	
	
//	List<Order> findByMyUser(MyUser user);
}
