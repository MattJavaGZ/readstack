package matt.pas.readstack.client.signup;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matt.pas.readstack.domain.api.UserRegistration;
import matt.pas.readstack.domain.api.UserService;

import java.io.IOException;

@WebServlet("/signup")
public class SignupController extends HttpServlet {

    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final UserRegistration user = getUserData(request);
        userService.register(user);
        response.sendRedirect(request.getContextPath());
    }

    private UserRegistration getUserData (HttpServletRequest request) {
        final String username = request.getParameter("username");
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");
        return new UserRegistration(username, email, password);
    }
}
