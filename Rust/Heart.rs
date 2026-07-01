fn abs_i(x: i32) -> i32 {
    if x < 0 { -x } else { x }
}

fn sq_i(x: i32) -> i32 {
    x * x
}

fn main() {
    for j in (-39..=39).rev() {
        for i in -35..=35 {
            let cond =
                // (abs(j-25) < 14 && abs(i) < 6) || (abs(j-25) == 13 && abs(i) < 10)
                ((abs_i(j - 25) < 14 && abs_i(i) < 6)
                 || (abs_i(j - 25) == 13 && abs_i(i) < 10))
                // ( (abs(i)-9)^2 + 2*j^2 <= 100 )
                || ((sq_i(abs_i(i) - 9) + 2 * sq_i(j)) <= 100)
                // (9*abs(i) - 14*j - 210 <= 0 && j <= -3)
                || ((9 * abs_i(i) - 14 * j - 210) <= 0 && j <= -3)
                // (i^2 + 2*(j+30)^2 between 64 and 225, and j <= -29)
                || {
                    let expr = sq_i(i) + 2 * sq_i(j + 30);
                    expr <= 225 && expr >= 64 && j <= -29
                }
                // (abs(abs(i) - 11.5) < 3.5 && abs(j + 23) < 7)
                || {
                    let ia = abs_i(i) as f64;
                    let ja = abs_i(j + 23);
                    ( (ia - 11.5).abs() < 3.5 ) && (ja < 7)
                };

            if cond {
                print!("💕");
            } else {
                print!(" ");
            }
        }
        println!();
    }
}