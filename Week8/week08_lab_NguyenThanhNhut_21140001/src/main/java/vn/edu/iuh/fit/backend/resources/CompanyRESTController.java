/*
 * @ (#) CompanyRESTController.java       1.0     09/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.resources;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 09/11/2024
 * @version:    1.0
 */

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.backend.dtos.CompanyDTO;
import vn.edu.iuh.fit.backend.services.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyRESTController {

    private final CompanyService companyService;

    @Autowired
    public CompanyRESTController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CompanyDTO> saveCompany(@RequestBody CompanyDTO companyDTO) {
        CompanyDTO company = companyService.saveCompany(companyDTO);
        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }
}
