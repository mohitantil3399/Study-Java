use std::f64;

fn main() {
    let width = 25;
    let height = 35;

    // Choose the character for filled cells (small heart emoji) and empty cells (space).
    let fill = "💖"; // You can try "💖" or "💗" too.
    let empty = "  ";

    // Map grid to the classic heart curve:
    // (x^2 + y^2 - 1)^3 - x^2 * y^3 <= 0
    //
    // We'll scale x to [-1.5, 1.5] and y to [-1.5, 1.5] for a nice proportion.
    // Flip y so the heart isn't upside down in terminal coordinates.

    for row in 0..height {
        let mut line = String::new();
        for col in 0..width {
            // Normalize to [-1.5, 1.5]
            let x = (col as f64 / (width - 1) as f64) * 3.5 - 1.5;
            let y = (row as f64 / (height - 1) as f64) * 3.5 - 1.5;

            // Flip y to make top rows positive (visual top of heart).
            let y = -y;

            let lhs = (x * x + y * y - 1.0).powi(3) - x * x * y.powi(3);

            if lhs <= 0.0 {
                line.push_str(fill);
            } else {
                line.push_str(empty);
            }
        }
        println!("{}", line);
    }
}