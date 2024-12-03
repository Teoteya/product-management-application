<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category List</title>
</head>
<body>
<h1>Category List</h1>

<!-- Кнопка для создания новой категории -->
<button onclick="location.href='<c:url value='/categories/create' />'">Create New Category</button>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <!-- Цикл для отображения категорий -->
    <c:forEach var="category" items="${categories}">
        <tr>
            <td>${category.id}</td>
            <td>${category.name}</td>
            <td>${category.description}</td>
            <td>
                <!-- Ссылки на редактирование и удаление -->
                <a href="<c:url value='/categories/${category.id}/edit' />">Edit</a> |
                <a href="<c:url value='/categories/${category.id}/delete' />">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<button onclick="location.href='<c:url value='/products/list' />'">Back to Product List</button>

<!-- Пагинация -->
<div id="pagination">
    <c:forEach var="i" begin="1" end="${totalPages}">
        <button onclick="location.href='<c:url value='/categories/list?page=${i - 1}' />'" type="button">
                ${i}
        </button>
    </c:forEach>
</div>

</body>
</html>
