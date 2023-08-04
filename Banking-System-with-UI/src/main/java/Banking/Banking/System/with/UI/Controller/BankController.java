package Banking.Banking.System.with.UI.Controller;


import Banking.Banking.System.with.UI.DTO.BankDto;
import Banking.Banking.System.with.UI.Entity.BankEntity;
import Banking.Banking.System.with.UI.Service.BankingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class BankController {

    private BankingService bankingSector;

    @GetMapping("/bankCustomer/details")
    public String listOfAccount(Model model){

        model.addAttribute("customer", bankingSector.listOfAllCustomer());
        return "customer";

    }

    @GetMapping("/bankCustomer/new")
    public String createCustomerForm(Model model) {

        BankEntity addCustomer = new BankEntity();
        model.addAttribute("addCustomer", addCustomer);
        return "create_Account";

    }


    @PostMapping("/customer")
    public String createCustomer(@ModelAttribute BankDto bankDto) {
        BankDto savedCustomer = bankingSector.addCustomer(bankDto);
        return "redirect:/bankCustomer/details";
    }

    @GetMapping("/customerDetails/{id}")
    ResponseEntity<BankDto> getCustomer(@PathVariable("id") Long id){

        BankDto saveDto = bankingSector.getCustomer(id);

        return ResponseEntity.ok(saveDto);
    }

    @GetMapping("/customer/{id}/deposit")
    public String showDepositForm(@PathVariable Long id, Model model) {
        BankDto customerDeposit = bankingSector.getCustomer(id);
        model.addAttribute("customer", customerDeposit);
        return "deposit-form";
    }

    @PostMapping("/customer/{id}/deposits")
    public String processDepositForm(@PathVariable Long id, @RequestParam("depositAmt") Double depositAmt) {
        BankDto customer = bankingSector.getCustomer(id);
        customer.setDepositAmt(depositAmt);
        bankingSector.updateAccountWithDepositAmt(customer, id);
        return "redirect:/bankCustomer/details";
    }


    @GetMapping("/customer/{id}/withdraw")
    public String showWithdrawForm(@PathVariable Long id, Model model) {
        BankDto customerWithdraw = bankingSector.getCustomer(id);
        model.addAttribute("customerWithdraw", customerWithdraw);
        return "withdraw-form";
    }

    @PostMapping("/customer/{id}/withdraws")
    public String processWithdrawForm(@PathVariable("id") Long id, @RequestParam("withdrawAmt") Double withdrawAmt) {
        BankDto customer = bankingSector.getCustomer(id);
        customer.setWithdrawAmt(withdrawAmt);
        bankingSector.updateAccountWithWithdrawAmt(customer, id);
        return "redirect:/bankCustomer/details" ;
    }

    @GetMapping("/customer/{id}/delete")
    public String deleteCustomer(@PathVariable Long id) {
        bankingSector.deleteAccount(id);
        return "redirect:/bankCustomer/details";
    }

}
