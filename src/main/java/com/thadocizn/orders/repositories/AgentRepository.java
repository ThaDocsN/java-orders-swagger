package com.thadocizn.orders.repositories;

import com.thadocizn.orders.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    @Query(value = "SELECT a.*, c.custname custname, c.custcode as custcode FROM agents a, customers c WHERE a.agentcode = c.agentcode ORDER BY a.agentname", nativeQuery = true)
    List<Object[]> agentsAndCustomers();

    @Query(value = "SELECT a.agentname, o.ordnum as ordernum, o.orddescription as orddesc FROM agents a, orders o WHERE a.agentcode = o.agentcode ORDER BY a.agentname", nativeQuery = true)
    List<Object[]> allAgentOrders();
}
