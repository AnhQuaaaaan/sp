async function dangnhap(event) {
    event.preventDefault();
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const loi = document.getElementById("loi");

    fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ username, password })
    })
        .then(async response => {
            if (!response.ok) {
                return await response.text().then(errorText => {
                    loi.innerText = errorText;
                    throw new Error(errorText);
                });
            }
            return response.json();
        })
        .then(userDto => {
            localStorage.setItem('token', JSON.stringify(userDto));
            window.location.href = "index.html";
        })
        .catch(error => {
            console.error(error);
        });
}
async function dangky(event) {
    event.preventDefault();
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const repassword = document.getElementById("repassword").value;
    const loi=document.getElementById("loi");
    if (password !== repassword) {
        loi.innerText="Mật khẩu không trùng khớp";
        return;
    }
    fetch("http://localhost:8080/api/auth/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ username, password })
    })
        .then(async response => {
            if (!response.ok) {
                return await response.text().then(errorText => {
                    loi.innerText = errorText;
                    throw new Error(errorText);
                });
            }
            return response.json();
        })
        .then(userDto => {
            window.location.href = "dang-nhap.html";
        })
        .catch(error => {
            console.error(error);
        });
}
function init(){
    const username = localStorage.getItem("token");
    const userData = JSON.parse(username);

    if (username) {
        document.getElementById("dangnhap").textContent = "Hello " + userData.username +"!!!!";
        const dx=document.getElementById("dangky");
        dx.innerHTML = "<li class='dang-nhap'><a href='dang-ky.html'>Đăng xuất</a></li>";
        dx.addEventListener("click",dangxuat);
    }
}
async function dangxuat(){
    localStorage.removeItem("token");
    window.location.href = "index.html";
}
window.onload =init();

