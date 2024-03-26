package matt.pas.readstack.client.discovery;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matt.pas.readstack.domain.api.CategoryName;
import matt.pas.readstack.domain.api.CategoryService;
import matt.pas.readstack.domain.api.DiscoverySaveRequest;
import matt.pas.readstack.domain.api.DiscoveryService;
import matt.pas.readstack.domain.category.Category;
import matt.pas.readstack.domain.category.CategoryDao;

import java.io.IOException;
import java.util.List;

@WebServlet("/discovery/add")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = {"USER", "ADMIN"}),
                @HttpMethodConstraint(value = "POST", rolesAllowed = {"USER", "ADMIN"})
        }
)
public class AddDiscoveryController extends HttpServlet {
    private CategoryService categoryService = new CategoryService();
    private DiscoveryService discoveryService = new DiscoveryService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final List<CategoryName> categories = categoryService.findAllCategoryNames();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/views/add-discovery.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final DiscoverySaveRequest saveRequest = createSaveRequest(request);
        discoveryService.add(saveRequest);
        response.sendRedirect(request.getContextPath()+"/");
    }

    private DiscoverySaveRequest createSaveRequest(HttpServletRequest request) {
        final String userName = request.getUserPrincipal().getName();
        final String title = request.getParameter("title");
        final String url = request.getParameter("url");
        final String description = request.getParameter("description");
        final int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        return new DiscoverySaveRequest(title, url, description, categoryId, userName);
    }
}
