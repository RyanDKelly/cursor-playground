# SBT Project

A basic Scala project using SBT (Scala Build Tool).

## Prerequisites

- Java 8 or higher
- SBT (Scala Build Tool)

## Getting Started

### Install SBT

If you don't have SBT installed, you can install it via:

**macOS (using Homebrew):**
```bash
brew install sbt
```

**Or download from:** https://www.scala-sbt.org/download.html

### Running the Project

1. **Compile the project:**
   ```bash
   sbt compile
   ```

2. **Run the application:**
   ```bash
   sbt run
   ```

3. **Run tests:**
   ```bash
   sbt test
   ```

4. **Clean build artifacts:**
   ```bash
   sbt clean
   ```

## Project Structure

```
├── build.sbt                 # Main build configuration
├── project/
│   └── build.properties      # SBT version
├── src/
│   ├── main/
│   │   ├── scala/            # Main Scala source code
│   │   └── resources/        # Main resources
│   └── test/
│       ├── scala/            # Test Scala source code
│       └── resources/        # Test resources
└── target/                   # Compiled classes and dependencies
```

## Dependencies

- Scala 2.13.12
- ScalaTest 3.2.17 (for testing)
