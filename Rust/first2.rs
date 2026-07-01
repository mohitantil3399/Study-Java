use std::io;

fn main() {
    println!("Enter a number: ");
    let mut input = String::new();
    io::stdin()
    .read_line(&mut input)
    .expect("Enter a valid number");
    let base: u64 = input.trim().parse().expect("Not a valid number");

    println!("Enter the power of the number: ");
    let mut power = String::new();
    io::stdin()
    .read_line(&mut power)
    .expect("Enter a valid number");
    let power: u64 = power.trim().parse().expect("Not a valid number");

    let mut result: u64 = 1;
    for _ in 0..power {
        result *= base;
    }

    println!("The value of {} raised to {} is: {}", base, power, result);
}