package Banking.Banking.System.with.UI.Service;

import Banking.Banking.System.with.UI.DTO.BankDto;
import Banking.Banking.System.with.UI.Entity.BankEntity;
import Banking.Banking.System.with.UI.Exception.ResourceNotFoundException;
import Banking.Banking.System.with.UI.Repository.BankingRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BankingServiceImpl implements BankingService{

    private BankingRepository bankingRepository;
    private ModelMapper modelMapper;


    @Override
    public BankDto addCustomer(BankDto bankDto) {
        BankEntity bankEntity = modelMapper.map(bankDto, BankEntity.class);
        BankEntity saveEntity = bankingRepository.save(bankEntity);
        BankDto saveDto = modelMapper.map(saveEntity, BankDto.class);
        return saveDto;
    }

    @Override
    public List<BankDto> listOfAllCustomer() {

        List<BankEntity> save =  bankingRepository.findAll();
        return save.stream().map((bankEntity) -> modelMapper.map(bankEntity, BankDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BankDto getCustomer(Long id) {

        BankEntity bankEntity = bankingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("id not found"));
        return modelMapper.map(bankEntity, BankDto.class);
    }

    @Override
    public BankDto updateAccountWithDepositAmt(BankDto bankDto, Long id) {

        BankEntity bankEntity    = bankingRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Id not found"));
        Double depositAmt1 = bankDto.getDepositAmt();
        bankEntity.setBalance(bankEntity.getBalance()+depositAmt1);
        bankEntity.setDepositAmt(depositAmt1);
        BankEntity save = bankingRepository.save(bankEntity);
        return  modelMapper.map(save, BankDto.class);

    }

    @Override
    public BankDto updateAccountWithWithdrawAmt(BankDto bankDto, Long id) {
        BankEntity bankEntity = bankingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("id not found"));
        Double withdrawAmount1 = bankDto.getWithdrawAmt();
        bankEntity.setBalance(bankEntity.getBalance()-withdrawAmount1);
        bankEntity.setWithdrawAmt(withdrawAmount1);
        BankEntity save = bankingRepository.save(bankEntity);
        return modelMapper.map(save,BankDto.class);
    }

    @Override
    public void deleteAccount(Long id) {
       bankingRepository.deleteById(id);
    }

}
