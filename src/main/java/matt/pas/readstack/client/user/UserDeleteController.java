package matt.pas.readstack.client.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matt.pas.readstack.domain.api.UserService;

import java.io.IOException;

@WebServlet("/userpanel/admin/delete")
@ServletSecurity(
        @HttpConstraint(rolesAllowed = "ADMIN")
)

public class UserDeleteController extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = request.getParameter("username");
        final int id = Integer.parseInt(request.getParameter("id"));
        userService.deleteUser(username, id);
        response.sendRedirect(request.getContextPath() + "/userpanel/admin");
    }

}
