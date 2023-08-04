package Banking.Banking.System.with.UI.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="BankTable")
public class BankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false)
    private String email;

    private Double depositAmt;

    private Double withdrawAmt;

    @Column(nullable = false)
    private Double Balance;
}
