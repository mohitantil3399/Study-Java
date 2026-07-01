use std::io;
fn add(a: i64, b: i64) -> i64 { a + b }
fn sub(a: i64, b: i64) -> i64 { a - b }
fn mul(a: i64, b: i64) -> i64 { a * b }
fn div(a: i64, b: i64) -> i64 { a / b }
fn exp(a: i64, b: i64) -> i64 { a.pow(b as u32) }
fn rem(a: i64, b: i64) -> i64 { a % b }
fn log(a: i64) -> f64 { (a as f64).ln() }
fn fact(a: i64) -> i64 { (1..=a).product() }

fn main(){
     let list = "
     for addition : +
     for subtraction : -
     for multiplication : *
     for division : /
     for remainder : %
     for exponent : ^
     for log : log
     for factorial : fact
      ";
     println!("The list is :{}",list);
     println!("Choose:\"1 : for using calculator\",\"0 : for exit\"");
     let mut input = String::new();
     io::stdin().read_line(&mut input).expect("Enter a valid input");
     let mut num:i32 = input.trim().parse().expect("Not a valid number");

     while num != 0{
          
       let mut number1 = String::new();
       let mut operation = String::new();
       let mut number2 = String::new();
          println!("Enter your number");
          io::stdin()
             .read_line(&mut number1)
             .expect("Enter a valid number");
          println!("Enter an operation from the list:");
          io::stdin()
             .read_line(&mut operation)
             .expect("Enter a valid operation");
          println!("Enter your number");
          io::stdin()
             .read_line(&mut number2)
             .expect("Enter a valid number");
          let a = number1.trim().parse().expect("Enter a valid number:");
          let b = number2.trim().parse().expect("Enter a valid number:");
          let op = operation.trim();
         match op {
        "+"   => println!("Result: {}", add(a, b)),
        "-"   => println!("Result: {}", sub(a, b)),
        "*"   => println!("Result: {}", mul(a, b)),
        "/"   => {if b != 0{
                  println!("Result: {}", div(a, b))
                 }else{
                    println!("Can not divide by 0.")
                 }
               },
        "%"   => println!("Result: {}", rem(a, b)),
        "^"   => println!("Result: {}", exp(a, b)),
        "log" => println!("Result:log of{} is : {}",a, log(a)),
        "fact"=> println!("Result: factorial of {} is : {}",a, fact(a)),
        _     => println!("Unknown operation"),
    }
    println!("Enter your choice again (1 to continue, 0 to exit):");
        input.clear();
        io::stdin()
           .read_line(&mut input)
           .expect("Enter a valid input");
        num = input.trim().parse().expect("Not a valid number");
    
     }

}