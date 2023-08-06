"use strict";
var _a, _b, _c;
// - any type box
let box = {
    contents: [],
    contentLength: () => box.contents.length // ignore (undefined) verification !!!
};
(_a = box.contents) === null || _a === void 0 ? void 0 : _a.push(12);
(_b = box.contents) === null || _b === void 0 ? void 0 : _b.push("asd");
box.contentLength(); // == 2
// -- typed box with numbers
let box2 = {
    contents: [],
    contentLength: () => { var _a, _b; return (_b = (_a = box2.contents) === null || _a === void 0 ? void 0 : _a.length) !== null && _b !== void 0 ? _b : 0; } // if undefined set as zero
};
(_c = box2.contents) === null || _c === void 0 ? void 0 : _c.push(12);
// box2.contents?.push("asd") >>> ERROR: cannot add String to box typed as number
// -- generif function
function sum(arg) {
    return arg;
}
let sum_return = sum("123");
