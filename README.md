## Описание
Product Management Application — это приложение для управления продуктами и категориями на Java с использованием Spring Boot, Hibernate и PostgreSQL. Этот документ содержит инструкции по локальному запуску проекта.

## Технологический стек
- **Java**: 17
- **Spring Boot**: 3.3.5
- **PostgreSQL**: 17.0
- **Maven**
- **Hibernate**
- **Git**

## Системные требования
- Свободный порт `5432` для PostgreSQL
- Свободный порт `8080` для приложения

## Шаги для локального запуска проекта
1. Склонируйте репозиторий проекта:
git clone https://github.com/Teoteya/product-management-application.git

2. Запустите PostgreSQL и создайте базу данных:
CREATE DATABASE product-management;

Для подключения к базе данных используйте следующие параметры:

URL: jdbc:postgresql://localhost:5432/product-management

Имя пользователя: postgres

Пароль: 12345

3. Соберите проект с помощью Maven:
mvn clean package 

После успешной сборки в папке target появится WAR-файл:
product-management-system-0.0.1-SNAPSHOT.war

После запуска приложение будет доступно по адресу http://localhost:8080.

## Для того, чтобы выполнить вход, нужно ввести данные одного из пользователей, помещенных в БД:

Username: user@gmail.com Password: 12345

Username: admin@gmail.com Password: 123456

## Документация API.
Swagger UI:
После запуска приложения вы можете открыть Swagger UI для тестирования API: 
http://localhost:8080/swagger-ui/index.html
Документация API: http://localhost:8080/v3/api-docs
