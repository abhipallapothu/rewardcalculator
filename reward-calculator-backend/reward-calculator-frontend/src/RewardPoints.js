import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './RewardPoints.css';

const CustomerCard = ({ customerId, monthlyData, totalPoints }) => (
  <div className="customer-card">
    <h2>Customer: {customerId}</h2>
    <h3>Monthly Points</h3>
    <ul>
      {Object.entries(monthlyData).map(([month, points]) => (
        <li key={month}>{month}: {points} points</li>
      ))}
    </ul>
    <h3>Total Points: {totalPoints || 0}</h3>
  </div>
);

const fetchFromAPI = async (url, setError) => {
  try {
    const { data } = await axios.get(url);
    return data;
  } catch (err) {
    setError(`Error fetching data from ${url}`);
  }
};

const RewardPoints = () => {
  const [data, setData] = useState({ monthlyPoints: {}, totalPoints: {} });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    (async () => {
      const [monthlyPoints, totalPoints] = await Promise.all([
        fetchFromAPI('http://localhost:8080/api/transactions/all-monthly-points', setError),
        fetchFromAPI('http://localhost:8080/api/transactions/all-total-points', setError),
      ]);

      setData({ monthlyPoints, totalPoints });
      setLoading(false);
    })();
  }, []);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div className="container">
      <h1>Reward Points for All Customers</h1>
      <div className="customer-list">
        {Object.keys(data.monthlyPoints).map((customerId) => (
          <CustomerCard
            key={customerId}
            customerId={customerId}
            monthlyData={data.monthlyPoints[customerId]}
            totalPoints={data.totalPoints[customerId]}
          />
        ))}
      </div>
    </div>
  );
};

export default RewardPoints;
