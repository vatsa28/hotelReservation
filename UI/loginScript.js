function myJavascriptFunction() {
    console.log("Hello")
    var usernameInput = document.getElementById("userName");
    var passwordInput = document.getElementById("password");
    var usernameValue = usernameInput.value;
    var passwordValue = passwordInput.value;
    console.log(usernameValue);
    console.log(passwordValue);
    sessionStorage.setItem("username", usernameValue);
    sessionStorage.setItem("password", passwordValue);
    fetch('http://localhost:8080/verifyUser?userName='+usernameValue+'&password='+passwordValue,{
    method: "GET", // *GET, POST, PUT, DELETE, etc.
    headers: {
        "Content-Type": "application/json"
    },
    })
    .then(function(response){
        console.log("abcdef");
        if (response.ok) {
            response.json().then(data=>{
                if(data.userType == "Customer")
                    window.location.assign("./homepage.html")
                else
                    window.location.assign("./manager.html")
            })
        } else {
            response.text().then(data => alert(data))
        }
    })
    .catch(error => {
        alert(error.message);
    });
}