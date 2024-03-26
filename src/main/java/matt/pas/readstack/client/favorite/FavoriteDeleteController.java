package matt.pas.readstack.client.favorite;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matt.pas.readstack.domain.api.DiscoveryBasicInfo;
import matt.pas.readstack.domain.api.DiscoveryService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/favorite/delete")
public class FavoriteDeleteController extends HttpServlet {

    DiscoveryService discoveryService = new DiscoveryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int id = Integer.parseInt(request.getParameter("id"));
        final HttpSession session = request.getSession();
        final List<DiscoveryBasicInfo> discoveries =  (List<DiscoveryBasicInfo>)session.getAttribute("discoveries");

        final DiscoveryBasicInfo discovery = discoveryService.findById(id).orElseThrow();
        discoveries.remove(discovery);

        response.sendRedirect(request.getContextPath());
    }


}
