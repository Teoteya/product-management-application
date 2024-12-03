<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="icon" href="/static/favicon.png" type="image/png">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Category</title>
</head>
<body>
<h1>Edit Category</h1>

<form action="/categories/${category.id}/update" method="post">
    <input type="hidden" name="_method" value="put"> <!-- Для имитации PUT запроса -->

    <div>
        <label for="name">Category Name</label>
        <input type="text" id="name" name="name" value="${category.name}" placeholder="Category Name" required>
    </div>

    <div>
        <label for="description">Description</label>
        <textarea id="description" name="description" placeholder="Category Description">${category.description}</textarea>
    </div>

    <button type="submit">Update Category</button>
</form>

<button onclick="location.href='/categories/list'">Back to Category List</button>

</body>
</html>
