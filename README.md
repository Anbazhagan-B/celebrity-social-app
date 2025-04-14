# 🌐 Social Connect

A full-stack social media-style application built using **React**, **Redux**, and **Spring Boot**, featuring:

- ✅ User registration and login
- 📸 Post sharing with image preview
- 🔗 User connections (connect/avoid)
- 💬 Real-time chat using WebSocket/STOMP
- 🔍 Trie-based username search
- 🎯 Microservices architecture with media handling



## 📦 Tech Stack

### 🖥️ Frontend
- [React](https://reactjs.org/)
- [Redux + Redux-Saga](https://redux-saga.js.org/)
- [React Router](https://reactrouter.com/)
- [Axios](https://axios-http.com/)
- [FontAwesome via react-icons](https://react-icons.github.io/react-icons/)
- Responsive layout with custom CSS

### ☕ Backend
- [Spring Boot 3.x](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [WebSocket + STOMP](https://spring.io/guides/gs/messaging-stomp-websocket/)
- [MySQL](https://www.mysql.com/)
- AWS S3 for profile & post images
- Trie-based in-memory user search
- REST APIs for Users, Posts, Connections


## 🚀 Features

- 👤 User registration & login
- 📸 Add and preview image posts (stored in AWS S3)
- 🔄 Interactions: Connect / Avoid other users
- 🧑‍🤝‍🧑 View list of connections
- 💬 Real-time one-to-one chat with read/delivered indicators
- 🔍 Trie-based username prefix search for performance
- 🔔 API error handling with global root-level popups
- 🎨 Consistent UI with `goldenrod` theme and responsive layout


## 🔧 Architecture Overview

The application is built using a **microservices architecture**, where services are logically and functionally decoupled. Each service handles one domain-specific responsibility.

### 📊 Architecture Diagram

![Social-Connect-Architecture](https://github.com/user-attachments/assets/e2c52f2d-4964-457a-a275-7aba22c69a9d)

> 🔗 **User Service** and **Post Service** communicate with the **Media Service** to upload and retrieve image URLs from **AWS S3**.  
> 💬 The **Chat Service** operates independently and does not use Redis currently.


## 🧑‍💻 Author

**Anbazhagan B**  
📧 anbazhagan.b414@gmail.com  
📧 anbazhagan.balasubramanian24@gmail.com  
🔗 [LinkedIn](http://www.linkedin.com/in/%20anbazhaganb414)

## 📄 License

This project is licensed under the **MIT License**. You are free to use, modify, and distribute it.

## 📸 Screenshots

  ![image](https://github.com/user-attachments/assets/556a591c-e379-4906-a360-04c997c4bcb9)
  ![image](https://github.com/user-attachments/assets/9eede5c9-701b-44d0-80dc-d4da51bece60)
  ![image](https://github.com/user-attachments/assets/e1cbf9ce-5b9a-4701-bcfb-3c217d423518)
  ![image](https://github.com/user-attachments/assets/b4f75ca3-2b03-4774-b666-4ba172ede2ed)
  ![image](https://github.com/user-attachments/assets/746605b3-7198-43ba-9dc9-a20574d76350)





