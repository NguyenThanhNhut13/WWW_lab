/*
 * @ (#) MailDTO.java       1.0     03/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.dtos;

import lombok.*;

/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 03/12/2024
 * @version:    1.0
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MailDTO {
    private String email;
    private String subject;
    private String content;
}
