<%@ page import="com.example.product_management_system.entity.Category" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category Created</title>
    <link rel="icon" href="<c:url value='/static/favicon.png' />" type="image/png">
</head>
<body>
<h1>Category Created Successfully!</h1>

<!-- Отображение информации о созданной категории -->
<div>
    <h2>Category Details</h2>
    <p><strong>Name:</strong> <%= request.getAttribute("category") != null ? ((Category) request.getAttribute("category")).getName() : "Category Name" %></p>
    <p><strong>Description:</strong> <%= request.getAttribute("category") != null ? ((Category) request.getAttribute("category")).getDescription() : "Category Description" %></p>
</div>

<button onclick="location.href='<c:url value='/categories/list' />'">Back to Category List</button>

</body>
</html>
