/*
 * @ (#) EmailRESTController.java       1.0     03/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.resources;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 03/12/2024
 * @version:    1.0
 */

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.backend.dtos.MailDTO;
import vn.edu.iuh.fit.backend.services.EmailService;

@RestController
@RequestMapping("/api/email")
public class EmailRESTController {

    private final EmailService emailService;

    public EmailRESTController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-interview-invitation")
    public ResponseEntity<?> sendInterviewInvitation(@RequestBody MailDTO mailDTO) {
        try {
            emailService.sendInterviewInvitation(mailDTO.getEmail(), mailDTO.getSubject(), mailDTO.getContent());
            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Email sent failed");
        }
    }
}
