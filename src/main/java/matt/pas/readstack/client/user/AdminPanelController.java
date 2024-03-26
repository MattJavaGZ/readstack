package matt.pas.readstack.client.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matt.pas.readstack.domain.api.StatsFullInfo;
import matt.pas.readstack.domain.api.StatsService;
import matt.pas.readstack.domain.api.UserService;
import matt.pas.readstack.domain.api.UserToAdminList;

import java.io.IOException;
import java.util.List;

@WebServlet("/userpanel/admin")

@ServletSecurity(
        @HttpConstraint(rolesAllowed = "ADMIN")
)

public class AdminPanelController extends HttpServlet {

    StatsService statsService = new StatsService();
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String page = request.getParameter("page");
        if (page == null) {
            final StatsFullInfo statsFullInfo = statsService.statsToPrint();
            request.setAttribute("statsFullInfo", statsFullInfo);
            request.getRequestDispatcher("/WEB-INF/views/user/admin-page-stats.jsp").forward(request, response);
        }
        else if (page.equals("users")) {
            final List<UserToAdminList> allUsers = userService.allUsers();
            request.setAttribute("allUsers", allUsers);
            request.getRequestDispatcher("/WEB-INF/views/user/admin-page-users.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
