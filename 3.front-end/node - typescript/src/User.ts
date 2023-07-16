
type User = {
    name: String,
    age: number,
    occupation: string
};

function logPerson(user: User){
    console.log(` - ${user.name}, ${user.age}`)
}

const users: User[] = [
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
]

console.log("Users: ")
users.forEach(logPerson)