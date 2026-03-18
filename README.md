🏦 Banking Application (Spring Boot + MySQL)

📌 Overview

This is a simple banking backend system built using Spring Boot and MySQL.
It allows users to perform basic banking operations such as deposits, withdrawals, and transaction tracking.

⸻

🚀 Features
•	👤 Create Users
•	📋 View Users
•	💰 Deposit Money
•	💸 Withdraw Money
•	📊 Transaction History
•	🗄️ MySQL Database Integration

⸻

🛠️ Technologies Used
•	Java (Spring Boot)
•	MySQL
•	JPA (Hibernate)
•	REST APIs
•	Postman (for testing)

⸻

⚙️ API Endpoints

➤ Create User

POST /api/users

➤ Get All Users

GET /api/users

➤ Deposit

POST /api/users/deposit?userId={id}&amount={amount}

➤ Withdraw

POST /api/users/withdraw?userId={id}&amount={amount}

🧪 Sample JSON (Create User)
{
"name": "Nipul",
"email": "nipul@gmail.com",
"password": "1234",
"balance": 1000
}

📊 Database Tables
•	User
•	Transaction

⸻

🎯 Future Improvements
•	🔐 Login & Authentication
•	💸 Transfer between users
•	📊 Dashboard UI
•	📁 Export to Excel

⸻

👨‍💻 Author

Nipul Kanishka
:::
