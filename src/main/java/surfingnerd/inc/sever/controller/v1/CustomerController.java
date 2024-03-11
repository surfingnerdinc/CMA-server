package surfingnerd.inc.sever.controller.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @RequestMapping(path = "/a/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCustomers() {
        return null;
    }


    @RequestMapping(path = "/e/{employeeUsername}/{customerPk}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomer(@PathVariable Long customerPk) {
        return null;
    }

    @RequestMapping(path = "/e/{employeeUsername}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCustomersAssignedToEmployee(@PathVariable Long employeeUsername) {
        return null;
    }
}
