package com.thadocizn.orders.repositories;

import com.thadocizn.orders.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT c.*, o.ordnum as ordernum, o.ordamount as orderamount, o.advanceamount as advamount, o.orddescription as orddesc FROM customers c, orders o WHERE c.custcode = o.custcode ORDER BY c.custname", nativeQuery = true)
    List<Object[]> allCustomerOrders();

    @Query(value = "SELECT * FROM customers c WHERE c.custname = :name", nativeQuery = true)
    Object[] getCustomer(@Param("name") String name);

    @Query(value = "SELECT c.*, o.ordnum as ordernum, o.ordamount as orderamount, o.advanceamount as advamount, o.orddescription as orddesc FROM orders o, customers c WHERE c.custcode = o.custcode AND c.custname = :custname", nativeQuery = true)
    List<Object[]> customerOrders(@Param("custname") String custname);

    @Query(value = "SELECT c.*, o.ordnum as ordernum, o.ordamount as orderamount, o.advanceamount as advamount, o.orddescription as orddesc FROM orders o, customers c WHERE c.custcode = o.custcode AND c.custcode = :custcode", nativeQuery = true)
    List<Object[]> customerOrdersByCode(@Param("custcode") long custcode);
}
