package lk.ijse.hello_shoes_shop_backend.Service;

import lk.ijse.hello_shoes_shop_backend.Dto.SupplierDto;

import java.util.List;

public interface SupplierService {

    List<SupplierDto> getAllSuppliers();
    void saveSupplier(SupplierDto supplierDto);
    SupplierDto searchSupplier(String searchSupplierId);
    String updateSupplier(String updateSupplierId, SupplierDto supplierDto);
    String deleteSupplier(String deleteSupplierId);

}
