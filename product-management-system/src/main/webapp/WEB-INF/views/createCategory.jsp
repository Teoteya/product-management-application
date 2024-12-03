<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Category</title>
    <link rel="icon" href="<c:url value='/static/favicon.png' />" type="image/png">
</head>
<body>
<h1>Create New Category</h1>

<!-- Форма для создания категории -->
<form action="<c:url value='/categories/created' />" method="post">
    <div>
        <label for="name">Category Name</label>
        <input type="text" id="name" name="name" placeholder="Enter category name" required>
    </div>
    <div>
        <label for="description">Description</label>
        <textarea id="description" name="description" placeholder="Enter category description"></textarea>
    </div>
    <div>
        <button type="submit">Create Category</button>
    </div>
</form>

<button onclick="location.href='<c:url value='/categories/list' />'">Back to Category List</button>

</body>
</html>
