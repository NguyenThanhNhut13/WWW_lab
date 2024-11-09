/*
 * @ (#) CompanyService.java       1.0     08/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 08/11/2024
 * @version:    1.0
 */

import org.springframework.data.domain.Pageable;
import vn.edu.iuh.fit.backend.dtos.CompanyDTO;
import vn.edu.iuh.fit.backend.dtos.PageResponseDTO;

public interface CompanyService {
    PageResponseDTO<CompanyDTO> getAllCompany(Pageable pageable);
    CompanyDTO getCompanyById(Long id);
    CompanyDTO saveCompany(CompanyDTO companyDTO);
    CompanyDTO updateCompany(CompanyDTO companyDTO);
    void deleteCompany(Long id);
}
