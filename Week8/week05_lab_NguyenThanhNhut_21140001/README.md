# Dự án week05_lab_NguyenThanhNhut_21140001

## Thông tin sinh viên
#### Trường Đại học Công nghiệp TP.HCM
#### Họ và tên: Nguyễn Thanh Nhứt
#### Mã sinh viên: 21140001
#### Giảng viên hướng dẫn: Thầy Võ Văn Hải
#### File báo cáo: [Tại đây]()
#### File presentation: [Tại đây]()

## Giới thiệu

Dự án này là một nền tảng tuyển dụng trực tuyến, nơi các công ty có thể đăng tin tuyển dụng và ứng viên có thể tìm kiếm và ứng tuyển vào các công việc phù hợp với kỹ năng và kinh nghiệm của mình.

## Công nghệ sử dụng

- **Frontend**: HTML, CSS, JavaScript, Thymeleaf, Bootstrap 5
- **Backend**: Java, Spring Boot, Spring Data JPA, Spring Security, Spring MVC
- **Database**: MariaDB
- **Build Tool**: Gradle
- **Data Analysis**: Python, Pandas, Scikit-learn, TensorFlow
- **Additional Library**: Lombok, MapStruct, vv.

## Các tính năng chính

### Guest
- **Tìm kiếm công việc**: Tìm kiếm dựa trên các tiêu chí như kỹ năng, vị trí, mức lương.
- **Xem chi tiết công việc**: Xem thông tin cụ thể về từng công việc.

### Company
- **Đăng ký tài khoản công ty**: Tạo tài khoản mới dành cho công ty.
- **Đăng tin tuyển dụng**: Đăng tải công việc mới.
- **Quản lý công việc**: Xem, chỉnh sửa và xóa công việc đã đăng.
- **Quản lý ứng viên**: Xem danh sách ứng viên ứng tuyển.
- **Gửi thông báo**: Gửi email mời phỏng vấn cho ứng viên.
- **Gợi ý ứng viên**: Hỗ trợ tìm kiếm ứng viên phù hợp với yêu cầu công việc.

### Candidate
- **Tìm kiếm công việc**: Tìm việc dựa trên kỹ năng, vị trí, mức lương, v.v.
- **Ứng tuyển công việc**: Nộp đơn ứng tuyển trực tuyến.
- **Gợi ý công việc**: Đề xuất công việc phù hợp dựa trên kỹ năng và kinh nghiệm.
- **Gợi ý kỹ năng cần thiết**: Đề xuất kỹ năng cần học thêm để đáp ứng yêu cầu công việc.
- **Quản lý hồ sơ**: Cập nhật hồ sơ cá nhân, kỹ năng, kinh nghiệm làm việc.

### Admin
- Có quyền truy cập và quản lý tất cả các tính năng của **Company** và **Candidate**.

## Hướng dẫn cài đặt

1. **Clone dự án**:
    ```bash
    git clone https://github.com/NguyenThanhNhut13/week08_lab_NguyenThanhNhut_21140001.git
    ```

2. **Chuyển vào thư mục dự án**:
    ```bash
    cd week05_lab_NguyenThanhNhut_21140001
    ```

3. **Cấu hình cơ sở dữ liệu và email**:
    - Mở file `application.properties` và cập nhật thông tin:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/works
      spring.datasource.username=your_username
      spring.datasource.password=your_password

      spring.mail.host=smtp.gmail.com
      spring.mail.port=587
      spring.mail.username=your_email
      spring.mail.password=your_password
      spring.mail.properties.mail.smtp.auth=true
      spring.mail.properties.mail.smtp.starttls.enable=true
      ```

4. **Chạy ứng dụng**:
    - Chạy API Python:
      ```bash
      cd python-api
      python app.py
      ```
    - Chạy backend Spring Boot:
      ```bash
      ./gradlew bootRun
      ```

5. **Truy cập ứng dụng**: Mở trình duyệt và truy cập `http://localhost:8080`.

## Lưu ý trước khi chạy ứng dụng

