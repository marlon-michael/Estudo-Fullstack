"use strict";
// -- class of Point
class Point {
    constructor(x, y) {
        this.x = 0;
        this.y = 0;
        this.class_name = "Class name"; // Final value of var
        this.position = () => {
            return [this.x, this.y];
        };
        this.x = x !== null && x !== void 0 ? x : 0;
        this.y = y !== null && y !== void 0 ? y : 0;
        this.class_name = "Class Point()"; // Can edit readonly/Final value in constructor
    }
}
let point = new Point(20, 5);
console.log(point.position());
