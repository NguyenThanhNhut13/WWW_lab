/*
 * @ (#) EmailService.java       1.0     03/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.services;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 03/12/2024
 * @version:    1.0
 */

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendInterviewInvitation(String email, String subject, String content) throws MessagingException;
}
