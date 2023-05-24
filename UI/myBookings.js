let username = sessionStorage.getItem("username");

function setSessionVariables(){

    document.getElementById("usernameDisplay").innerText = "Hello " + username+"!";
    const container = document.getElementById('booking-info-container');

    fetch(`http://localhost:8080/UserBooking/${username}`,{
        method: "GET", // *GET, POST, PUT, DELETE, etc.
        headers: {
            "Content-Type": "application/json"
        },
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
        console.log(data.currentBookings);
        console.log(data.pastBookings);

        const currentBookingsNameDiv = document.getElementById("currBookingsName");
        console.log(currentBookingsNameDiv);
        currentBookingsNameDiv.innerHTML = `<h2>Current Bookings</h2>`;

        const currentBookingsDiv = document.getElementById('currBookings');
        console.log(currentBookingsDiv);

        data.currentBookings.forEach(item => {

            console.log(item);
            const bookingDiv = document.createElement('div');

            bookingDiv.innerHTML = `
                <li>
                <p>RoomsBooked : ${item.roomsbooked}</p>
                <p>Hotel ID : ${item.hotelid}</p>
                <p>FromDate : ${item.bookedfrom}</p>
                <p>ToDate : ${item.bookedto}</p>
                <p>Addons : ${item.addons}</p>
                </li>
                `;
            currentBookingsDiv.appendChild(bookingDiv);
        })
        const pastBookingsNameDiv = document.getElementById("pastBookingsName");
        pastBookingsNameDiv.innerHTML = `<h2>Past Bookings</h2>`;

        const pastBookingsDiv = document.getElementById("pastBookings");

        data.pastBookings.forEach(item => {

            console.log(item);

            const bookingDiv = document.createElement('div');

            bookingDiv.innerHTML = `
                <li>
                <p>RoomsBooked : ${item.roomsbooked}</p>
                <p>Hotel ID : ${item.hotelid}</p>
                <p>FromDate : ${item.bookedfrom}</p>
                <p>ToDate : ${item.bookedto}</p>
                <p>Addons : ${item.addons}</p>
                </li>
                `;
            pastBookingsDiv.appendChild(bookingDiv);
        })

    })
    .catch(error => {
        console.log(error.message);
    });
}

function myDashboard(){
    window.location.assign("./homepage.html");
}