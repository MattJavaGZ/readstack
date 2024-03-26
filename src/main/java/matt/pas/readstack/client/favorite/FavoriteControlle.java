package matt.pas.readstack.client.favorite;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matt.pas.readstack.domain.api.DiscoveryBasicInfo;

import java.io.IOException;
import java.util.List;

@WebServlet("/favorite")
public class FavoriteControlle extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final List<DiscoveryBasicInfo> favoriteList = (List<DiscoveryBasicInfo>)session.getAttribute("discoveries");
        session.setAttribute("discoveries", favoriteList);
        request.getRequestDispatcher("/WEB-INF/views/favorite.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
