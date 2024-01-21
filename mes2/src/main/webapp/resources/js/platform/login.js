/* Created by Tivotal */

let showBtn = document.querySelector("#show-form");
let home = document.querySelector(".home");
let formClose = document.querySelector(".close-form");
let passHide = document.querySelectorAll(".pass-hide");
let signup = document.querySelector(".signup");
let formContainer = document.querySelector(".form-container");
let login = document.querySelector(".login");
var result = "${result}";

console.log('result: ' + result);

showBtn.addEventListener("click", () => {
  home.classList.add("show");
});

formClose.addEventListener("click", () => {
  home.classList.remove("show");
});

passHide.forEach((icon) => {
  icon.addEventListener("click", () => {
    //getting password input for each icon
    let passInput = icon.parentElement.querySelector("input");
    if (passInput.type === "password") {
      //changing type to text and updating icon
      passInput.type = "text";
      icon.classList.replace("fa-eye-slash", "fa-eye");
    } else {
      //changing type to password and updating icon
      passInput.type = "password";
      icon.classList.replace("fa-eye", "fa-eye-slash");
    }
  });
});

signup.addEventListener("click", () => {
  formContainer.classList.add("active");
});

login.addEventListener("click", () => {
  formContainer.classList.remove("active");
});



if(result == "loginFail") {
	Swal.fire({
		text: "회사 정보를 확인하세요",
		confirmButtonColor: "#577D71",
		icon: "warning"
	})
	.then(function(){
		location.reload();
	});
}
