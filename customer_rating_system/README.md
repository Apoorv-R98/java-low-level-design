# ⭐ Customer Rating System

A comprehensive Java-based customer rating system that allows tracking and analyzing agent performance through customer ratings. The system supports multiple ranking strategies and provides detailed analytics for agent performance evaluation.

## 🚀 Features

- **Agent Rating Management**: Record customer ratings (1-5 stars) for agents with timestamps
- **Multiple Ranking Strategies**: 
  - **Overall Average Ranking**: Rank agents by their overall average rating
  - **Monthly Best Agent**: Find the best performing agent for each month
- **Flexible Architecture**: Strategy pattern implementation for easy extension with new ranking algorithms
- **Data Validation**: Comprehensive input validation and error handling
- **In-Memory Storage**: Fast in-memory data repository with easy extensibility to database storage
- **Builder Pattern DTOs**: Clean data transfer objects with builder pattern for flexibility

## 🏗️ Architecture

The application follows clean architecture principles with clear separation of concerns:

```
src/com/rating/
├── Main.java                           # Application entry point
├── models/                             # Domain entities
│   ├── Agent.java                      # Agent entity
│   ├── Rating.java                     # Rating entity
│   └── dto/                            # Data Transfer Objects
│       ├── AgentAverageDTO.java        # Agent average rating DTO
│       └── MonthlyBestAgentDTO.java    # Monthly best agent DTO
├── services/                           # Business logic layer
│   ├── RatingService.java              # Core rating management service
│   └── RatingFactory.java              # Factory for creating validated ratings
├── repositories/                       # Data access layer
│   ├── RatingRepository.java           # Repository interface
│   └── InMemoryRatingRepository.java   # In-memory implementation
├── strategies/                         # Strategy pattern implementations
│   ├── RankingStrategy.java            # Strategy interface
│   ├── OverallAverageRankingStrategy.java # Overall ranking implementation
│   └── MonthlyBestAgentStrategy.java   # Monthly best agent implementation
├── exceptions/                         # Custom exceptions
│   ├── RatingSystemException.java      # Base system exception
│   ├── InvalidRatingException.java     # Invalid rating validation exception
│   └── AgentNotFoundException.java     # Agent not found exception
└── utils/                              # Utility classes
    └── AppLogger.java                  # Application logging utility
```

## 🔧 How to Run

### Prerequisites
- Java 11 or higher
- Maven or Gradle (optional, for dependency management)
- Any Java IDE or command line

### Running the Application

1. **Compile the application:**
```bash
cd customer_rating_system
javac -d out src/com/rating/**/*.java
```

2. **Run the application:**
```bash
java -cp out com.rating.Main
```

3. **Expected Output:**
```
=== Overall Ranking ===
AgentAverageDTO{agentId='A3', avg=4.50}
AgentAverageDTO{agentId='A2', avg=4.50}
AgentAverageDTO{agentId='A1', avg=4.00}

=== Monthly Best Agents ===
MonthlyBestAgentDTO{month=2025-01, agentId='A1', avg=5.00}
MonthlyBestAgentDTO{month=2025-02, agentId='A3', avg=5.00}
```

## 📖 API Usage

### Basic Usage Example

```java
import com.rating.repositories.InMemoryRatingRepository;
import com.rating.repositories.RatingRepository;
import com.rating.services.RatingService;
import java.time.LocalDate;

// Initialize the system
RatingRepository repository = new InMemoryRatingRepository();
RatingService ratingService = new RatingService(repository);

// Add ratings for agents
ratingService.addRating("AGENT_001", 5, LocalDate.of(2024, 12, 1));
ratingService.addRating("AGENT_001", 4, LocalDate.of(2024, 12, 5));
ratingService.addRating("AGENT_002", 3, LocalDate.of(2024, 12, 2));
ratingService.addRating("AGENT_002", 5, LocalDate.of(2024, 12, 8));

// Get overall rankings
List<?> overallRankings = ratingService.getOverallRanking();
overallRankings.forEach(System.out::println);

// Get monthly best agents
List<?> monthlyBest = ratingService.getMonthlyBestAgents();
monthlyBest.forEach(System.out::println);
```

### Rating Validation Rules

- **Agent ID**: Cannot be null or empty
- **Rating Value**: Must be between 1 and 5 (inclusive)
- **Date**: Cannot be null
- **Automatic Validation**: All ratings are validated through `RatingFactory`

### Custom Strategy Implementation

You can easily add new ranking strategies by implementing the `RankingStrategy` interface:

```java
public class CustomRankingStrategy implements RankingStrategy {
    @Override
    public List<?> rank(List<Rating> ratings) {
        // Your custom ranking logic here
        return customRankingResults;
    }
}
```

## 💡 Key Design Patterns

### 1. **Strategy Pattern**
- **Interface**: `RankingStrategy` defines the contract for ranking algorithms
- **Implementations**: Different ranking strategies (`OverallAverageRankingStrategy`, `MonthlyBestAgentStrategy`)
- **Benefits**: Easy to add new ranking algorithms without modifying existing code

### 2. **Factory Pattern**
- **Factory**: `RatingFactory` creates and validates `Rating` objects
- **Benefits**: Centralized validation logic and consistent object creation

### 3. **Builder Pattern**
- **DTOs**: `AgentAverageDTO` and `MonthlyBestAgentDTO` use builder pattern
- **Benefits**: Flexible object construction with optional parameters

