const addCourseForm = document.querySelector('form.signup');

if (addCourseForm) {
    addCourseForm.addEventListener('submit', function (event) {
        try {
            checkCourseName();
            checkTuitionFee();
            checkTeacherID();
            checkStudyTime();
            checkSchedule();
            checkStartDate();
            checkTotalLectures();

            // Kiểm tra có thuộc tính nào người dùng nhập vào bị lỗi không
            if (!this.checkValidity()) {
                event.preventDefault(); // Chặn submit
            }
        } catch (error) {
            // Nếu JS có lỗi ẩn bên trong các hàm check, in ra console và CŨNG CHẶN submit để an toàn
            console.error("Lỗi JS trong quá trình validate Course:", error);
            event.preventDefault();
        }
    });
}

// --- HÀM HỖ TRỢ (Rút gọn code lặp lại) ---
function getElements(name) {
    // Chỉ tìm input nằm trong form.signup (Modal Thêm Mới)
    const input = document.querySelector(`form.signup input[name="${name}"]`);
    const errorMsg = document.getElementById(`err-${name}`);
    return {input, errorMsg};
}

function setError(input, errorMsg, message) {
    if (message) {
        errorMsg.innerText = message;
        input.setCustomValidity("invalid");
    } else {
        errorMsg.innerText = "";
        input.setCustomValidity("");
    }
}
// ----------------------------------------

function checkCourseName() {
    const {input, errorMsg} = getElements('CourseName');
    if (!input || !errorMsg)
        return;

    const value = input.value.trim();
    if (value === "")
        setError(input, errorMsg, "Tên khóa học không được để trống!");
    else if (!validateLength(value, 1, 100))
        setError(input, errorMsg, "Tên quá dài (tối đa 100 ký tự)!");
    else
        setError(input, errorMsg, "");
}

function checkTuitionFee() {
    const {input, errorMsg} = getElements('TuitionFee');
    if (!input || !errorMsg)
        return;

    const value = input.value.trim();
    if (value === "")
        setError(input, errorMsg, "Học phí không được để trống!");
    else if (!validateNumber(value))
        setError(input, errorMsg, "Học phí phải là số hợp lệ!");
    else
        setError(input, errorMsg, "");
}

function checkTeacherID() {
    const {input, errorMsg} = getElements('TeacherID');
    if (!input || !errorMsg)
        return;

    const value = input.value.trim();
    if (value === "")
        setError(input, errorMsg, "Teacher ID không được để trống!");
    else if (!validateInt(value))
        setError(input, errorMsg, "Teacher ID phải là số nguyên dương!");
    else
        setError(input, errorMsg, "");
}

function checkStudyTime() {
    const {input, errorMsg} = getElements('StudyTime');
    if (!input || !errorMsg)
        return;

    const value = input.value.trim();
    if (value === "")
        setError(input, errorMsg, "Thời gian học không được để trống!");
    else
        setError(input, errorMsg, "");
}

function checkSchedule() {
    const {input, errorMsg} = getElements('Schedule');
    if (!input || !errorMsg)
        return;

    const value = input.value.trim();
    if (value === "")
        setError(input, errorMsg, "Lịch học không được để trống!");
    else
        setError(input, errorMsg, "");
}

function checkStartDate() {
    const {input, errorMsg} = getElements('StartDate');
    if (!input || !errorMsg)
        return;

    const value = input.value.trim();
    if (value === "")
        setError(input, errorMsg, "Ngày bắt đầu không được để trống!");
    else if (!validateDate(value))
        setError(input, errorMsg, "Sai định dạng ngày (YYYY-MM-DD)!");
    else
        setError(input, errorMsg, "");
}

function checkTotalLectures() {
    const {input, errorMsg} = getElements('TotalLectures');
    if (!input || !errorMsg)
        return;

    const value = input.value.trim();
    if (value === "")
        setError(input, errorMsg, "Tổng số buổi học không được để trống!");
    else if (!validateInt(value))
        setError(input, errorMsg, "Số buổi học phải là số nguyên!");
    else
        setError(input, errorMsg, "");
}


// --- CÁC HÀM REGEX VALIDATE ---
function validateLength(value, min, max) {
    const regex = new RegExp(`^.{${min},${max}}$`);
    return regex.test(value);
}

function validateInt(value) {
    const regex = /^\d+$/; // Chỉ chấp nhận số
    return regex.test(value);
}

function validateNumber(value) {
    const regex = /^\d+(\.\d+)?$/; // Chấp nhận số nguyên hoặc số thập phân
    return regex.test(value);
}

function validateDate(value) {
    const regex = /^\d{4}-\d{2}-\d{2}$/; // Định dạng YYYY-MM-DD
    return regex.test(value);
}

// --- GẮN SỰ KIỆN LẮNG NGHE ---
document.addEventListener("DOMContentLoaded", function () {
    const inputs = [
        {name: "CourseName", func: checkCourseName},
        {name: "TuitionFee", func: checkTuitionFee},
        {name: "TeacherID", func: checkTeacherID},
        {name: "StudyTime", func: checkStudyTime},
        {name: "Schedule", func: checkSchedule},
        {name: "StartDate", func: checkStartDate},
        {name: "TotalLectures", func: checkTotalLectures},
    ];

    inputs.forEach(item => {
        // Gắn sự kiện chuẩn xác vào các input của form.signup
        const inputField = document.querySelector(`form.signup input[name="${item.name}"]`);
        if (inputField) {
            inputField.addEventListener("input", item.func);
            inputField.addEventListener("blur", item.func);
        }
    });
});