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
import vn.edu.iuh.fit.backend.dtos.AddressDTO;
import vn.edu.iuh.fit.backend.dtos.CompanyDTO;
import vn.edu.iuh.fit.backend.dtos.PageResponseDTO;
import vn.edu.iuh.fit.backend.models.Address;
import vn.edu.iuh.fit.backend.repositories.AddressRepository;
import vn.edu.iuh.fit.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.backend.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final AddressRepository addressRepository;
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final AddressMapper addressMapper;

    @Autowired
    public CompanyServiceImpl(AddressRepository addressRepository, CompanyRepository companyRepository, CompanyMapper companyMapper,
                              AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.addressMapper = addressMapper;
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
        if (companyDTO.getAddress() != null) {
            Address aa = addressMapper.toEntity(companyDTO.getAddress());
            System.out.println("Address: " + aa.getCountry());
            addressRepository.save(addressMapper.toEntity(companyDTO.getAddress()));
        }
        return companyMapper.toDTO(companyRepository.save(companyMapper.toEntity(companyDTO)));
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
