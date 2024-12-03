<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="icon" href="/static/favicon.png" type="image/png">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
</head>
<body>
<h1>Product List</h1>
<button onclick="location.href='/products/create'">Create New Product</button>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Category</th>
        <th>Image</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <!-- Рендеринг продуктов через JSP EL -->
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td>${product.categoryName}</td>
            <td>${product.image}</td>
            <td>${product.status}</td>
            <td>
                <!-- Ссылки на редактирование и удаление -->
                <a href="/products/${product.id}/edit">Edit</a> |
                <a href="/products/${product.id}/delete">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<button onclick="location.href='/categories/list'">Get All Categories</button>

<div id="pagination">
    <!-- Пагинация -->
    <c:forEach var="i" begin="1" end="${totalPages}" varStatus="status">
        <button onclick="fetchProducts(${i - 1})" type="button">${i}</button>
    </c:forEach>
</div>

</body>
</html>
