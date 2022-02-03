let usernameBox = document.getElementById("username");
let passwordBox = document.getElementById("password");
let loginBtn = document.getElementById("loginBtn");
let login_verify = document.getElementById("username_verify");
let pasword_verify = document.getElementById("password_verify");

//const url = "http://54.176.76.0:7000/" // ec2 URL
const url = "http://localhost:7000/" // local URL

if (sessionStorage.getItem("userSession") != null){
  let user = JSON.parse(sessionStorage.getItem("userSession"));
  if(user.userRoleId != 2){
      window.location.replace(url + "employee.html");
  } else {
    window.location.replace(url + "manager.html");
  }
}  

loginBtn.addEventListener("click", loginFunc);

async function loginFunc(){
    let user = {
        username: usernameBox.value,
        password: passwordBox.value
    }

    

    if (user.username == ""){
      
      login_verify.style.fontSize = "12px";
      login_verify.style.color = "red";
      login_verify.innerHTML = "Invalid Username";
      login_verify.style.fontWeight = "bold";
    }

    if (user.password == ""){
      password_verify.style.fontSize = "12px";
      password_verify.style.color = "red";
      password_verify.innerHTML = "Invalid Password";
      password_verify.style.fontWeight = "bold";      
    }

    console.log("User and Pass:" + user.username + " | " + user.password);

    let response = await fetch(
        url + "login",
        {
          method : "POST",
          body : JSON.stringify(user),
          credentials: "include"
        }
      );
    
      if(response.status===200){
        let user_info = await response.json();
        console.log("User Role ID: " + user_info.userRoleId);
        sessionStorage.setItem("userSession", JSON.stringify(user_info));
        if (user_info.userRoleId == 2) {
            window.location.replace(url + "manager.html");
        } else {
            window.location.replace(url + "employee.html");
        }
      }else{
        console.log("Login unsuccessful. Returned status code of:"+response.status);
      }

}