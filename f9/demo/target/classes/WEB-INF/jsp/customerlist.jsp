<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- customerList.jsp -->

<!DOCTYPE html>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
    <h2>Customer List</h2>
    <table>
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${customerList}" var="customer">
                <tr>
                    <td>${customer.first_name}</td>
                    <td>${customer.last_name}</td>
                    <td>${customer.email}</td>
                    <td>${customer.phone}</td>
                    <td><a href="/customer/${customer.uuid}">View Details</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="/logout">Logout</a>
    <a href="/add-customer">Add New Customer</a>
</body>
</html>
