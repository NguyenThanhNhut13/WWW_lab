/*
 * @ (#) CompanyServiceImpl.java       1.0     08/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services.impl;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 08/11/2024
 * @version:    1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.converters.AddressMapper;
import vn.edu.iuh.fit.backend.converters.CompanyMapper;
import vn.edu.iuh.fit.backend.converters.UserMapper;
import vn.edu.iuh.fit.backend.dtos.CompanyDTO;
import vn.edu.iuh.fit.backend.dtos.PageResponseDTO;
import vn.edu.iuh.fit.backend.models.Address;
import vn.edu.iuh.fit.backend.models.Company;
import vn.edu.iuh.fit.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.backend.repositories.UserRepository;
import vn.edu.iuh.fit.backend.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final AddressRepository addressRepository;
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final AddressMapper addressMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public CompanyServiceImpl(AddressRepository addressRepository, CompanyRepository companyRepository, CompanyMapper companyMapper, AddressMapper addressMapper, UserRepository userRepository, UserMapper userMapper) {
        this.addressRepository = addressRepository;
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.addressMapper = addressMapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public PageResponseDTO<CompanyDTO> getAllCompany(Pageable pageable) {
        Page<CompanyDTO> companies = companyRepository.findAll(pageable).map(companyMapper::toDTO);
        return new PageResponseDTO<>(companies);
    }

    @Override
    public CompanyDTO getCompanyById(Long id) {
        return companyRepository.findById(id).map(companyMapper::toDTO).orElse(null);
    }

    @Override
    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
        Company company = companyMapper.toEntity(companyDTO);
        if (companyDTO.getUserId() != null) {
            company.setUser(userRepository.findById(companyDTO.getUserId()).orElse(null));
        }
        if (companyDTO.getAddress() != null) {
            Address address = addressRepository.save(addressMapper.toEntity(companyDTO.getAddress()));
            company.setAddress(address);
        }
        System.out.println("Company: " + company);
        return companyMapper.toDTO(companyRepository.save(company));
    }

    @Override
    public CompanyDTO updateCompany(CompanyDTO companyDTO) {
        if (companyDTO.getAddress() != null) {
            addressRepository.save(addressMapper.toEntity(companyDTO.getAddress()));
        }
        return companyMapper.toDTO(companyRepository.save(companyMapper.toEntity(companyDTO)));
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
