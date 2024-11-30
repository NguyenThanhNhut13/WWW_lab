/*
 * @ (#) CandidateRESTController.java       1.0     01/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.resources;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 01/12/2024
 * @version:    1.0
 */

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.services.CandidateService;

@RestController
@RequestMapping("/api/candidates")
public class CandidateRESTController {

    private final CandidateService candidateService;

    public CandidateRESTController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<?> findByUsername(@RequestParam("username") String username) {
        try {
            return ResponseEntity.ok(candidateService.findByUsername(username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
