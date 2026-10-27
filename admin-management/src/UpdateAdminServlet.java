import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// handles editing an existing admin's details
public class UpdateAdminServlet extends HttpServlet {

    private AdminService adminService = new AdminService();

    // loads the edit form with the current details filled in
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Admin admin = adminService.findById(id);

        if (admin == null) {
            resp.sendRedirect("adminList");
            return;
        }

        req.setAttribute("admin", admin);
        req.getRequestDispatcher("adminEdit.jsp").forward(req, resp);
    }

    // actually saves the changes
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("userId");
        Admin existing = adminService.findById(id);

        if (existing == null) {
            resp.sendRedirect("adminList");
            return;
        }

        // only update the fields that make sense to edit - keep the password unless they typed a new one
        existing.setEmail(req.getParameter("email"));
        existing.setRole(req.getParameter("role"));
        existing.setPermissions(req.getParameter("permissions"));

        String newPassword = req.getParameter("password");
        if (newPassword != null && !newPassword.trim().isEmpty()) {
            existing.setPassword(newPassword);
        }

        adminService.updateAdmin(existing);
        resp.sendRedirect("adminList");
    }
}
