package matt.pas.readstack.client.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matt.pas.readstack.domain.api.UserService;

import java.io.IOException;

@WebServlet("/userpanel/admin/block")

@ServletSecurity(
        @HttpConstraint(rolesAllowed = "ADMIN")
)


public class UserBlockController extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String userName = request.getParameter("username");
        userService.blockUser(userName);
        response.sendRedirect(request.getContextPath() + "/userpanel/admin");
    }


}
