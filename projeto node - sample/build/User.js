"use strict";
function logPerson(user) {
    console.log(` - ${user.name}, ${user.age}`);
}
const users = [
    {
        name: "Max",
        age: 25,
        occupation: "Student"
    },
    {
        name: "Max",
        age: 23,
        occupation: "Singer"
    }
];
console.log("Users: ");
users.forEach(logPerson);
