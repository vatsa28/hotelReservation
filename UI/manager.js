let username = sessionStorage.getItem("username");
let hotelid;
let bookingid;
let usernameUser;

function viewBookingsFunction(){

    const selectedHotelId = document.getElementById("hotelName").value;
    console.log(selectedHotelId);

    const container = document.getElementById('booking-info-container');

    fetch('http://localhost:8080/hotelBookings?hotelid='+selectedHotelId,{
    method: "GET", // *GET, POST, PUT, DELETE, etc.
    headers: {
        "Content-Type": "application/json"
    },
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
        data.forEach(item => {

            console.log(item);
            hotelid = item.hotelid;
            bookingid = item.hotelbookingid;
            usernameUser = item.username;
            console.log(hotelid);
            console.log(bookingid);
            const hotelDiv = document.createElement('div');

            hotelDiv.innerHTML = `
                <h2>Booking ID : ${item.hotelbookingid}</h2>
                <p>FromDate : ${item.bookedfrom}</p>
                <p>ToDate : ${item.bookedto}</p>
                <p>Addons : ${item.addons}</p>
                <p>RoomsBooked : ${item.roomsbooked}</p>
                <button type="button" class="btn btn-secondary" onclick="checkoutFunc()" id="selectedHotel">Checkout</button>
                `;
            container.appendChild(hotelDiv);
        })
    })
    .catch(error => {
        alert(error.message);
    });

}

function checkoutFunc(){

    const formData = {
        hotelid: hotelid,
        bookingid: bookingid,
        username: usernameUser,
    }

    console.log(formData);
    formJsonBody = JSON.stringify(formData);

    fetch('http://localhost:8080/checkout',{
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: formJsonBody,
    })
    .then(response => {

        response.text().then(data => alert(data))
    })
    .catch(error => console.error(error));

}

function extractRooms(roomsBooked){

    console.log(roomsBooked);
    console.log(roomsBooked.get("Delux"));
    console.log(roomsBooked[Delux]);
    return "234";
}

function setSessionVariables(){

    const userNameDiv = document.getElementById("usernameDisplay");
    userNameDiv.innerText = "Hello " + username+"!";

}