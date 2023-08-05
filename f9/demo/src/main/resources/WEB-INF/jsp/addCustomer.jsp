<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- addCustomer.jsp -->

<!DOCTYPE html>
<html>
<head>
    <title>Add New Customer</title>
</head>
<body>
    <h2>Add New Customer</h2>
    <form method="post" action="/add-customer">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" required>

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" required>

        <label for="street">Street:</label>
        <input type="text" id="street" name="street">

        <label for="address">Address:</label>
        <input type="text" id="address" name="address">

        <label for="city">City:</label>
        <input type="text" id="city" name="city">

        <label for="state">State:</label>
        <input type="text" id="state" name="state">

        <label for="email">Email:</label>
        <input type="email" id="email" name="email">

        <label for="phone">Phone:</label>
        <input type="tel" id="phone" name="phone">

        <button type="submit">Save</button>
    </form>
    <a href="/customer">Back to Customer List</a>
</body>
</html>
