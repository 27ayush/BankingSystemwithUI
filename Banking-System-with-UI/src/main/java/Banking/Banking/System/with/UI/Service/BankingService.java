package Banking.Banking.System.with.UI.Service;

import Banking.Banking.System.with.UI.DTO.BankDto;

import java.util.*;

public interface BankingService  {

   BankDto addCustomer(BankDto bankDto);

   List<BankDto>  listOfAllCustomer();

   BankDto getCustomer(Long id);

   BankDto updateAccountWithDepositAmt(BankDto bankDto, Long id);

   BankDto updateAccountWithWithdrawAmt(BankDto bankDto, Long id);

   void deleteAccount(Long id);
}
