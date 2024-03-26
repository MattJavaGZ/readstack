package matt.pas.readstack.client.favorite;

import com.mysql.cj.AbstractQuery;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matt.pas.readstack.domain.api.DiscoveryBasicInfo;
import matt.pas.readstack.domain.api.DiscoveryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/favorite/add")

public class FavoriteAddController extends HttpServlet {
    DiscoveryService discoveryService = new DiscoveryService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int discoveryId = Integer.parseInt(request.getParameter("id"));
        final HttpSession session = request.getSession();

        if (session.getAttribute("discoveries") == null){
           List<DiscoveryBasicInfo> favoriteList = new ArrayList<>();
           final DiscoveryBasicInfo discovery = discoveryService.findById(discoveryId).orElseThrow();
           discovery.setIsFavorite(2);
           favoriteList.add(discovery);
           session.setAttribute("discoveries", favoriteList);
           response.sendRedirect(request.getContextPath());
       }
       else {
           final List<DiscoveryBasicInfo> favoriteList = (List<DiscoveryBasicInfo>)session.getAttribute("discoveries");
           final DiscoveryBasicInfo discovery = discoveryService.findById(discoveryId).orElseThrow();
           if (!favoriteList.contains(discovery)) {
               discovery.setIsFavorite(2);
               favoriteList.add(discovery);
               session.setAttribute("discoveries", favoriteList);
           }
           response.sendRedirect(request.getContextPath());
       }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
