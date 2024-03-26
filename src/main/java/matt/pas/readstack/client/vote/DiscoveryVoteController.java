package matt.pas.readstack.client.vote;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matt.pas.readstack.domain.api.DiscoveryVote;
import matt.pas.readstack.domain.api.DiscoveryVoteService;

import java.io.IOException;

@WebServlet("/discovery/vote")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = {"USER", "ADMIN"})
        }
)

public class DiscoveryVoteController extends HttpServlet {
    DiscoveryVoteService discoveryVoteService = new DiscoveryVoteService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final DiscoveryVote discoveryVote = createDiscoveryVote(request);
        discoveryVoteService.addVote(discoveryVote);
        final int discoveryId = Integer.parseInt(request.getParameter("id"));
        response.sendRedirect(request.getContextPath() + "/discovery?id=" + discoveryId);
    }

    private static DiscoveryVote createDiscoveryVote(HttpServletRequest request) {
        final String userName = request.getUserPrincipal().getName();
        final int discoveryId = Integer.parseInt(request.getParameter("id"));
        final String type = request.getParameter("type");
        return new DiscoveryVote(userName, discoveryId, type);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
