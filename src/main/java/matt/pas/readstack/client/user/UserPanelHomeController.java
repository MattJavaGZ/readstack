package matt.pas.readstack.client.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/userpanel")

@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = {"USER", "ADMIN"})
        }
)

public class UserPanelHomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.isUserInRole("USER")) {
            response.sendRedirect(request.getContextPath()+ "/userpanel/user");
        } else if (request.isUserInRole("ADMIN")) {
            response.sendRedirect(request.getContextPath()+ "/userpanel/admin");
        }
    }
}

