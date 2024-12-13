/*
 * @ (#) Skill.java       1.0     03/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.repositories;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 03/11/2024
 * @version:    1.0
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.models.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
}
