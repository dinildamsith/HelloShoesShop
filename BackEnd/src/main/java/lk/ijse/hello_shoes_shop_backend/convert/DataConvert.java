package lk.ijse.hello_shoes_shop_backend.convert;

import lk.ijse.hello_shoes_shop_backend.Dto.*;
import lk.ijse.hello_shoes_shop_backend.entity.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataConvert {
    private final ModelMapper modelMapper;


    // User Data
    public UserDto userEntityConvertUserDto(UserEntity userEntity){
        return modelMapper.map(userEntity, UserDto.class);
    }
    public UserEntity userDtoConvertUserEntity(UserDto userDto){
        return modelMapper.map(userDto, UserEntity.class);
    }

    //Customer Data
    public CustomerDto customerEntityConvertCustomerDto(CustomerEntity customerEntity){
        return modelMapper.map(customerEntity, CustomerDto.class);
    }
    public CustomerEntity customerDtoConvertCustomerEntity(CustomerDto customerDto){
        return modelMapper.map(customerDto, CustomerEntity.class);
    }
    public List<CustomerDto> customerEntityListConvertCustomerDtoList(List<CustomerEntity> customerEntityList){
        return modelMapper.map(customerEntityList,List.class);
    }

    //Supplier Data
    public SupplierEntity supplierDtoConvertSupplierEntity(SupplierDto supplierDto){
        return modelMapper.map(supplierDto,SupplierEntity.class);
    }
    public SupplierDto supplierEntityConvertSupplierDto(SupplierEntity supplierEntity){
        return modelMapper.map(supplierEntity, SupplierDto.class);
    }
    public List<SupplierDto> supplierEntityListConvertSupplierDtoList(List<SupplierEntity> supplierEntityList){
        return modelMapper.map(supplierEntityList, List.class);
    }

    // Employee Data
    public EmployeeEntity employeeDtoConvertEmployeeEntity(EmployeeDto employeeDto){
        return modelMapper.map(employeeDto,EmployeeEntity.class);
    }
    public EmployeeDto employeeEntityConvertEmployeeDto(EmployeeEntity employeeEntity){
        return modelMapper.map(employeeEntity,EmployeeDto.class);
    }
    public List<EmployeeDto> employeeEntityListConvertEmployeeDtoList(List<EmployeeEntity> employeeEntityList){
        return modelMapper.map(employeeEntityList, List.class);
    }

    // item Data
    public ItemEntity itemDtoConvertItemEntity(ItemDto itemDto){
        return modelMapper.map(itemDto, ItemEntity.class);
    }
    public ItemDto itemEntityConvertItemDto(ItemEntity itemEntity){
        return modelMapper.map(itemEntity, ItemDto.class);
    }
    public List<ItemDto> itemEntityListConvertItemDtoList(List<ItemEntity> itemEntityList){
        return modelMapper.map(itemEntityList, List.class);
    }

    //order Data
    public OrderDto orderEntityConvertOrderDto(OrderEntity orderEntity){
       return modelMapper.map(orderEntity, OrderDto.class);
    }
    public OrderEntity orderDtoConvertOrderEntity(OrderDto orderDto){
        return modelMapper.map(orderDto, OrderEntity.class);
    }

    public List<OrderDto> ordeEntityListConvertOrdeDtoList(List<OrderEntity> orderEntityList){
        return modelMapper.map(orderEntityList, List.class);
    }

    //
    public ReturnDto returnEntityConvertReturnDto(ReturnEntity returnEntity){
        return modelMapper.map(returnEntity, ReturnDto.class);
    }

    public ReturnEntity returnDtoConvertReturnEntity(ReturnDto returnDto){
        return modelMapper.map(returnDto, ReturnEntity.class);
    }
}
