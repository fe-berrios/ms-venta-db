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

    // getVentas
    @GetMapping("db/ventas")
    public List<VentaEntity> getVentas(){
        return ventaService.getVentas();
    }

    // getVentaById
    @GetMapping("/db/venta/{id}")
    public VentaEntity getVenta(@PathVariable Long id){
        return ventaService.getVentaById(id);
    }

    // getVentaByBO
    @GetMapping("/db/venta/bo/{buy_order}")
    public VentaEntity getVentaByBO(@PathVariable String buy_order){
        return ventaService.getVentaByBO(buy_order);
    }

    @GetMapping("/db/venta/token/{token}")
    public VentaEntity getVentaByToken(@PathVariable("token")String token){
        return ventaService.getVentaByToken(token);
    }

    // addVenta
    @PostMapping("/db/add/venta")
    public Venta addVenta(@RequestBody Venta venta){
        return ventaService.addVenta(venta);
    }

    // deleteVenta
    @DeleteMapping("/db/delete/venta/{id}")
    public void deleteVenta(@PathVariable("id")Long id){
        ventaService.deleteVenta(id);
    }

    // updateStatusVenta
    @PutMapping("/db/update/venta/{buy_order}")
    public Venta updateStatusVenta(@PathVariable("buy_order")String buy_order, @RequestBody Venta venta){
        return ventaService.updateStatusVenta(venta, buy_order);
    }

}