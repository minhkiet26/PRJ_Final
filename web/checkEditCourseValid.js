document.addEventListener("DOMContentLoaded", function () {

  
    const Validator = {
        // Kiểm tra số nguyên dương (Dùng cho ID, Total Lectures, Number Enrolled)
        isInt: (value) => /^\d+$/.test(value),

        // Kiểm tra số thực/số thập phân dương (Dùng cho Tuition Fee: VD 2500000.00)
        isFloat: (value) => /^\d+(\.\d+)?$/.test(value),

        // Kiểm tra ngày: Chấp nhận YYYY-MM-DD hoặc YYYY-MM-DD HH:mm:ss
        isDate: (value) => /^\d{4}-\d{2}-\d{2}/.test(value),

        // Kiểm tra khung giờ học: HH:mm - HH:mm (VD: 19:00 - 21:00)
        isTimeRange: (value) => /^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]\s*-\s*(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/.test(value),

        // Kiểm tra file ảnh: kết thúc bằng đuôi ảnh hợp lệ, có thể chứa tham số phía sau (VD: .png?v=1)
        isImageUrl: (value) => /\.(png|jpe?g|gif|webp)(\?.*)?$/i.test(value)
    };

    document.addEventListener('submit', function (event) {
        const form = event.target;

        // Chỉ kiểm tra form có action là 'UpdateCourseController'
        if (form.tagName === 'FORM' && form.getAttribute('action') === 'UpdateCourseController') {
            try {
                let errors = [];

                // Hàm hỗ trợ lấy giá trị input an toàn (tránh lỗi null nếu field bị thiếu)
                const getValue = (name) => {
                    const input = form.querySelector(`input[name="${name}"]`);
                    return input ? input.value.trim() : "";
                };

                // Lấy toàn bộ 12 trường dữ liệu
                const courseId = getValue("CourseID");
                const courseName = getValue("CourseName");
                const tuitionFee = getValue("TuitionFee");
                const teacherId = getValue("TeacherID");
                const studyTime = getValue("StudyTime");
                const schedule = getValue("Schedule");
                const startDate = getValue("StartDate");
                const totalLectures = getValue("TotalLectures");
                const numberEnrolled = getValue("NumberEnrolled");
                const status = getValue("Status");
                const imageUrl = getValue("ImageURL");
                const description = getValue("Description"); // Có thể là textarea, nếu là textarea đổi input -> textarea trong hàm getValue

                if (courseId !== "" && (!Validator.isInt(courseId) || parseInt(courseId) <= 0)) {
                    errors.push("- Course ID không hợp lệ (Phải là số nguyên dương).");
                }

                if (courseName === "")
                    errors.push("- Tên khóa học (Course Name) không được để trống.");

                if (tuitionFee === "") {
                    errors.push("- Học phí (Tuition Fee) không được để trống.");
} else if (!Validator.isFloat(tuitionFee) || parseFloat(tuitionFee) < 0) {
                    errors.push("- Học phí phải là số hợp lệ và lớn hơn hoặc bằng 0.");
                }

                if (teacherId === "") {
                    errors.push("- ID Giáo viên (Teacher ID) không được để trống.");
                } else if (!Validator.isInt(teacherId) || parseInt(teacherId) <= 0) {
                    errors.push("- ID Giáo viên phải là số nguyên dương.");
                }

                if (studyTime === "") {
                    errors.push("- Thời gian học (Study Time) không được để trống.");
                } else if (!Validator.isTimeRange(studyTime)) {
                    errors.push("- Thời gian học sai định dạng (Ví dụ chuẩn: 19:00 - 21:00).");
                }
                
                if (schedule === "")
                    errors.push("- Lịch học (Schedule) không được để trống.");

                if (startDate === "") {
                    errors.push("- Ngày bắt đầu (Start Date) không được để trống.");
                } else if (!Validator.isDate(startDate)) {
                    errors.push("- Ngày bắt đầu sai định dạng YYYY-MM-DD.");
                }

                if (totalLectures === "") {
                    errors.push("- Tổng số buổi học không được để trống.");
                } else if (!Validator.isInt(totalLectures) || parseInt(totalLectures) <= 0) {
                    errors.push("- Tổng số buổi học phải là số nguyên lớn hơn 0.");
                }

                if (numberEnrolled === "") {
                    errors.push("- Số lượng học viên đăng ký không được để trống.");
                } else if (!Validator.isInt(numberEnrolled) || parseInt(numberEnrolled) < 0) {
                    errors.push("- Số lượng học viên đăng ký phải là số nguyên >= 0.");
                }

                if (status === "")
                    errors.push("- Trạng thái (Status) không được để trống.");

                if (imageUrl === "") {
                    errors.push("- Đường dẫn ảnh (Image URL) không được để trống.");
                } else if (!Validator.isImageUrl(imageUrl)) {
                    errors.push("- Đường dẫn ảnh phải có đuôi hợp lệ (.png, .jpg, .jpeg, .gif).");
                }

                if (description === "")
                    errors.push("- Mô tả (Description) không được để trống.");

                if (errors.length > 0) {
                    event.preventDefault(); // Chặn hành vi gửi form lên server
                    alert("VUI LÒNG SỬA CÁC LỖI SAU TRƯỚC KHI LƯU:\n\n" + errors.join("\n"));
                }

            } catch (error) {
                event.preventDefault();
console.error("Lỗi quá trình kiểm tra JS:", error);
                alert("Đã xảy ra lỗi hệ thống khi kiểm tra form. Vui lòng mở Console (F12) để xem chi tiết.");
            }
        }
    });
});