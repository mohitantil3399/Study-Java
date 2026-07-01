use std::io;
fn factorial(n: u64) ->u64 {
    if n == 0 || n == 1 {
        return 1;
    }
    return n * factorial(n - 1);
}

fn main() {
    println!("Hello, World!🦀");
    println!("This is my first rust program ❤️❤️");

    for _i in 0..100 {
        println!("I Like Rust 🦀");
    }

    let x = 100;
    if x % 2 == 0 {
        println!("The number is even");
    }
    // taking user input 
    let mut input = String::new();
    println!("Enter the number whose factorial you want to print : ");
    io::stdin()
        .read_line(&mut input)
        .expect("Enter a valid number .");
    let num :u64= input.trim().parse().expect("An error occured");    

    println!("The factorial of {} : {}",num, factorial(num));
}