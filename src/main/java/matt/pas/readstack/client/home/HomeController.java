package matt.pas.readstack.client.home;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matt.pas.readstack.domain.api.*;

import java.io.IOException;
import java.util.List;

@WebServlet("")
public class HomeController extends HttpServlet {

    DiscoveryService discoveryService = new DiscoveryService();
    CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final List<DiscoveryBasicInfo> allDiscoveries = discoveryService.findAll();
        discoveryService.favoriteCheck(request, allDiscoveries);
        request.setAttribute("discoveries", allDiscoveries);

        final List<CategoryName> allCategoryNames = categoryService.findAllCategoryNames();
        request.setAttribute("allCategoryNames", allCategoryNames);

        final boolean block = request.isUserInRole("BLOCK");
        request.setAttribute("block", block);

        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }


}
