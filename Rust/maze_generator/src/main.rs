use rand::seq::SliceRandom;
use rand::thread_rng;
use image::{RgbImage, Rgb};

const WIDTH: usize = 20;
const HEIGHT: usize = 15;
const CELL_SIZE: u32 = 20; // pixels per cell

#[derive(Clone, Copy)]
enum Direction {
    Up,
    Down,
    Left,
    Right,
}

fn main() {
    let mut maze = vec![vec![false; WIDTH]; HEIGHT];
    carve_passages(0, 0, &mut maze);

    // List of possible names
    let names = ["Alpha", "Bravo", "Charlie", "Delta", "Echo"];
    let mut rng = thread_rng();
    let chosen_name = names.choose(&mut rng).unwrap();

    // Create an image
    let img_width = (WIDTH as u32) * CELL_SIZE;
    let img_height = (HEIGHT as u32) * CELL_SIZE;
    let mut img = RgbImage::new(img_width, img_height);

    // Draw maze
    for y in 0..HEIGHT {
        for x in 0..WIDTH {
            let color = if maze[y][x] { Rgb([255, 255, 255]) } else { Rgb([0, 0, 0]) };
            for dy in 0..CELL_SIZE {
                for dx in 0..CELL_SIZE {
                    img.put_pixel(x as u32 * CELL_SIZE + dx, y as u32 * CELL_SIZE + dy, color);
                }
            }
        }
    }

    // Save with random name
    let filename = format!("{}.jpg", chosen_name);
    img.save(&filename).expect("Failed to save image");
    println!("Maze saved as {}", filename);
}

fn carve_passages(cx: usize, cy: usize, maze: &mut Vec<Vec<bool>>) {
    let mut rng = thread_rng();
    let mut directions = vec![Direction::Up, Direction::Down, Direction::Left, Direction::Right];
    directions.shuffle(&mut rng);

    maze[cy][cx] = true;

    for dir in directions {
        let (nx, ny) = match dir {
            Direction::Up if cy > 1 => (cx, cy - 2),
            Direction::Down if cy < HEIGHT - 2 => (cx, cy + 2),
            Direction::Left if cx > 1 => (cx - 2, cy),
            Direction::Right if cx < WIDTH - 2 => (cx + 2, cy),
            _ => continue,
        };

        if !maze[ny][nx] {
            maze[(cy + ny) / 2][(cx + nx) / 2] = true; // carve passage
            carve_passages(nx, ny, maze);
        }
    }
}