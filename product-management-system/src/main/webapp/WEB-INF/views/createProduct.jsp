<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Product</title>
</head>
<body>
<h1>Create New Product</h1>
<form action="<c:url value='/products/created' />" method="post" enctype="multipart/form-data">

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required/><br>

    <label for="description">Description:</label>
    <input type="text" id="description" name="description"/><br>

    <label for="price">Price:</label>
    <input type="number" id="price" name="price" required/><br>

    <label for="categoryName">Category Name:</label>
    <select id="categoryName" name="categoryName" required>
        <c:forEach var="category" items="${categories}">
            <option value="${category.name}">${category.name}</option>
        </c:forEach>
    </select>

    <label for="status">Status:</label>
    <select id="status" name="status" required>
        <option value="" disabled selected>Choose a status</option>
        <option value="активен">активен</option>
        <option value="не активен">не активен</option>
    </select>

    <label for="image">Image:</label>
    <input type="file" id="image" name="image"/><br>

    <button type="submit">Create Product</button>

    <a href="<c:url value='/products/list' />">Back to Products List</a>
</form>
</body>
</html>
