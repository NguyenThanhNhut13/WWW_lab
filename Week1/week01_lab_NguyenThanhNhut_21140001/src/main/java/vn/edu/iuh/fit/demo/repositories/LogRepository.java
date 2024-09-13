/*
 * @ (#) LogRepository.java       1.0     13/09/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.demo.repositories;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 13/09/2024
 * @version:    1.0
 */

import vn.edu.iuh.fit.demo.entities.Log;

import java.util.List;

public interface LogRepository {
    public List<Log> findAll();
    public Log findById(String id);
    public boolean exists(String id);
    public void save(Log log);
    public Log update(Log log);
    public boolean delete(Log log);


}
