package bo.felipe.app.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ventas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="buy_order")
    private String buyOrder;

    @Column(name="session_id")
    private String sessionId;

    @Column(name="amount")
    private int    amount;

    @Column(name="return_url")
    private String returnUrl;

    @Column(name="token")
    private String token;

    @Column(name="url")
    private String url;

    @Column(name="status")
    private String status;

    @Column(name="authorization_code")
    private String authorization_code;

}