### 4. **Repository Pattern**
- **Interface**: `RatingRepository` abstracts data access
- **Implementation**: `InMemoryRatingRepository` provides in-memory storage
- **Benefits**: Easy to switch to database storage later

## 🔍 Ranking Algorithms

### Overall Average Ranking
- **Purpose**: Ranks all agents by their overall average rating
- **Calculation**: `(sum of all ratings) / (number of ratings)` per agent
- **Sorting**: Descending order (highest average first)
- **Output**: `AgentAverageDTO` objects with agent ID and average rating

### Monthly Best Agent
- **Purpose**: Finds the best performing agent for each month
- **Calculation**: Groups ratings by month, calculates average per agent per month
- **Selection**: Agent with highest average rating for each month
- **Output**: `MonthlyBestAgentDTO` objects with month, agent ID, and average rating

## 📊 Sample Data Analysis

```java
// Sample ratings for demonstration
Agent A1: [5★ Jan-15, 3★ Feb-10] → Average: 4.00
Agent A2: [4★ Jan-20, 5★ Feb-12] → Average: 4.50  
Agent A3: [4★ Jan-25, 5★ Feb-05] → Average: 4.50

// Overall Ranking (by average)
1. A3: 4.50 ⭐
2. A2: 4.50 ⭐  
3. A1: 4.00 ⭐

// Monthly Best Agents
January 2025: A1 (5.00 average)
February 2025: A3 (5.00 average)
```

## 🔒 Exception Handling

The system includes comprehensive error handling:

- **InvalidRatingException**: Thrown for invalid rating values, null agent IDs, or null dates
- **AgentNotFoundException**: Thrown when operations reference non-existent agents
- **RatingSystemException**: Base exception for all system-related errors

### Example Exception Scenarios

```java
// These will throw InvalidRatingException
ratingService.addRating(null, 5, LocalDate.now());     // Null agent ID
ratingService.addRating("A1", 0, LocalDate.now());      // Rating < 1
ratingService.addRating("A1", 6, LocalDate.now());      // Rating > 5
ratingService.addRating("A1", 5, null);                 // Null date
```

## 🚦 Extension Points

### Adding New Ranking Strategies

1. **Implement RankingStrategy interface**:
```java
public class WeightedRatingStrategy implements RankingStrategy {
    @Override
    public List<?> rank(List<Rating> ratings) {
        // Implement weighted ranking logic
        // Consider factors like recency, volume, etc.
        return weightedResults;
    }
}
```

2. **Use in RatingService**:
```java
public List<?> getWeightedRanking() {
    return new WeightedRatingStrategy().rank(repo.findAll());
}
```

### Adding Database Support

1. **Create new repository implementation**:
```java
public class DatabaseRatingRepository implements RatingRepository {
    @Override
    public void save(Rating rating) {
        // Database save logic
    }
    
    @Override
    public List<Rating> findAll() {
        // Database query logic
        return databaseResults;
    }
}
```

2. **Inject into service**:
```java
RatingRepository repo = new DatabaseRatingRepository();
RatingService service = new RatingService(repo);
```

## 🎯 Future Enhancements

- **🗄️ Database Integration**: PostgreSQL/MySQL support with JPA/Hibernate
- **🌐 REST API**: Spring Boot REST endpoints for web integration
- **📊 Analytics Dashboard**: Web-based dashboard for visual analytics
- **⏱️ Time-based Weighting**: Recent ratings have higher weight in calculations
- **👥 Agent Management**: Full CRUD operations for agent entities
- **📈 Trend Analysis**: Rating trends over time analysis
- **🔄 Real-time Updates**: WebSocket support for real-time rating updates
- **📱 Mobile API**: Mobile-optimized API endpoints
- **🔐 Authentication**: Security layer for rating submission
- **📧 Notifications**: Email/SMS alerts for rating thresholds

## 🧪 Testing

### Unit Test Examples

```java
@Test
public void testRatingValidation() {
    assertThrows(InvalidRatingException.class, () -> 
        RatingFactory.create("A1", 0, LocalDate.now()));
    assertThrows(InvalidRatingException.class, () -> 
        RatingFactory.create("A1", 6, LocalDate.now()));
    assertThrows(InvalidRatingException.class, () -> 
        RatingFactory.create(null, 5, LocalDate.now()));
}

@Test
public void testOverallRanking() {
    // Setup test data
    RatingRepository repo = new InMemoryRatingRepository();
    RatingService service = new RatingService(repo);
    
    service.addRating("A1", 5, LocalDate.now());
    service.addRating("A1", 3, LocalDate.now());
    service.addRating("A2", 4, LocalDate.now());
    
    // Test ranking
    List<?> rankings = service.getOverallRanking();
    assertEquals(2, rankings.size());
    // Additional assertions...
}
```

## 📝 Contributing

1. **Fork the repository**
2. **Create a feature branch**: `git checkout -b feature/new-ranking-strategy`
3. **Add your changes** following the existing patterns
4. **Write unit tests** for new functionality
5. **Update documentation** if needed
6. **Submit a pull request**

### Code Style Guidelines

- Follow Java naming conventions
- Use meaningful variable and method names
- Add JavaDoc comments for public APIs
- Maintain consistent indentation (4 spaces)
- Keep methods small and focused (Single Responsibility Principle)

---

**Built with Java | Clean Architecture | Strategy Pattern | Builder Pattern**

**📧 Contact**: For questions or suggestions, please open an issue in the repository.
