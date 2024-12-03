<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Product</title>
</head>
<body>
<h1>Update Product</h1>
<form action="/products/${product.id}/update" method="post" enctype="multipart/form-data">
    <input type="hidden" name="_method" value="put">  <!-- Для имитации PUT запроса -->

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${product.name}" required/><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description">${product.description}</textarea><br>

    <label for="price">Price:</label>
    <input type="number" id="price" name="price" value="${product.price}" step="0.01" required/><br>

    <label for="categoryName">Category Name:</label>
    <select id="categoryName" name="categoryName" required>
        <c:forEach var="category" items="${categories}">
            <option value="${category.name}" ${category.name == product.categoryName ? 'selected' : ''}>
                    ${category.name}
            </option>
        </c:forEach>
    </select><br>

    <label for="status">Status:</label>
    <select id="status" name="status" required>
        <option value="активен" ${product.status == 'активен' ? 'selected' : ''}>активен</option>
        <option value="не активен" ${product.status == 'не активен' ? 'selected' : ''}>не активен</option>
    </select><br>

    <label for="image">Image:</label>
    <input type="file" id="image" name="image"/><br>

    <button type="submit">Update Product</button>
    <a href="/products/list">Back to Products List</a>
</form>

</body>
</html>