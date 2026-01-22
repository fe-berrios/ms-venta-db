package bo.felipe.app.controller;

import bo.felipe.app.model.Venta;
import bo.felipe.app.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class VentaController {

    @Autowired
    VentaService ventaService;

    @PostMapping("/db/add/venta")
    public Venta addVenta(@RequestBody Venta venta){
        return ventaService.addVenta(venta);
    }

}
