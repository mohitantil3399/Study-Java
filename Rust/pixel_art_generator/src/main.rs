
use rand::seq::SliceRandom;
use rand::thread_rng;
use std::env;
use std::io;
use std::path::Path;

fn main() {
    // Get image path from user input or command line
    let input_path = if env::args().len() > 1 {
        env::args().nth(1).unwrap()
    } else {
        println!("Enter the path of the image:");
        let mut input = String::new();
        io::stdin().read_line(&mut input).expect("Failed to read input");
        input.trim().to_string()
    };

    // Load image
    let img = image::open(&Path::new(&input_path)).expect("Failed to open image");

    // Downsample size (pixel art resolution)
    let pixel_width = 70;
    let pixel_height = 90;

    // Resize down
    let small = img.resize_exact(pixel_width, pixel_height, image::imageops::Nearest);

    // Scale back up to original size
    let pixel_art = small.resize_exact(img.width(), img.height(), image::imageops::Nearest);

    // Random names list
    let names = ["PixelAlpha", "PixelBravo", "PixelCharlie", "PixelDelta", "PixelEcho"];
    let mut rng = thread_rng();
    let chosen_name = names.choose(&mut rng).unwrap();

    // Save output
    let output_name = format!("{}.jpg", chosen_name);
    pixel_art.save(&output_name).expect("Failed to save pixel art image");

    println!("Input image path: {}", input_path);
    println!("Pixel art saved as: {}", output_name);
}