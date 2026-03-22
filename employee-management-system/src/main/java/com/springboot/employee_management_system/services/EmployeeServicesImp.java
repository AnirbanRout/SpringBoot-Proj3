package com.springboot.employee_management_system.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.employee_management_system.dto.AddressDto;
import com.springboot.employee_management_system.dto.EmployeeDto;
import com.springboot.employee_management_system.entity.Address;
import com.springboot.employee_management_system.entity.Employee;
import com.springboot.employee_management_system.exception.EmployeeNotFoundException;
import com.springboot.employee_management_system.repository.AddressRepository;
import com.springboot.employee_management_system.repository.EmployeeRepository;

@Service("empimp")
public class EmployeeServicesImp implements EmployeeServices {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public String addEmployee(EmployeeDto employeeDto) {

        Employee employee = Employee.builder()
                .name(employeeDto.getName())
                .email(employeeDto.getEmail())
                .age(employeeDto.getAge())
                .salary(employeeDto.getSalary())
                .build();

        List<Address> addressList = new ArrayList<>();
        if (employeeDto.getAddresses() != null) {
            for (AddressDto addr : employeeDto.getAddresses()) {

                Address address = Address.builder()
                        .street(addr.getStreet())
                        .city(addr.getCity())
                        .state(addr.getState())
                        .country(addr.getCountry())
                        .build();

                addressList.add(address);
                address.setEmployee(employee);

            }
        }

        employee.setAddresses(addressList);

        employeeRepository.save(employee);
        return "employee data saved successfully...";
    }

    @Override
    public String deleteEmployee(int id) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employeeRepository.delete(employee);

            return "employee data deleted successfully...";
        }

        return "no employee found with this id:" + id;

    }

    @Override
    public String matchEmploye(String name, String email) {

        Optional<Employee> optionalEmployee = employeeRepository.findByName(name);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();

            if (employee.getEmail().equals(email)) {
                return "match successful...";
            }

            else {
                return "wrong email...";
            }
        }

        return "no user found with this name:" + name;

    }

    @Override
    public Employee findEmployee(int id) {

        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("no employee found with this id:" + id));

    }

    @Override
    public String updateEmployee(EmployeeDto employeeDto, int id) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();

            if (employeeDto.getName() != null) {
                employee.setName(employeeDto.getName());
            }

            if (employeeDto.getAge() != 0) {
                employee.setAge(employeeDto.getAge());
            }

            if (employeeDto.getEmail() != null) {
                employee.setEmail(employeeDto.getEmail());
            }

            if (employeeDto.getSalary() != 0) {
                employee.setSalary(employeeDto.getSalary());
            }

            if (employeeDto.getAddresses() != null) {

                List<Address> addrList = employee.getAddresses();

                for (AddressDto addr : employeeDto.getAddresses()) {
                    Optional<Address> optionalAddress = addressRepository.findById(addr.getId());

                    if (optionalAddress.isPresent()) {

                        Address existingAddress = optionalAddress.get();

                        if (addr.getStreet() != null) {
                            existingAddress.setStreet(addr.getStreet());
                        }

                        if (addr.getCity() != null) {
                            existingAddress.setCity(addr.getCity());
                        }

                        if (addr.getState() != null) {
                            existingAddress.setState(addr.getState());
                        }

                        if (addr.getCountry() != null) {
                            existingAddress.setCountry(addr.getCountry());
                        }

                    }

                    else {
                        Address newAddress = Address.builder()
                                .street(addr.getStreet())
                                .city(addr.getCity())
                                .state(addr.getState())
                                .country(addr.getCountry())
                                .build();

                        addrList.add(newAddress);
                        newAddress.setEmployee(employee);
                    }
                }

                employee.setAddresses(addrList);

            }

            employeeRepository.save(employee);
            return "employee updated with id:" + id;

        }

        return "no employee found with this id:" + id;

    }

}
