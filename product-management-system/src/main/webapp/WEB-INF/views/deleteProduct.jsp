<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Product</title>
</head>
<body>
<h1>Delete Product</h1>
<p>Are you sure you want to delete the product:</p>
<ul>
    <li><strong>Name:</strong> ${product.name}</li>
    <li><strong>Description:</strong> ${product.description}</li>
    <li><strong>Price:</strong> ${product.price}</li>
    <li><strong>Category:</strong> ${product.categoryName}</li>
</ul>

<form action="<c:url value='/products/${product.id}/delete' />" method="post">
    <input type="hidden" name="_method" value="delete"> <!-- Для имитации DELETE запроса -->
    <button type="submit">Yes, Delete</button>
    <button type="button" onclick="location.href='<c:url value='/products/list' />'">Cancel</button>
</form>
</body>
</html>
