package bo.felipe.app.service;

import bo.felipe.app.model.Venta;
import bo.felipe.app.model.entity.VentaEntity;
import bo.felipe.app.model.repository.VentaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {

    @Autowired
    VentaRepository ventaRepository;

    public Venta addVenta(Venta venta){
        VentaEntity ventaEntity = new VentaEntity();

        ventaEntity.setBuyOrder(venta.getBuyOrder());
        ventaEntity.setSessionId(venta.getSessionId());
        ventaEntity.setAmount(venta.getAmount());
        ventaEntity.setReturnUrl(venta.getReturnUrl());
        ventaEntity.setToken(venta.getToken());
        ventaEntity.setUrl(venta.getUrl());

        ventaRepository.save(ventaEntity);
        return venta;
    }

    public List<VentaEntity> getVentas(){
        return ventaRepository.findAll();
    }

    public VentaEntity getVentaById(Long id){
        return ventaRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Usuario no encontrado: " + (id)));
    }

    public VentaEntity getVentaByToken(String token){
        return ventaRepository.findByToken(token);
    }

    public VentaEntity getVentaByBO(String buy_order){
        return ventaRepository.findByBuyOrder(buy_order);
    }

    public void deleteVenta(Long id){
        ventaRepository.deleteById(id);
    }

    public Venta updateStatusVenta(Venta venta, String buy_order){
        VentaEntity updatedVenta = getVentaByBO(buy_order);
        updatedVenta.setStatus(venta.getStatus());
        updatedVenta.setAuthorization_code(venta.getAuthorizationCode());

        ventaRepository.save(updatedVenta);
        return venta;
    }

}
