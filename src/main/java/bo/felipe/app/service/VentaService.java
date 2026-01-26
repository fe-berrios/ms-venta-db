package bo.felipe.app.service;

import bo.felipe.app.model.Venta;
import bo.felipe.app.model.entity.VentaEntity;
import bo.felipe.app.model.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        VentaEntity nuevaVenta = ventaRepository.save(ventaEntity);

        return venta;
    }



}
