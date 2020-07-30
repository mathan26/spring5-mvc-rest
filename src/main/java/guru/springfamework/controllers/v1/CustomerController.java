package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@Controller
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/customers")
    public ResponseEntity<CustomerListDTO> getListOfCustomers(){
        return new ResponseEntity<CustomerListDTO>(new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id){
        return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(id),HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.createNewCustomer(customerDTO),
                HttpStatus.CREATED);
    }
}
