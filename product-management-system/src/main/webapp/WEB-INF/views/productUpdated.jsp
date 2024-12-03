<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Updated</title>
</head>
<body>

<h1>Product Updated Successfully</h1>
<p><strong>Name:</strong> ${product.name}</p>
<p><strong>Description:</strong> ${product.description}</p>
<p><strong>Price:</strong> ${product.price}</p>
<p><strong>Category:</strong> ${product.categoryName}</p>
<p><strong>Image:</strong> ${product.image}</p>
<p><strong>Status:</strong> ${product.status}</p>

<button onclick="location.href='/products/list'">Back to Products List</button>

</body>
</html>
