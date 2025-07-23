# Shop Farm

This project is an e-commerce developed during the Full Cycle discipline of the Computer Science Course of the Federal University of Vale do São Francisco.

---

## Technologies used

### Backend
- Java 17
- Maven
- Spring Boot
- Spring Security
- Mercado Pago SDK for Java

### DataBase
- MySQL

### Frontend
- TypeScript
- React
- Axios
- Tailwind
- Material UI
- Swiper
- Framer Motion

---

# How to execute the project

### Prerequisites

- Java 17
- Node.js 18+
- MySQL DataBase
- Account in [Mercado Pago Developer](https://www.mercadopago.com.br/developers/panel) (sandbox mode)

---

## DataBase

- Create a MySQL database with the name "shopfarm"
- Make the following changes to the Application.Properties file on the path "Shopfarm\api\src\main\resources"

```ts
  spring.datasource.username= Database User Name
  spring.datasource.password= Database User Password
  mp.token= Mercado Pago Api Private Key
```
- Access the Shop Farm directory and run the migrations with the commands.
  
```bash
  cd api
  mvnw flyway:migrate
```
## Backend

- Access the Shop Farm directory and run the Backend with the following commands.

```bash
  cd api
  mvnw spring-boot:run
```

## Frontend

- Access the Frontend directory

```bash
  cd frontend
```

- Install the dependencies

```bash
  npm install
```

- Go to the "Shopfarm\frontend\src\pages" pages directory on the path, go to the `payment.tsx` file and place the Mercado Pago API public key at the appointed location.

- Run the Frontend with the following commands

```bash
  npm run dev
```
  


