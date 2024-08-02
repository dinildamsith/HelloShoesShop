package lk.ijse.hello_shoes_shop_backend.Controller;

import lk.ijse.hello_shoes_shop_backend.Dto.SupplierDto;
import lk.ijse.hello_shoes_shop_backend.Service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500", methods = {RequestMethod.PATCH, RequestMethod.DELETE ,RequestMethod.POST,RequestMethod.PUT,RequestMethod.GET})
public class SupplierController {

    @Autowired
    SupplierService supplierService;


    @GetMapping
    @RequestMapping("/getAllSuppliers")
    public List<SupplierDto> getAllSuppliers(){
        List<SupplierDto> allSuppliers = supplierService.getAllSuppliers();
        return allSuppliers;
    }

    @PostMapping
    @RequestMapping("/save")
    public void saveSupplier(@RequestBody SupplierDto supplierDto){
        supplierService.saveSupplier(supplierDto);
    }

    @GetMapping
    @RequestMapping("/search/{id}")
    public SupplierDto searchSupplier(@PathVariable ("id") String searchSupplierId){
        SupplierDto searchSupplierDto = supplierService.searchSupplier(searchSupplierId);
        return searchSupplierDto;
    }

    @PutMapping
    @RequestMapping("/update/{id}")
    public String updateSupplier(@PathVariable("id") String updateSupplierId , @RequestBody SupplierDto supplierDto){
        String updateSupplier = supplierService.updateSupplier(updateSupplierId, supplierDto);
        return updateSupplier;
    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSupplier(@PathVariable ("id") String deleteSupplierId){
        String deleteSupplier = supplierService.deleteSupplier(deleteSupplierId);
        return deleteSupplier;
    }



}
