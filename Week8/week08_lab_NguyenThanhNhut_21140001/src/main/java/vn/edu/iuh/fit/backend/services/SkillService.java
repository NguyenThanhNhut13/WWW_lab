/*
 * @ (#) SkilllService.java       1.0     13/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 13/11/2024
 * @version:    1.0
 */

import vn.edu.iuh.fit.backend.dtos.SkillDTO;

import java.util.Set;

public interface SkillService {
    Set<SkillDTO> getAllSkills();
    SkillDTO save(SkillDTO skillDTO);
    SkillDTO getSkillById(Long id);
}
