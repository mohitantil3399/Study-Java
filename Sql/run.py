import subprocess
import sys
import os

# ── Config ──
CONTAINER = "mysql-learning"
USER = "root"
PASSWORD = "Mohit@SQL"
DATABASE = "learning_db"


def run_sql_file(filepath):
    """Execute a .sql file against the MySQL container."""
    if not os.path.exists(filepath):
        print(f"  [X] File not found: {filepath}")
        return

    if not filepath.endswith(".sql"):
        print(f"  [!] Not a .sql file: {filepath}")
        return

    print(f"\n  [>] Running: {os.path.basename(filepath)}")
    print(f"  [>] Target:  {DATABASE}@{CONTAINER}")
    print(f"  {'=' * 40}")

    cmd = f'docker exec -i {CONTAINER} mysql -u{USER} -p{PASSWORD} {DATABASE}'

    try:
        with open(filepath, "r") as f:
            result = subprocess.run(
                cmd,
                stdin=f,
                capture_output=True,
                text=True,
                shell=True
            )

        if result.stdout.strip():
            print(f"\n  [OK] Output:\n")
            for line in result.stdout.strip().split("\n"):
                print(f"  {line}")

        if result.stderr.strip():
            errors = [
                line for line in result.stderr.strip().split("\n")
                if "Using a password on the command line" not in line
            ]
            if errors:
                print(f"\n  [!] Warnings/Errors:\n")
                for line in errors:
                    print(f"  {line}")

        if result.returncode == 0:
            print(f"\n  [OK] Done!")
        else:
            print(f"\n  [X] Failed (exit code {result.returncode})")

    except Exception as e:
        print(f"  [X] Error: {e}")


def main():
    if len(sys.argv) > 1:
        # Usage: python run.py myfile.sql
        filepath = sys.argv[1]
    else:
        # Interactive mode
        print("\n  MySQL SQL Runner")
        print(f"  {'=' * 40}")
        filepath = input("  Enter SQL filename: ").strip()

    # If just a filename (no path), look in current directory
    if not os.path.isabs(filepath) and not os.path.exists(filepath):
        script_dir = os.path.dirname(os.path.abspath(__file__))
        alt_path = os.path.join(script_dir, filepath)
        if os.path.exists(alt_path):
            filepath = alt_path

    run_sql_file(filepath)


if __name__ == "__main__":
    main()
