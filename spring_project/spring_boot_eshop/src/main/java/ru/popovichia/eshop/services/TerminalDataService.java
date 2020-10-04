package ru.popovichia.eshop.services;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.popovichia.eshop.entities.Customer;

/**
 *
 * @author Igor Popovich, email: iapopo17@mts.ru, phone: +7 913 902 36 36,
 * company: mts.ru
 */
@Service
public interface TerminalDataService {
    
    public List<Customer> getAllCustomers();
    public List<Customer> getCustomersLikePattern(String stringPattern);
    
}
