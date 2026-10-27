<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Management Panel</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen p-8">

    <div class="max-w-4xl mx-auto bg-white p-6 rounded shadow-md">
        <div class="flex justify-between items-center mb-4">
            <h2 class="text-xl font-bold">Admin Management Panel</h2>
            <a href="adminRegister" class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">+ Add Admin</a>
        </div>

        <table class="w-full text-left border-collapse">
            <thead>
                <tr class="border-b bg-gray-50">
                    <th class="p-2">ID</th>
                    <th class="p-2">Username</th>
                    <th class="p-2">Email</th>
                    <th class="p-2">Role</th>
                    <th class="p-2">Last Login</th>
                    <th class="p-2">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% List<Admin> admins = (List<Admin>) request.getAttribute("admins");
                   if (admins != null) {
                       for (Admin a : admins) { %>
                <tr class="border-b">
                    <td class="p-2"><%= a.getUserId() %></td>
                    <td class="p-2"><%= a.getUsername() %></td>
                    <td class="p-2"><%= a.getEmail() %></td>
                    <td class="p-2"><%= a.getRole() %></td>
                    <td class="p-2"><%= a.getLastLogin() %></td>
                    <td class="p-2">
                        <a href="updateAdmin?id=<%= a.getUserId() %>" class="text-blue-600 mr-3">Edit</a>
                        <a href="deleteAdmin?id=<%= a.getUserId() %>" class="text-red-600" onclick="return confirm('Delete this admin?')">Delete</a>
                    </td>
                </tr>
                <% } } %>
            </tbody>
        </table>

        <% if (admins == null || admins.isEmpty()) { %>
            <p class="text-gray-500 mt-4">No admins registered yet.</p>
        <% } %>
    </div>

</body>
</html>
