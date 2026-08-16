# SQL Learning Environment

MySQL running via Docker for hands-on SQL practice.

---

## Quick Start

```bash
# Start MySQL container
docker compose up -d

# Stop MySQL container
docker compose down

# Stop & delete all data (fresh start)
docker compose down -v
```

---

## Connect to MySQL CLI

Open a terminal and run:

```bash
docker exec -it mysql-learning mysql -uroot -pMohit@SQL learning_db
```

This drops you directly into the MySQL shell connected to `learning_db`. From here you can run any SQL:

```sql
SELECT * FROM employees;
SELECT name, salary FROM employees WHERE department = 'Engineering';
```

Type `exit` to leave the MySQL shell.

---

## Run a SQL File

```bash
# Run learningsql.sql against learning_db
docker exec -i mysql-learning mysql -uroot -pMohit@SQL learning_db < learningsql.sql
```

---

## Using MySQL Directly from VS Code

### Option 1: MySQL Extension (Recommended)

1. Install the **MySQL** extension by Weijan Chen (`cweijan.vscode-mysql-client2`) from the Extensions marketplace
2. Click the **Database** icon in the left sidebar
3. Click **+** to add a new connection with these details:

| Property | Value |
|----------|-------|
| Host     | `localhost` |
| Port     | `3306` |
| User     | `root` |
| Password | `Mohit@SQL` |
| Database | `learning_db` |

4. Once connected, you can:
   - Browse tables, columns, and data visually
   - Right-click a `.sql` file → **Run SQL** to execute it directly
   - Write queries in any `.sql` file and hit `Ctrl+E` to run the selected query
   - See results in a nice table view inside VS Code

### Option 2: SQLTools Extension

1. Install **SQLTools** (`mtxr.sqltools`) + **SQLTools MySQL/MariaDB Driver** (`mtxr.sqltools-driver-mysql`)
2. Open Command Palette (`Ctrl+Shift+P`) → `SQLTools: Add New Connection`
3. Fill in the same connection details above
4. Write SQL in `.sql` files → select queries → `Ctrl+E` to execute

### Option 3: VS Code Terminal (No Extension Needed)

Just open the integrated terminal (`Ctrl+``) and run:

```bash
docker exec -it mysql-learning mysql -uroot -pMohit@SQL learning_db
```

You'll get the MySQL shell right inside VS Code.

---

## What Files Should You Create?

Organize your SQL learning with these file types:

### `.sql` Files — Your Main Learning Files

Create `.sql` files for each topic you're learning:

```
Sql/
├── learningsql.sql           # Scratch pad / playground
├── 01_select_basics.sql      # SELECT, WHERE, ORDER BY, LIMIT
├── 02_filtering.sql          # WHERE, AND, OR, IN, BETWEEN, LIKE
├── 03_aggregations.sql       # COUNT, SUM, AVG, MIN, MAX, GROUP BY
├── 04_joins.sql              # INNER JOIN, LEFT JOIN, RIGHT JOIN
├── 05_subqueries.sql         # Nested queries, EXISTS, IN
├── 06_insert_update_delete.sql  # DML operations
├── 07_create_alter_drop.sql  # DDL operations
├── 08_indexes_views.sql      # Performance & views
├── 09_functions.sql          # String, Date, Math functions
└── 10_advanced.sql           # Window functions, CTEs, transactions
```

> **Tip:** Each file can have multiple queries separated by `;`. Add comments with `--` to take notes.

### Example File Structure

```sql
-- ============================================
-- 01_select_basics.sql
-- Topic: SELECT fundamentals
-- ============================================

-- 1. Select all columns
SELECT * FROM employees;

-- 2. Select specific columns
SELECT name, department, salary FROM employees;

-- 3. Aliases
SELECT name AS employee_name, salary AS annual_pay FROM employees;

-- 4. ORDER BY
SELECT name, salary FROM employees ORDER BY salary DESC;

-- 5. LIMIT
SELECT * FROM employees LIMIT 5;

-- 6. DISTINCT
SELECT DISTINCT department FROM employees;
```

### `scripts/` Folder — Auto-Run on Fresh Start

Any `.sql` files in `scripts/` run automatically when you create the container for the first time. Use this for:

- Setting up custom tables
- Loading test datasets
- Files run in alphabetical order (`01_...`, `02_...`, etc.)

```
scripts/
├── 01_sample_data.sql        # Already created — employees, products, orders
├── 02_my_custom_tables.sql   # Add your own tables here
└── 03_more_test_data.sql     # More practice data
```

> **Note:** Scripts in `scripts/` only run on **first** `docker compose up`. To re-run them, do `docker compose down -v` then `docker compose up -d` for a fresh database.

---

## Connection Details

| Property | Value |
|----------|-------|
| Host     | `localhost` |
| Port     | `3306` |
| User     | `root` |
| Password | `Mohit@SQL` |
| Database | `learning_db` |

---

## Sample Tables

Pre-loaded with sample data for immediate practice:

| Table | Rows | Columns | Good For |
|-------|------|---------|----------|
| **employees** | 10 | id, name, department, salary, hire_date | SELECT, WHERE, GROUP BY, aggregations |
| **products** | 8 | id, name, category, price, stock | Filtering, sorting, math functions |
| **orders** | 10 | id, employee_id, product_id, quantity, order_date | JOINs, foreign keys, subqueries |

---

## Folder Structure

```
Sql/
├── docker-compose.yml        # Container configuration
├── .env                      # MySQL credentials
├── README.md                 # This file
├── learningsql.sql           # Your working SQL file
├── 01_select_basics.sql      # (create as you learn)
├── 02_filtering.sql          # (create as you learn)
├── ...
└── scripts/
    └── 01_sample_data.sql    # Auto-runs on first start
```
