
function toggleFullName(show) {
    const field = document.getElementById('fullNameField');
    const input = field.querySelector('input');

    if (show) {
        field.classList.remove('hidden');
        field.classList.add('show');
        input.required = true;
    }
}

const signupForm = document.querySelector('form.signup');

signupForm.addEventListener('submit', function (event) {
    checkEmail();
    checkPassword();
    checkValidatePasswords();
    checkRole();
    checkName();

    // Kiểm tra có thuộc tính nào người dùng nhập vào bị lỗi không
    if (!this.checkValidity()) {
        event.preventDefault(); // chặn submit
    }
});

function checkPassword() {
    const password = document.querySelector('input[name="PASSWORD"]');
    const errorMsg = document.getElementById('error-msg-password');
    const value = password.value.trim();
    if (password.value === "") {
        errorMsg.innerText = "Password không được để trống!";
        password.setCustomValidity("invalid");
    } else if (!validateLength(value, 1, 50)) {
        errorMsg.innerText = "Password quá dài(tối da 50 ký tự)!";
        password.setCustomValidity("invalid");
    } else {
        errorMsg.innerText = "";
        password.setCustomValidity("");
    }
}

function checkValidatePasswords() {
    const password = document.querySelector('input[name="PASSWORD"]');
    const confirm = document.querySelector('input[name="CONFIRM_PASSWORD"]');
    const errorMsg = document.getElementById('error-msg-confirmpassword');

    if (confirm.value === "") {
        //viết lỗi để gửi
        errorMsg.innerText = "Không được để trống!";
        confirm.setCustomValidity("invalid");
    } else if (confirm.value !== password.value) {
        errorMsg.innerText = "Mật khẩu xác nhận không khớp!";
        confirm.setCustomValidity("invalid");
    } else {
        errorMsg.innerText = "";
        confirm.setCustomValidity("");
    }
}

function checkEmail() {
    const serverError = document.getElementById('server-error-email');
    const emailInput = document.querySelector('input[name="EMAIL"]');
    const errorMsg = document.getElementById('error-msg-email');
    const emailValue = emailInput.value.trim();
    if(serverError){
        serverError.innerText = "";
    }

    if (emailValue === "") {
        serverError.innerText = "";
        errorMsg.innerText = "Email không được để trống!";
        emailInput.setCustomValidity("invalid");
    } else if (!validateEmail(emailValue)) {
        serverError.innerText = "";
        errorMsg.innerText = "Định dạng email không hợp lệ";
        emailInput.setCustomValidity("invalid");
    } else {
        serverError.innerText = "";
        errorMsg.innerText = "";
        emailInput.setCustomValidity("");
    }
}


function checkName() {
    const nameField = document.getElementById('fullNameField');
    const nameInput = document.querySelector('input[name="FULLNAME"]');
    const errorMsg = document.getElementById('error-msg-name');

    // Chỉ kiểm tra nếu ô tên đang hiển thị
    if (!nameField.classList.contains('hidden')) {
        const value = nameInput.value.trim();
        if (value === "") {
            errorMsg.innerText = "Tên không được để trống!";
            nameInput.setCustomValidity("invalid");
        } else if (!validateLength(value, 1, 100)) {
            errorMsg.innerText = "Tên quá dài(tối đa 100 ký tự)!";
            nameInput.setCustomValidity("invalid");
        } else {
            errorMsg.innerText = "";
            nameInput.setCustomValidity("");
        }
    } else {
        // Nếu ẩn thì mặc định là hợp lệ
        nameInput.setCustomValidity("");
    }
}

function checkRole() {
    const roles = document.getElementsByName("ROLE");
    const errorMsg = document.getElementById('error-msg-role');
    let isChecked = false;

    for (const role of roles) {
        if (role.checked) {
            isChecked = true;
            break;
        }
    }

    if (!isChecked) {
        errorMsg.innerText = "Vui lòng chọn một vai trò!";
        roles[0].setCustomValidity("invalid");
    } else {
        errorMsg.innerText = "";
        roles[0].setCustomValidity("");
    }
}

//----------------------
function validateEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}

function validateLength(value, min, max) {
    // Tạo regex động dựa trên tham số truyền vào
    const regex = new RegExp(`^.{${min},${max}}$`);
    return regex.test(value);
}