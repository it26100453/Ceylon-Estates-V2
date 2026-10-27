import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// shows the list of all admins - the admin management panel
public class ViewAdminsServlet extends HttpServlet {

    private AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Admin> admins = adminService.getAllAdmins();
        req.setAttribute("admins", admins);
        req.getRequestDispatcher("adminList.jsp").forward(req, resp);
    }
}
