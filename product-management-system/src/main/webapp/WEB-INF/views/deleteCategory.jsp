<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="icon" href="<c:url value='/static/favicon.png' />" type="image/png">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Category</title>
</head>
<body>
<h1>Delete Category</h1>

<p>Are you sure you want to delete the category <strong>${category.name}</strong>?</p>

<form action="<c:url value='/categories/${category.id}/delete' />" method="post">
    <input type="hidden" name="_method" value="delete"> <!-- Для имитации DELETE запроса -->
    <button type="submit">Yes, Delete</button>
</form>

<button onclick="location.href='<c:url value='/categories/list' />'">No, Back to Category List</button>

</body>
</html>
