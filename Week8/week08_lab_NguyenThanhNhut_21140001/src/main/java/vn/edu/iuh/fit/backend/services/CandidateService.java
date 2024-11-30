/*
 * @ (#) CandidateService.java       1.0     01/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 01/12/2024
 * @version:    1.0
 */

import vn.edu.iuh.fit.backend.dtos.CandidateDTO;

public interface CandidateService {
    CandidateDTO findByUsername(String username);
}
