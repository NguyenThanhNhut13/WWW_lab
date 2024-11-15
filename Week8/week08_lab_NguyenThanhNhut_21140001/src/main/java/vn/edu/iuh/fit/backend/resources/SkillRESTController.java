/*
 * @ (#) SkillRESTController.java       1.0     13/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.resources;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 13/11/2024
 * @version:    1.0
 */

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.dtos.SkillDTO;
import vn.edu.iuh.fit.backend.services.SkillService;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/skills")
public class SkillRESTController {

    private final SkillService skillService;

    public SkillRESTController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public ResponseEntity<Set<SkillDTO>> getAllSkills() {
        try {
            return ResponseEntity.ok(skillService.getAllSkills());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillDTO> getSkillById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(skillService.getSkillById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<SkillDTO> save(@RequestBody SkillDTO skillDTO) {
        try {
            return ResponseEntity.ok(skillService.save(skillDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /*
        * @description: Save all skills
        * @param: Set<SkillDTO> skillDTOs
        * @return: ResponseEntity<Set<SkillDTO>>
     */
    @PostMapping("/all")
    public ResponseEntity<Set<SkillDTO>> saveAll(@RequestBody Set<SkillDTO> skillDTOs) {
        try {
            Set<SkillDTO> savedSkills = skillDTOs.stream()
                    .map(skillService::save)
                    .collect(Collectors.toSet());
            return ResponseEntity.ok(savedSkills);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
