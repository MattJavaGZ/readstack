package matt.pas.readstack.client.user;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matt.pas.readstack.domain.api.DiscoveryBasicInfo;
import matt.pas.readstack.domain.api.DiscoveryService;
import matt.pas.readstack.domain.api.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/userpanel/user")
@ServletSecurity(
        @HttpConstraint(rolesAllowed = "USER")
)
public class UserPanelController extends HttpServlet {
    DiscoveryService discoveryService = new DiscoveryService();
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String userName = request.getUserPrincipal().getName();
        final int userId = userService.userIdByUserName(userName);

        final String type = request.getParameter("type");
        if (type == null) {
            final List<DiscoveryBasicInfo> discoveries = discoveryService.discoveriesByUserVote(userId);
            request.setAttribute("discoveries", discoveries);
            discoveryService.favoriteCheck(request, discoveries);
            request.setAttribute("title", "Ocenione znaleziska");
            request.getRequestDispatcher("/WEB-INF/views/user/user-page.jsp").forward(request, response);
        }
        if (type.equals("comment")) {
            final List<DiscoveryBasicInfo> discoveries = discoveryService.discoveriesByUserComment(userId);
            request.setAttribute("discoveries", discoveries);
            discoveryService.favoriteCheck(request, discoveries);
            request.setAttribute("title", "Skomentowane znaleziska");
            request.getRequestDispatcher("/WEB-INF/views/user/user-page.jsp").forward(request, response);
        }
    }

}
