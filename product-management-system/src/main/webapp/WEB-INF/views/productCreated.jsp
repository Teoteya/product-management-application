<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Created</title>
</head>
<body>
<h1>Product Created Successfully!</h1>

<h2>Product Details:</h2>
<p><strong>Name:</strong> ${product.name}</p>
<p><strong>Description:</strong> ${product.description}</p>
<p><strong>Price:</strong> ${product.price}</p>
<p><strong>Category:</strong> ${product.categoryName}</p>
<p><strong>Status:</strong> ${product.status}</p>
<p><strong>Created At:</strong> ${product.createdAt}</p>

<button onclick="location.href='/products/create'">Create Another Product</button>
<button onclick="location.href='/products/list'">Go to Product List</button>
</body>
</html>
