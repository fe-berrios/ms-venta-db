package bo.felipe.app.model.repository;

import bo.felipe.app.model.entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<VentaEntity, Long> {
    /*Get desde buyOrder*/
    List<VentaEntity> findByBuyOrder(String buy_order);
}
