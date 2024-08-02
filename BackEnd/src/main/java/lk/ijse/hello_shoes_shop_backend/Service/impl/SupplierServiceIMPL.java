package lk.ijse.hello_shoes_shop_backend.Service.impl;

import lk.ijse.hello_shoes_shop_backend.Dao.SupplierRepo;
import lk.ijse.hello_shoes_shop_backend.Dto.SupplierDto;
import lk.ijse.hello_shoes_shop_backend.Service.SupplierService;
import lk.ijse.hello_shoes_shop_backend.convert.DataConvert;
import lk.ijse.hello_shoes_shop_backend.entity.SupplierEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierServiceIMPL implements SupplierService {

    @Autowired
    SupplierRepo supplierRepo;
    @Autowired
    DataConvert dataConvert;

    Logger logger = LoggerFactory.getLogger(SupplierServiceIMPL.class);

    @Override
    public List<SupplierDto> getAllSuppliers() {
        logger.info("Get All Suppliers Success");
        return dataConvert.supplierEntityListConvertSupplierDtoList(supplierRepo.findAll());
    }

    @Override
    public void saveSupplier(SupplierDto supplierDto) {
        logger.info("Supplier Save Success");
        supplierRepo.save(dataConvert.supplierDtoConvertSupplierEntity(supplierDto));
    }

    @Override
    public SupplierDto searchSupplier(String searchSupplierId) {
        boolean existsBySupplierId = supplierRepo.existsById(searchSupplierId);

        if (existsBySupplierId){
            logger.info("Supplier Search Success");
            SupplierEntity supplierEntity = supplierRepo.findById(searchSupplierId).get();
            return dataConvert.supplierEntityConvertSupplierDto(supplierEntity);
        }else {
            logger.info("This Id Have no Supplier");
        }
        return null;
    }

    @Override
    public String updateSupplier(String updateSupplierId, SupplierDto updateSupplierDetails) {
        SupplierEntity updatedSupplierEntity = supplierRepo.findById(updateSupplierId).orElse(null);
        if (updatedSupplierEntity != null){
            updatedSupplierEntity.setSupplierName(updateSupplierDetails.getSupplierName());
            updatedSupplierEntity.setCategory(updateSupplierDetails.getCategory());
            updatedSupplierEntity.setAddressLine1(updateSupplierDetails.getAddressLine1());
            updatedSupplierEntity.setAddressLine2(updateSupplierDetails.getAddressLine2());
            updatedSupplierEntity.setAddressLine3(updateSupplierDetails.getAddressLine3());
//            updatedSupplierEntity.setAddressLine4(updateSupplierDetails.getAddressLine4());
//            updatedSupplierEntity.setAddressLine5(updateSupplierDetails.getAddressLine5());
//            updatedSupplierEntity.setAddressLine6(updateSupplierDetails.getAddressLine6());
            updatedSupplierEntity.setContact1(updateSupplierDetails.getContact1());
            updatedSupplierEntity.setContact2(updateSupplierDetails.getContact2());
            updatedSupplierEntity.setEmail(updateSupplierDetails.getEmail());

            supplierRepo.save(updatedSupplierEntity);
            logger.info("Supplier Update Success");
            return "Update Supplier Success";
        }else {
            logger.info("This Id Have No Supplier");
            return "This Id Have No Supplier";
        }

    }

    @Override
    public String deleteSupplier(String deleteSupplierId) {
        boolean existsBySupplierId = supplierRepo.existsById(deleteSupplierId);
        if (existsBySupplierId){
            supplierRepo.deleteById(deleteSupplierId);
            logger.info("Supplier Delete Success");
            return "Supplier DELETED";
        }else {
            logger.info("This Id Have No Supplier");
           return  "This Id Have No Supplier";
        }

    }

}
