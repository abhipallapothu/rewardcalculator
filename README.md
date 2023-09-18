Reward Points Calculator 🎉

Overview

The Reward Points Calculator is a Spring Boot and React-based application that allows users to track reward points based on transactions. The application has a range of features from adding new transactions to viewing monthly and total points for each customer.

Features 🚀

- Add New Transaction: A POST request is made to `/api/transactions` to add a new transaction. Points are calculated based on the amount of the transaction and stored.

- View Monthly Points: For each customer, you can view the reward points earned each month by making a GET request to `/api/transactions/monthly/{customerId}`.

- View Total Points: A customer's total points can be fetched by making a GET request to `/api/transactions/total/{customerId}`.

- View All Transactions: To fetch all transactions for a customer, a GET request is made to `/api/transactions/all/{customerId}`.

- View Points for All Customers: The frontend displays monthly and total points for all customers, fetched from `/api/transactions/all-monthly-points` and `/api/transactions/all-total-points` respectively.

Tech Stack 🛠

- Frontend: React
- Backend: Spring Boot (Java)
- Styling: CSS

Setup 🔧

- Backend

  - Navigate to the backend directory and open it with your preferred IDE (e.g., IntelliJ IDEA).
  - Run `RewardCalculatorApplication.java`.
  - Your server will start on [http://localhost:8080](http://localhost:8080).

- Frontend

  - Navigate to the frontend directory in your terminal.
  - Run `npm install` to install the required packages.
  - Run `npm start` to start the development server.
  - Open [http://localhost:3000](http://localhost:3000) to view the app.

Code Structure 📂

- Backend

  - `RewardController.java`: Contains all the API routes for transactions and points calculations.

  - `Transaction.java`: This is the model class that holds the properties of a transaction.

  - `RewardService.java`: Houses the business logic, like calculating points and handling transactions.

- Frontend

  - `App.js`: Main component that wraps the `RewardPoints` component.

  - `RewardPoints.js`: React functional component that handles the state and UI for displaying reward points.

  - `RewardPoints.css`: Basic styling for the `RewardPoints` component.

How it Works 🛠

- The `RewardService.java` calculates points in real-time as transactions are added.

- When you navigate to the front end, it makes API calls to fetch monthly and total points for all customers and displays them using the `CustomerCard` component.

