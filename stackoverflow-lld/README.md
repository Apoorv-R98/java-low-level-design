
# StackOverflow LLD (Java)

A simplified Low Level Design of a StackOverflow-like system suitable for **1-hour LLD interviews**.

## Features
- Post Questions
- Answer Questions
- Comment on Posts
- Upvote / Downvote
- Tags on Questions
- Search by keyword or tag
- Thread-safe operations
- Custom Exceptions

## Design Patterns Used

| Pattern | Usage |
|-------|------|
Factory | Post creation |
Strategy | Voting system |
Repository | Data access |
Service Layer | Business logic |
Concurrency | synchronized methods + ConcurrentHashMap |

## Project Structure

src/
 model/
 strategy/
 factory/
 repository/
 service/
 exception/
 Demo.java

## Key Concepts

**Post Abstraction**
```
Post
 ├── Question
 └── Answer
```

**Voting**
Strategy pattern allows flexible vote behaviors.

**Thread Safety**
- synchronized methods
- ConcurrentHashMap for repository

## Run

Compile:

```
javac Demo.java
```

Run:

```
java Demo
```
