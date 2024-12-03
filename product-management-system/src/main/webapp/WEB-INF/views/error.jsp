<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f2f2f2;
        }
        .error-container {
            text-align: center;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            width: 400px;
        }
        .error-container h1 {
            color: #ff4747;
        }
        .error-container p {
            font-size: 1.1em;
            margin: 20px 0;
        }
        .error-container button {
            background-color: #ff4747;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 1em;
            cursor: pointer;
            border-radius: 5px;
        }
        .error-container button:hover {
            background-color: #d43f3f;
        }
        .error-container .error-details {
            color: #333;
            font-size: 1em;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="error-container">
    <h1>Error 500 - Internal Server Error</h1>
    <p>An unexpected error has occurred. Please try again later.</p>

    <!-- Если доступна информация об ошибке, выводим её -->
    <c:if test="${not empty error}">
        <div class="error-details">
            <p><strong>Error Details:</strong></p>
            <pre>${error}</pre>
        </div>
    </c:if>

    <button onclick="window.history.back()">Go Back</button>
</div>

</body>
</html>
