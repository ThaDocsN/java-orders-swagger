package com.thadocizn.orders;

import com.thadocizn.orders.models.Agent;
import com.thadocizn.orders.models.Customer;
import com.thadocizn.orders.models.Order;
import com.thadocizn.orders.repositories.AgentRepository;
import com.thadocizn.orders.repositories.CustomerRepository;
import com.thadocizn.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdersController {
    @Autowired
    AgentRepository agentRepo;

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    OrderRepository orderRepo;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @GetMapping("/customer/order")
    public List<Object[]> getAllCustomerOrders() {
        return customerRepo.allCustomerOrders();
    }

    @GetMapping("/customer/name/{custname}")
    public List<Object[]> getCustomerOrders(@PathVariable String custname) {
        return customerRepo.customerOrders(custname);
    }

    @GetMapping("/customer/order/{custcode}")
    public List<Object[]> getCustomerOrdersByCode(@PathVariable long custcode) {
        return customerRepo.customerOrdersByCode(custcode);
    }

    @GetMapping("/customers/custcode/{custcode}")
    public Customer getCustomer(@PathVariable long custcode) {
        return customerRepo.findById(custcode).orElseThrow();
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) throws URISyntaxException {
        return customerRepo.save(customer);
    }

    @PutMapping("/customer/custcode/{custcode}")
    public Customer updateCustomer(@RequestBody Customer newCustomer, @PathVariable long custcode) throws URISyntaxException {
        Optional<Customer> customerToUpdate = customerRepo.findById(custcode);
        if (customerToUpdate.isPresent()) {
            newCustomer.setCustCode(custcode);
            customerRepo.save(newCustomer);
            return newCustomer;
        }
        return null;
    }

    @DeleteMapping("/customers/custcode/{custcode}")
    public Customer deleteCustomer(@PathVariable long custcode) {
        var foundCustomer = customerRepo.findById(custcode);
        if (foundCustomer.isPresent()) {
            customerRepo.deleteById(custcode);
            return foundCustomer.get();
        }
        return null;
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @GetMapping("/orders/ordnum/{ordnum}")
    public Order getOrder(@PathVariable long ordnum) {
        return orderRepo.findById(ordnum).orElseThrow();
    }

    @PostMapping("/orders")
    public Order addOrder(@RequestBody Order order) throws URISyntaxException {
        return orderRepo.save(order);
    }

    @PutMapping("/orders/ordnum/{ordnum}")
    public Order updateOrder(@RequestBody Order newOrder, @PathVariable long ordnum) throws URISyntaxException {
        Optional<Order> orderToUpdate = orderRepo.findById(ordnum);
        if (orderToUpdate.isPresent()) {
            newOrder.setOrdNum(ordnum);
            orderRepo.save(newOrder);
            return newOrder;
        }
        return null;
    }

    @DeleteMapping("/orders/ordnum/{ordnum}")
    public Order deleteOrder(@PathVariable long ordnum) {
        var foundIt = orderRepo.findById(ordnum);

        if (foundIt.isPresent()) {
            orderRepo.deleteById(ordnum);
            return foundIt.get();
        }
        return null;
    }

    @GetMapping("/agents")
    public List<Agent> getAllAgents() {
        return agentRepo.findAll();
    }

    @GetMapping("/agents/agentcode/{agentcode}")
    public Agent getAgent(@PathVariable long agentcode) {
        return agentRepo.findById(agentcode).orElseThrow();
    }

    @GetMapping("/agents/customers")
    public List<Object[]> getAgentsAndCustomers() {
        return agentRepo.agentsAndCustomers();
    }

    @GetMapping("/agents/orders")
    public List<Object[]> getAllAgentOrders() {
        return agentRepo.allAgentOrders();
    }

    @PostMapping("/agents")
    public Agent addAgent(@RequestBody Agent agent) throws URISyntaxException {
        return agentRepo.save(agent);
    }

    @PutMapping("/agents/agentcode/{agentcode}")
    public Agent updateAgent(@RequestBody Agent newAgent, @PathVariable long agentcode) throws URISyntaxException {
        Optional<Agent> agentToUpdate = agentRepo.findById(agentcode);
        if (agentToUpdate.isPresent()) {
            newAgent.setAgentCode(agentcode);
            agentRepo.save(newAgent);
            return newAgent;
        }
        return null;
    }

    @DeleteMapping("/agents/agentcode/{agentcode}")
    public Agent deleteAgent(@PathVariable long agentcode) {
        var foundAgent = agentRepo.findById(agentcode);
        if (foundAgent.isPresent()) {
            agentRepo.deleteById(agentcode);
            return foundAgent.get();
        }
        return null;

    }
}