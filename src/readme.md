# Shop Springboot

## Installation

## Contributing

### Understanding the class diagram :

```mermaid
classDiagram
    User "1"-->"0..*" Card : has
    Card <--> Product
    Order <--> Product
    User --> Order
    Order "1"-->"1..*" ProductRow
    
    class User {
      -Integer id
      -String username
      +User getUsername()
    }
    class Product {
        -Integer id
        -String name
        -Float price
    }
    class Card {
        -Integer id
        -DateTime creationDate
        -Map<Product, Integer> products
    }
    class Order {
        -Integer id
        -DateTime creationDate
        -List<ProductRow> products
    }
    class ProductRow {
        -Integer id
        -Product product
        -Integer quantity
        -Float price
        -Float discount
    }

```