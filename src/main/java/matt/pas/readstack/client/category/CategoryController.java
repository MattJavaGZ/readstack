package matt.pas.readstack.client.category;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matt.pas.readstack.domain.api.CategoryFullInfo;
import matt.pas.readstack.domain.api.CategoryService;
import matt.pas.readstack.domain.api.DiscoveryBasicInfo;
import matt.pas.readstack.domain.api.DiscoveryService;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@WebServlet("/category")
public class CategoryController extends HttpServlet {

    DiscoveryService discoveryService = new DiscoveryService();
    CategoryService categoryService = new CategoryService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int categoryId = Integer.parseInt(request.getParameter("id"));
        final CategoryFullInfo category = categoryService.findById(categoryId)
                .orElseThrow();
        request.setAttribute("category", category);
        final List<DiscoveryBasicInfo> discoveries = discoveryService.findByCategory(categoryId);
        discoveryService.favoriteCheck(request, discoveries);
        request.setAttribute("discoveries", discoveries);
        request.getRequestDispatcher("/WEB-INF/views/category.jsp").forward(request, response);
    }


}
