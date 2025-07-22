# 🚀 Getting Started

 # 🛠 Prerequisites
 - Java 17 or above installed
 - Git
 - IntelliJ IDEA (recommended) or any Java IDE

 # 🧑‍💻 Steps to Run the Project

  **Clone the repository**
  - git clone https://github.com/sshukl72/optimal-route-lucidity.git
  - cd /path-to-copied-location/optimal-route-lucidity
  - Open in IntelliJ IDEA

  **Open IntelliJ**
  - Click on File → Open... and select the project directory
  
  **Run the Application**
  - Navigate to DeliveryAppDriver.java inside the main package
  - Right-click and choose Run 'DeliveryAppDriver.main()'

  **Output**
   -The console will show the best delivery route and the total time in minutes

# 🚴 Delivery Route Optimizer

This project helps a delivery executive, Aman, determine the **fastest way to complete multiple food deliveries**. Using real-world geographic coordinates, preparation times, and a route-planning algorithm, it computes the optimal sequence of pickups and drop-offs for a batch of food delivery orders.

---

## 🧠 Problem Statement

Imagine a delivery executive named **Shubham**, currently idle in **Koramangala**, Bangalore. Suddenly, he receives a batch of 2 or more delivery orders that need to be completed in the **shortest possible time**.

Each order includes:
- A **restaurantDetails (R1, R2, …)** where food is being prepared (with average prep time)
- A **consumerDetails (C1, C2, …)** who will receive the food

The goal is to:
- Optimize Shubham's delivery route
- Ensure each food item is picked up **after** its prep time
- Deliver every order to its corresponding customer **only after pickup**

---

## 📍 Assumptions

- Shubham, R1, and R2 are notified at the **same time**.
- Food preparation starts **immediately**.
- Aman can wait at restaurants if he arrives before the meal is ready.
- Travel time is computed using the **Haversine formula** (based on coordinates).
- Travel speed is assumed to be **20 km/h (≈ 333.33 meters per minute)** but can be changed dynamically.
- Road conditions, traffic, and real-world deviations are **ignored** for simplicity.

---

## 📦 Features

- Input multiple orders with restaurantDetails and consumerDetails with locations provided
- Compute all valid pickup and delivery sequences
- Respect prep times and optimize total completion time
- Outputs the **shortest time** 

---

## 📂 Project Structure

```bash
 src
 ├── com.lucidity.orderoptimizer
     ├── model
     │   ├── geo        
     │   │   ├── CurrentLocationDetails.java  # Holds cummalative of pickup and drop time, current LocationDetails, picked orders and delivered orders. 
     │   │   ├── LocationDetails.java         # Contains latitude and longitude details
     │   ├── order                            
     │   │   ├── ConsumerDetails.java         # hold details of customer such as customerName and consumer LocationDetails
     │   │   ├── RestaurantDetails.java       # hold details of restaurant such as restaurantName, restaurant LocationDetails and prepration time
     │   │   ├── OrderDetails.java            # hold details  such as Order ID, restaurantDetails and consumerDetails
     │   │   ├── DeliveryPartnerDetails.java  # hold details of deliveryPartner such as name, Location and speed
     ├── service
     │   ├── DeliveryOptimizer.java           # Validation of the input and business logic call and implementation with response
     │   ├── PathPlanner.java                 # Core algorithm to find best route
     ├── util
     │   ├── GeoUtils.java                    # Utility for distance calculation
     │   ├── Constant.java                    # Constant for response and static values
     ├── common
     │   ├── Response.java                    # Contains Generic response for different scenarios
     │    
     └── DeliveryAppDriver.java               # Main application (Driver to run the application)
