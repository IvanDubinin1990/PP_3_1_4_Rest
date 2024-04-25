let currentUser = "";

fetch("/api/user/current")
    .then(res => res.json())
    .then(data => {
        currentUser = data;
        console.log(data)
        showOneUser(currentUser);
        document.getElementById("headUsername").innerText = currentUser.name + ' ' + currentUser.surname;
        document.getElementById("headRoles").innerText = currentUser.roles.map(role => role.name.replace('ROLE_', '')).join(" ");
    })

function showOneUser(user) {
    let temp = "";
    temp += "<tr>"
    temp += "<td style='text-align: center'>" + user.id + "</td>"
    temp += "<td style='text-align: center'>" + user.name + "</td>"
    temp += "<td style='text-align: center'>" + user.surname + "</td>"
    temp += "<td style='text-align: center'>" + user.department + "</td>"
    temp += "<td style='text-align: center'>" + user.salary + "</td>"
    temp += "<td style='text-align: center'>" + user.email + "</td>"
    temp += "<td style='text-align: center'>" + user.roles.map(role => role.name.replace('ROLE_', '')).join(" ") + "</td>"
    temp += "</tr>"
    document.getElementById("oneUserBody").innerHTML = temp;
}