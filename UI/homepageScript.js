let respData;
let finalFromDate;
let finalToDate;

let username = sessionStorage.getItem("username");

function setSessionVariables(){

    document.getElementById("usernameDisplay").innerText = "Hello " + username+"!";
}

function myFunction() {
    var name = sessionStorage.getItem("username");
    console.log(name)
    const dateInput = document.getElementById("fromDate");
      const dateString = dateInput.value;
    const dateObject = new Date(dateString);
    const year = dateObject.getFullYear();
    const month = String(dateObject.getMonth() + 1).padStart(2, "0");
    const day = String(dateObject.getDate()).padStart(2, "0");
    const fromDate = `${year}-${month}-${day}`;
    console.log(fromDate);
    finalFromDate = fromDate;
    const dateInput2 = document.getElementById("toDate");
      const dateString2 = dateInput2.value;
    const dateObject2 = new Date(dateString2);
    const year2 = dateObject2.getFullYear();
    const month2 = String(dateObject2.getMonth() + 1).padStart(2, "0");
    const day2 = String(dateObject2.getDate()).padStart(2, "0");
    const toDate = `${year2}-${month2}-${day2}`;
    console.log(toDate);
    finalToDate = toDate;

    const container = document.getElementById('hotel-info-container');

    const params = new URLSearchParams({
          fromDate: fromDate,
          toDate: toDate
    });

    fetch('http://localhost:8080/room/search?'+params,{
        method: "GET", // *GET, POST, PUT, DELETE, etc.
        headers: {
            "Content-Type": "application/json"
        },
        
    })
    .then(response => response.json())
    .then(data => {

        respData = data;
        console.log(data);
        data.forEach(item => {
            console.log(item);
            const hotelDiv = document.createElement('div');

            hotelDiv.innerHTML = `
                <h2>${item.hotel.name}</h2>
                <p>Address: ${item.hotel.street}, ${item.hotel.city}, ${item.hotel.state}</p>
                <p>ZIP Code: ${item.hotel.zipCode}</p>
                <table class="table">
                <thead>
                    <tr>
                    <th scope="col">Room Category</th>
                    <th scope="col">Price</th>
                    <th scope="col">Available Rooms</th>
                    </tr>
                </thead>
                    <tbody>
                        <tr>
                            <th scope="row">Delux Room</th>
                            <td> ${item.roomprices.Delux} </td>
                            <td> ${item.availableRooms.Delux} </td>
                        </tr>
                        <tr>
                            <th scope="row">Executive Room</th>
                            <td> ${item.roomprices.Executive} </td>
                            <td> ${item.availableRooms.Executive} </td>
                        </tr>
                        <tr>
                            <th scope="row">Standard Room</th>
                            <td> ${item.roomprices.Standard} </td>
                            <td> ${item.availableRooms.Standard} </td>
                            </tr>
                        <tr>
                            <th scope="row">Suit Room</th>
                            <td> ${item.roomprices.Suit} </td>
                            <td> ${item.availableRooms.Suit} </td>
                            </tr>
                    </tbody>
                </table>
                <button type="button" class="btn btn-secondary" onclick="myClickFunc()" id="selectedHotel" value="${item.hotel.hotelId}">Select this Hotel</button>
                `;
            container.appendChild(hotelDiv);
        });
    })
    .catch(error => console.error(error));
}

function myClickFunc(){

    console.log("abcdef");
    const buttonValue = event.target.value;
    console.log(buttonValue)
    respData.forEach(item => {
        if(item.hotel.hotelId == buttonValue){
            console.log(item.hotel.name);
            console.log(item.availableRooms.Delux);
            console.log(item.availableRooms.Executive);
            console.log(item.availableRooms.Standard);
            console.log(item.availableRooms.Suit);
            console.log(item.roomaddonPrices);
            console.log(item.roomaddonPrices.TV);
            sessionStorage.setItem("selectedHotelId", item.hotel.hotelId);
            sessionStorage.setItem("selectedHotelName", item.hotel.name);
            sessionStorage.setItem("selectedFromDate", finalFromDate);
            sessionStorage.setItem("selectedToDate", finalToDate);
            sessionStorage.setItem("selectedHotelDeluxRooms", item.availableRooms.Delux);
            sessionStorage.setItem("selectedHotelExecutiveRooms", item.availableRooms.Executive);
            sessionStorage.setItem("selectedHotelStandardRooms", item.availableRooms.Standard);
            sessionStorage.setItem("selectedHotelSuitRooms", item.availableRooms.Suit);
            sessionStorage.setItem("addOnPricesAC", item.roomaddonPrices.AirConditioning);
            sessionStorage.setItem("addOnPricesExtraBed", item.roomaddonPrices.ExtraBed);
            sessionStorage.setItem("addOnPricesFood", item.roomaddonPrices.Food);
            sessionStorage.setItem("addOnPricesTV", item.roomaddonPrices.TV);
            sessionStorage.setItem("addOnPricesWifi", item.roomaddonPrices.Wifi);
            window.location.assign("./hotelBookForm.html");
        }
    })
}

function myBookings(){
    window.location.assign("./myBookings.html");
}

function myDashboard(){
    window.location.assign("./homepage.html");
}