<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="icon" href="<c:url value='/static/favicon.png' />" type="image/png">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category Updated</title>
</head>
<body>
<h1>Category Updated</h1>

<p>Category <strong>${category.name}</strong> has been updated successfully!</p>

<table>
    <tr>
        <th>Name</th>
        <td>${category.name}</td>
    </tr>
    <tr>
        <th>Description</th>
        <td>${category.description}</td>
    </tr>
</table>

<button onclick="location.href='<c:url value='/categories/list' />'">Back to Category List</button>

</body>
</html>
