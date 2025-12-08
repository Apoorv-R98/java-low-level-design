# 💳 Payment App System

A digital payment system supporting P2P, Merchant, and QR code payments with idempotency and validation.

## 🚀 How to Run

### Prerequisites
- Java 11 or higher installed
- Command line access

### Option 1: Using the Script (Recommended)
```bash
# Make script executable (if needed)
chmod +x compile-and-run.sh

# Run the application
./compile-and-run.sh
```

### Option 2: Manual Compilation
```bash
# Create output directory
mkdir -p out

# Compile all Java files
javac -d out src/main/java/paymentsystem/**/*.java

# Run the application
java -cp out paymentsystem.PaymentAppSystem
```

### Option 3: IDE Execution
1. Open the project in IntelliJ IDEA, Eclipse, or VS Code
2. Navigate to `src/main/java/paymentsystem/PaymentAppSystem.java`
3. Right-click and select "Run PaymentAppSystem.main()"

## 📊 Expected Output
```
Before balances: {A1=Account(A1,bal=100000), A2=Account(A2,bal=20000), M1_AC=Account(M1_AC,bal=50000)}
P2P result: PaymentResult(id=...,status=SUCCESS)
QR result: PaymentResult(id=...,status=SUCCESS)  
Merchant result: PaymentResult(id=...,status=SUCCESS)
After balances: {A1=Account(A1,bal=60000), A2=Account(A2,bal=45000), M1_AC=Account(M1_AC,bal=65000)}
```

## 🏗️ Architecture

The system demonstrates:
- **Strategy Pattern**: Different transfer strategies (UPI, QR, Merchant)
- **Template Method**: BasePaymentProcessor workflow
- **Chain of Responsibility**: Validation chain
- **Repository Pattern**: InMemoryDB for data storage
- **Idempotency**: Duplicate request prevention

## 💡 Key Features

- **P2P Payments**: Direct user-to-user transfers
- **Merchant Payments**: Business payment processing
- **QR Code Payments**: Scan-to-pay functionality
- **Thread Safety**: Concurrent transaction handling
- **Validation**: Amount and balance validation
- **Idempotency**: Prevents duplicate transactions

## 🔧 Troubleshooting

### "Unable to locate a Java Runtime"
- Install Java JDK 11+: https://adoptium.net/
- Verify installation: `java -version`

### Compilation Errors
- Ensure all `.java` files are present
- Check for syntax errors in modified files
- Verify package declarations match directory structure

### Runtime Errors
- Check account balances are sufficient
- Verify user and account IDs exist in InMemoryDB
- Review validation logic for business rules

## 📁 Project Structure
```
src/main/java/paymentsystem/
├── PaymentAppSystem.java          # Main application
├── domain/                        # Core entities
├── processor/                     # Payment processors
├── strategies/                    # Transfer strategies
├── validation/                    # Validation logic
├── idempotency/                  # Duplicate prevention
├── db/                           # Data storage
└── exceptions/                   # Custom exceptions
```
