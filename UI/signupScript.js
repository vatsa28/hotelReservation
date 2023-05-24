function signupFunction(){

    var firstName = document.getElementById("firstname").value;
    var lastName = document.getElementById("lastname").value;
    var username = document.getElementById("username").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    if(document.getElementById("inlineRadio2").checked)
        var userType = document.getElementById("inlineRadio2").value;
    else
        var userType = document.getElementById("inlineRadio1").value;

    const formData = {
        firstname: firstName,
        lastname: lastName,
        username: username,
        email: email,
        password: password,
        usertype: userType,
    }
    console.log(formData);

    formJsonBody = JSON.stringify(formData);
    console.log(formJsonBody);

    fetch('http://localhost:8080/createUser',{
    method: "POST",
    headers: {
        "Content-Type": "application/json"
    },
    body: formJsonBody,
    })
    .then(function(response){
        console.log(response);
        if (response.ok) {
            response.text().then(data => alert(data))
            window.location.assign("./login.html")
        } else {
            response.text().then(data => alert(data))
        }
    })
    .catch(error => {
    console.log(error)
    });
}