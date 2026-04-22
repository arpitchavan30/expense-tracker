# Expense Tracker

A full-stack Expense Tracker application built as part of a technical assessment.
The application helps users record daily expenses, view spending history, filter records, and track total expenses.

## Tech Stack

### Backend :

* Java 17
* Spring Boot
* Spring MVC
* Spring Data JPA
* H2 Database (file-based persistence)
* Gradle

### Frontend :

* HTML
* CSS
* JavaScript
* Thymeleaf

## Features

### Add Expense

Users can add a new expense with:

* Amount
* Category
* Description
* Expense Date

### View Expenses

Displays all saved expenses in a clean table format.

### Filter by Category

Users can filter expenses by category.

### Sort by Latest

Expenses can be sorted by most recent date.

### Total Spend

Shows total of currently visible expenses.

## Idempotent Create API

To prevent duplicate entries caused by repeated clicks or request retries, expense creation uses an Idempotency-Key header.
If the same request key is sent again, the previously created expense is returned instead of creating a duplicate record.

## Project Structure

```text id="8hn7yr"
src/main/java/com/example/expenses_tracker

controller/
service/
repository/
entity/
dto/
util/
enums/
```

## API Endpoints

### Create Expense

```text id="s4wv1p"
POST /expenses/create
```

### Headers Used:

```text id="5mvyie"
Idempotency-Key: unique-request-key
Content-Type: application/json
```

### Sample Request Body for create api :

```json id="2w3n93"
{
  "amount": 450,
  "category": "Food",
  "description": "Lunch",
  "date": "2026-04-23"
}
```

## Get All Expenses

```text id="4tx3qv"
GET /expenses/getAll
```

Optional Query Parameters used

## Access Application

Open in browser:

```text id="6l44tr"
http://localhost:8080
```

## Design Highlights

* Structured layered architecture for maintainability
* Controller → Service → Repository
* Clear separation between entities and API response models using DTOs
* File-based H2 database for lightweight local persistence
* Simple responsive frontend built with plain HTML, CSS, and JavaScript
* Idempotent create API to prevent duplicate expense creation on repeated submissions
* Clean and readable codebase with focus on backend design principles

## Future Improvements

* Update and delete expense entries
* Pagination for large datasets
* Dashboard with monthly and category-wise analytics
* User authentication and personal expense history
* Migration from H2 to PostgreSQL / MySQL for production scale

## Author

Arpit Chavan
