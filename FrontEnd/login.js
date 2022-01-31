let usernameBox = document.getElementById("username");
let passwordBox = document.getElementById("password");
let loginBtn = document.getElementById("loginBtn");

const url = "http://localhost:7002/"

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

    console.log("User and Pass:" + user.username + " | " + user.password);

    let response = await fetch(
        url+"login",
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