<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Admin admin = (Admin) request.getAttribute("admin"); %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Admin</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">

    <div class="bg-white p-8 rounded shadow-md w-96">
        <h2 class="text-xl font-bold mb-4">Edit Admin - <%= admin.getUsername() %></h2>

        <form action="updateAdmin" method="post">
            <input type="hidden" name="userId" value="<%= admin.getUserId() %>">

            <label class="block mb-1">Email</label>
            <input type="email" name="email" value="<%= admin.getEmail() %>" class="w-full border p-2 mb-3 rounded" required>

            <label class="block mb-1">New Password (leave blank to keep current)</label>
            <input type="password" name="password" class="w-full border p-2 mb-3 rounded">

            <label class="block mb-1">Role</label>
            <select name="role" class="w-full border p-2 mb-3 rounded">
                <option value="Admin" <%= admin.getRole().equals("Admin") ? "selected" : "" %>>Admin</option>
                <option value="SuperAdmin" <%= admin.getRole().equals("SuperAdmin") ? "selected" : "" %>>Super Admin</option>
            </select>

            <label class="block mb-1">Permissions</label>
            <input type="text" name="permissions" value="<%= admin.getPermissions() %>" class="w-full border p-2 mb-4 rounded">

            <button type="submit" class="bg-blue-600 text-white w-full py-2 rounded hover:bg-blue-700">Save Changes</button>
        </form>

        <a href="adminList" class="block text-center mt-3 text-blue-600">Cancel</a>
    </div>

</body>
</html>
