//making variables 
// using " var , let ,const"
// we use semicolon ";" to mark the end of line in js as well
var variable = 90390;
// as you can see it is not starically typed language indeed it is scripting language 

var user = "Hello";//upto es5 
console.log(variable,user);//for printing 
/**
 * var always create a variable in the window , it is function scoped ,
 *  available within a function not globally .
 * it could be declared again without any errors with the same name.
 * but it does not happen with let.
 * 
 */

let hi = "Hi";//available in es 6+ 
console.log(hi);
hi = "Bye";
console.log(hi);

//defining a constant like in c 
const pi = 3.14;
let r = 897327;
let area = 2*pi*r;
console.log(area);

