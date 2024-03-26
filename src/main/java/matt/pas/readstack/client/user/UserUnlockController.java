package matt.pas.readstack.client.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matt.pas.readstack.domain.api.UserService;

import java.io.IOException;

@WebServlet("/userpanel/admin/unlock")

@ServletSecurity(
        @HttpConstraint(rolesAllowed = "ADMIN")
)

public class UserUnlockController extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String userName = request.getParameter("username");
        userService.unlockUser(userName);
        response.sendRedirect(request.getContextPath() + "/userpanel/admin");
    }


}