- **Import dữ liệu**: Tải và import file `works.sql` từ [đây](https://github.com/NguyenThanhNhut13/WWW_lab/blob/main/Week8/week05_lab_NguyenThanhNhut_21140001/src/main/resources/data.sql).
- **Train model gợi ý công việc**:
  Nếu không tìm thấy model, hãy chạy file `job_recommendation_model.py` trong thư mục `python-api/models`:
  ```bash
  python job_recommendation_model.py
  ```

## Demo

### Tài khoản mẫu
- **Admin**:
    - Username: `admin`
    - Password: `admin`
- **Company**:
    - Username: `company`
    - Password: `company`
- **Candidate**:
    - Username: `met`
    - Password: `met`

## Hệ thống đăng nhập phân quyền với Spring Security
- Role COMPANY: dành cho công ty cho phép đăng tin tuyển dụng, quản lý công việc, quản lý ứng viên, gửi thông báo, gợi ý ứng viên.
- Role CANDIDATE: dành cho ứng viên cho phép tìm kiếm công việc, ứng tuyển công việc, gợi ý công việc, gợi ý kỹ năng cần thiết, quản lý hồ sơ.
- Role ADMIN: dành cho quản trị viên bao gồm tất cả các quyền của COMPANY và CANDIDATE.
- Role USER: dành cho những người dùng đã đăng ký nhưng chưa được cấp quyền.
- File config: Xem thêm tại [đây](https://github.com/NguyenThanhNhut13/WWW_lab/blob/main/Week8/week05_lab_NguyenThanhNhut_21140001/src/main/java/vn/edu/iuh/fit/backend/security/WebSecurityConfig.java)

## Demo đăng nhập

## Demo chức năng cho Guest - Không cần đăng nhập 
#### 1. Trang chủ
Sau khi truy cập vào url: http://localhost:8080/, người dùng có thể xem các công việc phổ biến, và các công việc mới nhất.
![Trang chủ](https://github.com/NguyenThanhNhut13/WWW_lab/blob/main/Week8/week05_lab_NguyenThanhNhut_21140001/demo/homepage.png)
#### 2. Xem chi tiết công việc
Khi ấn vào xem chi tiết công việc người dùng có thể xem thông tin chi tiết về công việc
![Trang chi tiết công việc](https://github.com/NguyenThanhNhut13/WWW_lab/blob/main/Week8/week05_lab_NguyenThanhNhut_21140001/demo/job-detail.gif)

## Demo chức năng dành cho ứng viên
#### 1. Trang chủ - Trang chi tiết công việc
- Nếu là người dùng mới đăng ký tài khoản (chưa cập nhật thông tin) hoặc chưa từng ứng tuyển thì hệ thống sẽ gợi ý các công việc phổ biến như người dùng khách.
![Trang chủ](https://github.com/NguyenThanhNhut13/WWW_lab/blob/main/Week8/week05_lab_NguyenThanhNhut_21140001/demo/homepage-new-user.png)

- Nếu người dùng đã cập nhật thông tin hoặc đã từng ứng tuyển thì hệ thống sẽ gợi ý các job phù hợp dựa vào model đã được huấn luyện sẵn bằng TensorFlow.
![Trang chi tiết công việc](https://github.com/NguyenThanhNhut13/WWW_lab/blob/main/Week8/week05_lab_NguyenThanhNhut_21140001/demo/recommend-job.gif)

### 2. Gợi ý kỹ năng cần thiết
-  Khi người dùng xem chi tiết công việc hệ thống sẽ gợi ý các Skill phù hợp với công việc mà người dùng nên học.
![Gợi ý kỹ năng](https://github.com/NguyenThanhNhut13/WWW_lab/blob/main/Week8/week05_lab_NguyenThanhNhut_21140001/demo/recommend-skill-new-user.gif)

- Nếu người dùng đã cập nhật thông tin hoặc đã từng ứng tuyển thì hệ thống sẽ hiển thị thêm thông tin kỹ năng của họ để có cái nhìn tổng quát hơn.
![Gợi ý kỹ năng](https://github.com/NguyenThanhNhut13/WWW_lab/blob/main/Week8/week05_lab_NguyenThanhNhut_21140001/demo/recommend-skill-old-user.gif)

### 3. Ứng tuyển - quản lý thông tin
- Khi ứng tuyển, nếu là người dùng mới thì hệ thống sẽ hiện form ứng tuyển yêu cầu cung cấp thông tin cá nhân, kỹ năng, kinh nghiệm làm việc.
![Ứng tuyển](https://github.com/NguyenThanhNhut13/WWW_lab/blob/main/Week8/week05_lab_NguyenThanhNhut_21140001/demo/apply-job-new-user.gif)

- Nếu người dùng đã có thông tin thì hệ thống sẽ tự điền thông tin và cho phép người dùng cập nhật thông tin.
![Ứng tuyển](https://github.com/NguyenThanhNhut13/WWW_lab/blob/main/Week8/week05_lab_NguyenThanhNhut_21140001/demo/apply-job-old-user.gif)

## Demo chức năng dành cho công ty tuyển dụng
#### 1. Đăng ký tài khoản công ty
- Nếu người dùng mới muốn đăng tuyển thì phải tạo tài khoản công ty. Sau khi tạo xong thì họ sẽ được chuyển hướng đến trang quản lý dành cho công ty. Nếu người dùng đã có tài khoản công ty thì sẽ được tự động chuyển hướng sau khi đăng nhập.
![Đăng ký tài khoản công ty](https://github.com/NguyenThanhNhut13/WWW_lab/blob/main/Week8/week05_lab_NguyenThanhNhut_21140001/demo/register-company.gif)

### 2. Đăng tin tuyển dụng
- Người dùng có thể đăng tin tuyển dụng với các thông tin và kỹ năng yêu cầu.
![Đăng tin tuyển dụng](https://github.com/NguyenThanhNhut13/WWW_lab/blob/main/Week8/week05_lab_NguyenThanhNhut_21140001/demo/post-job.gif)

### 3. Quản lý công việc
- Người dùng có thể quản lý các công việc đã đăng tuyển.
![Quản lý job](https://github.com/NguyenThanhNhut13/WWW_lab/blob/main/Week8/week05_lab_NguyenThanhNhut_21140001/demo/job-management.png)

### 3. Quản lý ứng viên
- Sau khi đăng tuyển người dùng có thể quản lý các ứng viên đã ứng tuyển như xem thông tin, tìm kiếm, v.v.
![Quản lý ứng viên](https://github.com/NguyenThanhNhut13/WWW_lab/blob/main/Week8/week05_lab_NguyenThanhNhut_21140001/demo/candidate-management.gif)

### 4. Gửi thông báo cho ứng viên
- Người dùng có thể gửi email mời ứng viên đến phỏng vấn với nội dung có sẵn và người dùng có thể tùy chỉnh nội dung.
![Gửi thông báo cho ứng viên](https://github.com/NguyenThanhNhut13/WWW_lab/blob/main/Week8/week05_lab_NguyenThanhNhut_21140001/demo/send-email.gif)

### 5. Gợi ý ứng viên
- Hệ thống sẽ gợi ý các ứng viên phù hợp dựa vào mức độ phù hợp giữa công việc và kỹ năng của ứng viên.
![Gợi ý ứng viên](https://github.com/NguyenThanhNhut13/WWW_lab/blob/main/Week8/week05_lab_NguyenThanhNhut_21140001/demo/recommend-candidate.png)

#### Ngoài ra còn có các chức năng khác như: cập nhật thông tin công ty, đăng xuất, v.v.

## Tài liệu tham khảo

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Gradle Documentation](https://docs.gradle.org/current/userguide/userguide.html)

## Tác giả, liên hệ
- Facebook: [Nguyễn Thanh Nhứt](https://www.facebook.com/nguyenthanhnhut13)
- Email: thanhnhutcu@gmail.com