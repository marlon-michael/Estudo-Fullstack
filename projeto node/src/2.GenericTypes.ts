
// -- Generic type of Box
type Box<Box_Type = any> = {
    contents? : Box_Type[]
    contentLength: () => number; // function that returns a number (need to be set on instances of Box)
}

// - any type box
let box: Box = {
    contents: [],
    contentLength: () => box.contents!.length // ignore (undefined) verification !!!
}
box.contents?.push(12)
box.contents?.push("asd")
box.contentLength() // == 2

// -- typed box with numbers
let box2: Box<number> = {
    contents: [],
    contentLength: () => box2.contents?.length ?? 0 // if undefined set as zero
}
box2.contents?.push(12)
// box2.contents?.push("asd") >>> ERROR: cannot add String to box typed as number

// -- generif function
function sum<Func_Type>(arg: Func_Type): Func_Type{
    return arg
}
let sum_return: string = sum("123");