package bo.felipe.app.controller;

import bo.felipe.app.model.Venta;
import bo.felipe.app.model.entity.VentaEntity;
import bo.felipe.app.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class VentaController {

    @Autowired
    VentaService ventaService;

    @PostMapping("/db/add/venta")
    public Venta addVenta(@RequestBody Venta venta){
        return ventaService.addVenta(venta);
    }

    @GetMapping("/db/venta/{id}")
    public VentaEntity getVenta(@PathVariable Long id){
        return ventaService.getVentaById(id);
    }

    @GetMapping("/db/venta/bo/{buy_order}")
    public List<VentaEntity> getVentaByBO(@PathVariable String buy_order){
        return ventaService.getVentaByBO(buy_order);
    }
}
