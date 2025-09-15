# 🎬 MovieApp

A backend application built with **Spring Boot (Java 21)** and **PostgreSQL** as part of the Associate Software Engineer task.  
The project provides secure movie management APIs with **role-based access** (Admin/User) and integrates with the **OMDB API** for fetching movie data.

---

## ✨ Features

### ✅ Admin Dashboard
- 🔐 **Login**: Secure admin authentication.  
- 🔍 **Search Movies**: Fetch movies from the **external OMDB API** based on search criteria.  
- ➕ **Add/Remove Movies**: Manage movies in the local database.  
- 📚 **Batch Operations**: Add or remove multiple movies at once.  
- 📦 **Pagination**: Smooth navigation through movie lists.  

### 👥 User Dashboard
- 🔐 **Login**: Secure user authentication.  
- 🎬 **View Movies**: Browse movies added by the admin.  
- 🔍 **Search**: Find specific movies by title.  
- ⭐ **Rate Movies**: Users can rate each movie.  


## 🛠 Technology Stack

| Technology | Description |
|------------|-------------|
| 🚀 Backend | Spring Boot 3+, Java 21, Hibernate, Spring Data JPA |
| 📀 Database | PostgreSQL |
| 🎥 API | OMDB API (external API call for movie data) |
| 🔐 Security | Spring Security, JWT Authentication |
| 🧰 Tools | Maven, Lombok, Swagger |

## 📸 Screenshots
![Swagger UI](https://github.com/mohamed12hamdy/MovieApp/blob/master/movieapp/images/Screenshot%20(128).png)
![Swagger UI](https://github.com/mohamed12hamdy/MovieApp/blob/master/movieapp/images/Screenshot%20(129).png)


## 🗃️ Database ERD (Entity relationship diagram)
![Database ERD](https://github.com/mohamed12hamdy/MovieApp/blob/master/movieapp/images/Screenshot%20(123).png)

# 📦 Installation & Setup
## 📥 1. Clone the Repository

git clone https://github.com/mohamed12hamdy/Movieapp.git
cd movie-management-app
🖥️ 2. Application Setup

1.Configure PostgreSQL in application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/movie_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
omdb.api-key=YOUR_OMDB_API_KEY
2.Build and Run the Application:
mvn spring-boot:run
