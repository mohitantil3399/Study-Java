use std ::time::Instant;
fn main(){
    let total_iterations = 100000;
    let start_time = Instant::now();
    // run the loop 
    for _i in 0..total_iterations{
        // only looped through 
    }
    let end_time = Instant::now();
    let total_time = end_time.duration_since(start_time);
    println!("Total number of iterations : {}",total_iterations);
    println!("Total time taken : {} nano seconds.",total_time.as_nanos());
}