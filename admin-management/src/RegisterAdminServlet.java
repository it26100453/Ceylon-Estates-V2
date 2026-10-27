import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// handles the admin registration form submission
public class RegisterAdminServlet extends HttpServlet {

    private AdminService adminService = new AdminService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String role = req.getParameter("role");
        String permissions = req.getParameter("permissions");

        // basic validation, nothing fancy
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            req.setAttribute("error", "Username and password are required");
            req.getRequestDispatcher("adminRegister.jsp").forward(req, resp);
            return;
        }

        String newId = adminService.generateNextId();
        Admin newAdmin = new Admin(newId, username, password, email, role, permissions);

        boolean success = adminService.createAdmin(newAdmin);

        if (success) {
            resp.sendRedirect("adminList");
        } else {
            req.setAttribute("error", "Username already taken");
            req.getRequestDispatcher("adminRegister.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("adminRegister.jsp").forward(req, resp);
    }
}
