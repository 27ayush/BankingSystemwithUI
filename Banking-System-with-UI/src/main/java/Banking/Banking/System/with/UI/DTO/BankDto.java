package Banking.Banking.System.with.UI.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BankDto {


    private Long id;

    private String Name;

    private String email;

    private Double depositAmt;

    private Double withdrawAmt;
    
    private Double Balance;
}
