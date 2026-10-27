<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register Admin</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">

    <div class="bg-white p-8 rounded shadow-md w-96">
        <h2 class="text-xl font-bold mb-4">Register New Admin</h2>

        <% if (request.getAttribute("error") != null) { %>
            <p class="text-red-500 mb-3"><%= request.getAttribute("error") %></p>
        <% } %>

        <form action="registerAdmin" method="post">
            <label class="block mb-1">Username</label>
            <input type="text" name="username" class="w-full border p-2 mb-3 rounded" required>

            <label class="block mb-1">Password</label>
            <input type="password" name="password" class="w-full border p-2 mb-3 rounded" required>

            <label class="block mb-1">Email</label>
            <input type="email" name="email" class="w-full border p-2 mb-3 rounded" required>

            <label class="block mb-1">Role</label>
            <select name="role" class="w-full border p-2 mb-3 rounded">
                <option value="Admin">Admin</option>
                <option value="SuperAdmin">Super Admin</option>
            </select>

            <label class="block mb-1">Permissions (comma separated)</label>
            <input type="text" name="permissions" placeholder="approveListing,manageUsers" class="w-full border p-2 mb-4 rounded">

            <button type="submit" class="bg-blue-600 text-white w-full py-2 rounded hover:bg-blue-700">Register</button>
        </form>

        <a href="adminList" class="block text-center mt-3 text-blue-600">Back to Admin List</a>
    </div>

</body>
</html>
