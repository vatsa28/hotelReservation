let hotelName = sessionStorage.getItem("selectedHotelName");
let fromDate = sessionStorage.getItem("selectedFromDate");
let toDate = sessionStorage.getItem("selectedToDate");
let hotelId = sessionStorage.getItem("selectedHotelId");
let username = sessionStorage.getItem("username");

let execRoomCount = sessionStorage.getItem("selectedHotelExecutiveRooms");
let deluxeRoomCount = sessionStorage.getItem("selectedHotelDeluxRooms");
let standardRoomCount = sessionStorage.getItem("selectedHotelStandardRooms");
let suiteRoomCount = sessionStorage.getItem("selectedHotelSuitRooms");
let roomsAddonPricesAC = sessionStorage.getItem("addOnPricesAC");
let roomsAddonPricesFood = sessionStorage.getItem("addOnPricesFood");
let roomsAddonPricesTV = sessionStorage.getItem("addOnPricesTV");
let roomsAddonPricesWifi = sessionStorage.getItem("addOnPricesWifi");
let roomsAddonPricesExtraBed = sessionStorage.getItem("addOnPricesExtraBed");

function price(){

    formData = getFormData();
    console.log(formData);

    formJsonBody = JSON.stringify(formData);
    console.log(formJsonBody);

    const priceText = document.getElementById("priceText");

    const buttomSubmit = document.getElementById("buttonSubmit");
    buttomSubmit.innerHTML = `<button type="submit" class="btn btn-primary" onclick="book()">Confirm Booking</button>`

    fetch('http://localhost:8080/calculatePrice',{
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: formJsonBody,
    })
    .then(response => response.json())
    .then(data => {

        respData = data;
        console.log(data);
        priceText.innerHTML = "The Price is $"+data;
    })
    .catch(error => console.error(error));

}

function setSessionVariables(){

    document.getElementById("hotelName").innerText = "Hotel Name: " + hotelName;
    document.getElementById("FromDate").innerText = "From Date: " + fromDate;
    document.getElementById("ToDate").innerText = "To Date: " + toDate;
    document.getElementById("execRoomReadOnly").value = "ExecutiveRoom("+execRoomCount+")";
    document.getElementById("deluxeRoomReadOnly").value = "DeluxeRoom("+deluxeRoomCount+")";
    document.getElementById("standardRoomReadOnly").value = "StandardRoom("+standardRoomCount+")";
    document.getElementById("suiteRoomReadOnly").value = "SuiteRoom("+suiteRoomCount+")";

    document.getElementById("form-check-label1").innerText = "AC("+roomsAddonPricesAC+"%)";
    document.getElementById("form-check-label2").innerText = "Food("+roomsAddonPricesFood+"%)";
    document.getElementById("form-check-label3").innerText = "WiFi("+roomsAddonPricesWifi+"%)";
    document.getElementById("form-check-label4").innerText = "Television("+roomsAddonPricesTV+"%)";
    document.getElementById("form-check-label5").innerText = "ExtraBed("+roomsAddonPricesExtraBed+"%)";
}

function getFormData(){

    const deluxRoomUserCount = document.getElementById("deluxeRoom").value;
    const deluxRoomUserActCount = deluxRoomUserCount !== "" ? deluxRoomUserCount : 0;
    const standardRoomUserCount = document.getElementById("standardRoom").value;
    const standardRoomUserActCount = standardRoomUserCount !== "" ? standardRoomUserCount : 0;
    const suiteRoomUserCount = document.getElementById("suiteRoom").value;
    const suiteRoomUserActCount = suiteRoomUserCount !== "" ? suiteRoomUserCount : 0;
    const execRoomUserCount = document.getElementById("execRoom").value;
    const execRoomUserActCount = execRoomUserCount !== "" ? execRoomUserCount : 0;

    const roomsDict = {
        Delux: deluxRoomUserActCount,
        Standard: standardRoomUserActCount,
        Executive: execRoomUserActCount,
        Suit: suiteRoomUserActCount
    }

    const amenitiesList = [];
    
    const checkBoxAC = document.getElementById("checkBoxAC");
    const checkBoxFood = document.getElementById("checkBoxFood");
    const checkboxWifi = document.getElementById("checkBoxWiFi");
    const checkBoxTV = document.getElementById("checkBoxTV");
    const checkBoxExtraBed = document.getElementById("checkBoxExtraBed");

    if(checkBoxAC.checked)
        amenitiesList.push(checkBoxAC.value);
    if(checkBoxFood.checked)
        amenitiesList.push(checkBoxFood.value);
    if(checkboxWifi.checked)
        amenitiesList.push(checkboxWifi.value);
    if(checkBoxTV.checked)
        amenitiesList.push(checkBoxTV.value);
    if(checkBoxExtraBed.checked)
        amenitiesList.push(checkBoxExtraBed.value);

    console.log(amenitiesList);

    const formData = {
        hotelid: hotelId,
        rooms: roomsDict,
        bookedfrom: fromDate,
        bookedto: toDate,
        username: username,
        addons: amenitiesList,
    }
    console.log(formData);

    return formData;

}

function book(){
    console.log("Hello");

    formData = getFormData();
    console.log(formData);

    formJsonBody = JSON.stringify(formData);
    console.log(formJsonBody);

    fetch('http://localhost:8080/bookHotel',{
        method: "POST", // *GET, POST, PUT, DELETE, etc.
        headers: {
            "Content-Type": "application/json"
        },
        body: formJsonBody,
    })
    .then(response =>{

        response.text().then(data => alert(data))
        window.location.assign("./homepage.html")
    })
    .catch(error => console.error(error));
}

function myBookings(){
    window.location.assign("./myBookings.html");
}

function myDashboard(){
    window.location.assign("./homepage.html");
}