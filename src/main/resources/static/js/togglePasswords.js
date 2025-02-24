
document.addEventListener("DOMContentLoaded", function () {
    function togglePasswordVisibility(inputId, iconId) {
        const input = document.getElementById(inputId);
        const icon = document.getElementById(iconId);

        icon.addEventListener("click", function () {
            if (input.type === "password") {
                input.type = "text";
                icon.classList.remove("bi-eye-slash");
                icon.classList.add("bi-eye");
            } else {
                input.type = "password";
                icon.classList.remove("bi-eye");
                icon.classList.add("bi-eye-slash");
            }
        });
    }

    togglePasswordVisibility("password", "togglePassword");
    togglePasswordVisibility("confirmPassword", "toggleConfirmPassword");
});