package matt.pas.readstack.client.discovery;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matt.pas.readstack.domain.api.*;
import matt.pas.readstack.domain.visit.VisitDao;

import java.io.IOException;
import java.util.List;

@WebServlet("/discovery")
public class SingleDiscoveryController extends HttpServlet {

    CommentService commentService = new CommentService();
    DiscoveryService discoveryService = new DiscoveryService();
    VisitService visitService = new VisitService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int discoveryId = Integer.parseInt(request.getParameter("id"));
        visitService.saveVisit(discoveryId);
        final DiscoveryBasicInfo discovery = discoveryService.findById(discoveryId).orElseThrow();
        request.setAttribute("discovery", discovery);
        final List<CommentFullInfo> commentsByDiscoveryId = commentService.findCommentsByDiscoveryId(discoveryId);
        request.setAttribute("commentsByDiscoveryId", commentsByDiscoveryId);
        final boolean admin = request.isUserInRole("ADMIN");
        request.setAttribute("admin", admin);
        request.getRequestDispatcher("/WEB-INF/views/discovery.jsp").forward(request, response);
    }

}
