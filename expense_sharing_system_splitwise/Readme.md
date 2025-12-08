# 💰 Expense Sharing System (Splitwise Clone)

A command-line expense sharing application that helps users split bills and track shared expenses, similar to Splitwise. This system supports multiple split types, maintains user balances, and provides intelligent debt netting to minimize the number of transactions needed between users.

## 🚀 Features

- **User Management**: Add and manage users with contact information
- **Multiple Split Types**: 
  - **EQUAL**: Split expenses equally among all participants
  - **EXACT**: Specify exact amounts for each participant  
  - **PERCENT**: Split expenses by percentage allocation
- **Smart Balance Tracking**: Automatic calculation of who owes whom
- **Debt Netting**: Intelligent debt consolidation to minimize transactions
- **Balance Views**: Show individual user balances or complete summary
- **Interactive CLI**: User-friendly command-line interface

## 🏗️ Architecture

The application follows clean architecture principles with clear separation of concerns:

```
src/com/expense/
├── ExpenseSharingApp.java          # Main application entry point
├── controller/                     # Command handling layer
│   ├── CommandDispatcher.java      # Routes commands to handlers
│   └── commands/                   # Individual command implementations
├── services/                       # Business logic layer
│   ├── ExpenseService.java         # Core expense management
│   ├── LedgerService.java          # Balance and debt tracking
│   └── SplitFactory.java           # Split calculation logic
├── models/                         # Domain entities
│   ├── User.java                   # User entity
│   ├── Expense.java                # Expense entity
│   ├── Split.java                  # Abstract split representation
│   ├── EqualSplit.java             # Equal split implementation
│   ├── ExactSplit.java             # Exact amount split
│   ├── PercentSplit.java           # Percentage-based split
│   └── SplitType.java              # Split type enumeration
└── exceptions/                     # Custom exceptions
    ├── DuplicateUserException.java
    ├── UserNotFoundException.java
    └── InvalidSplitException.java
```

## 🔧 How to Run

### Prerequisites
- Java 11 or higher
- Any Java IDE or command line

### Running the Application

1. **Compile the application:**
```bash
javac -d out src/com/expense/**/*.java
```

2. **Run the application:**
```bash
java -cp out com.expense.ExpenseSharingApp
```

3. **Start using the interactive shell:**
```
=======================================
        Expense Sharing System
=======================================
Type HELP to see available commands.
```

## 📖 Commands & Usage

### Add Users
```bash
ADD_USER <user_id> <user_name> <email> <mobile_number>
```
**Example:**
```bash
ADD_USER u1 John john@example.com 9876543210
ADD_USER u2 Jane jane@example.com 9876543211
ADD_USER u3 Bob bob@example.com 9876543212
```

### Add Expenses

#### EQUAL Split
Split amount equally among all participants:
```bash
EXPENSE <paid_by_user_id> <amount> <number_of_users> <user_id_1> <user_id_2> ... EQUAL
```
**Example:**
```bash
EXPENSE u1 1200 3 u1 u2 u3 EQUAL
# Each person owes 400: u2 owes u1: 400, u3 owes u1: 400
```

#### EXACT Split
Specify exact amounts for each participant:
```bash
EXPENSE <paid_by_user_id> <amount> <number_of_users> <user_id_1> <user_id_2> ... EXACT <amount_1> <amount_2> ...
```
**Example:**
```bash
EXPENSE u1 1200 3 u1 u2 u3 EXACT 400 500 300
# u2 owes u1: 500, u3 owes u1: 300
```

#### PERCENT Split
Split by percentage (must sum to 100%):
```bash
EXPENSE <paid_by_user_id> <amount> <number_of_users> <user_id_1> <user_id_2> ... PERCENT <percent_1> <percent_2> ...
```
**Example:**
```bash
EXPENSE u1 1200 3 u1 u2 u3 PERCENT 40 35 25
# u2 owes u1: 420 (35%), u3 owes u1: 300 (25%)
```

### View Balances
```bash
SHOW                    # Show all user balances
SHOW <user_id>          # Show specific user's balance
SHOW_SUMMARY            # Show consolidated balance summary
```

### Other Commands
```bash
HELP                    # Display help information
EXIT                    # Exit the application
```

## 💡 Example Usage Session

```bash
# Add users
ADD_USER u1 Alice alice@test.com 1234567890
ADD_USER u2 Bob bob@test.com 1234567891
ADD_USER u3 Charlie charlie@test.com 1234567892

# Alice pays for dinner, split equally
EXPENSE u1 1200 3 u1 u2 u3 EQUAL

# Check balances
SHOW
# Output:
# u2 owes u1: 400
# u3 owes u1: 400

# Bob pays for movie tickets, exact amounts
EXPENSE u2 600 3 u1 u2 u3 EXACT 250 200 150

# Check Alice's balance
SHOW u1
# Output:
# u1 owes u2: 250

# Show summary
SHOW_SUMMARY
# Output:
# ---- BALANCE SUMMARY ----
# u1 owes u2: 250
# u3 owes u1: 400
# u3 owes u2: 150
```

## 🧠 Key Technical Features

### 1. **Smart Debt Netting**
The system automatically nets mutual debts to minimize transactions:
- If A owes B $100 and B owes A $60, the system shows "A owes B: $40"
- Reduces complexity in real-world settlement scenarios

### 2. **Robust Split Validation**
- **EXACT splits**: Validates that individual amounts sum to total expense
- **PERCENT splits**: Ensures percentages sum to exactly 100%
- **Error handling**: Comprehensive validation with meaningful error messages

### 3. **Command Pattern Implementation**
- Clean separation between command parsing and execution
- Easy to extend with new commands
- Robust error handling and user feedback

### 4. **Factory Pattern for Splits**
- `SplitFactory` creates appropriate split objects based on type
- Extensible design for adding new split algorithms
- Consistent calculation interface across all split types

## 🔒 Exception Handling

The system includes comprehensive error handling:
- **DuplicateUserException**: Prevents adding users with existing IDs
- **UserNotFoundException**: Validates user existence before operations
- **InvalidSplitException**: Ensures split calculations are mathematically valid
- **Input Validation**: Handles malformed commands gracefully

## 🚦 Design Patterns Used

1. **Command Pattern**: For handling different user commands
2. **Factory Pattern**: For creating appropriate split objects
3. **Strategy Pattern**: Different split calculation strategies
4. **Repository Pattern**: User and balance data management
5. **Builder Pattern**: For constructing complex expense objects

## 📊 Balance Tracking Logic

The ledger maintains a two-dimensional mapping:
```java
balances[borrower][lender] = amount
```
- Automatically handles debt netting between users
- Rounds to 2 decimal places for currency precision
- Displays clean, formatted output for user readability

## 🔮 Future Enhancements

- **Persistence**: Add database storage for users and expenses
- **Web Interface**: REST API and web UI
- **Groups**: Support for expense groups/circles
- **Notifications**: Email/SMS notifications for expenses
- **Currency**: Multi-currency support
- **History**: Expense history and analytics
- **Mobile App**: Native mobile applications

---

**Built with Java | Command Line Interface | Clean Architecture**

