package matt.pas.readstack.client.comment;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matt.pas.readstack.domain.api.CommentSaveRequest;
import matt.pas.readstack.domain.api.CommentService;

import java.io.IOException;

@WebServlet("/discovery/comment")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = {"USER", "ADMIN"}),
                @HttpMethodConstraint(value = "POST", rolesAllowed = {"USER", "ADMIN"})
        }
)

public class AddCommentController extends HttpServlet {

    CommentService commentService = new CommentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int discoveryId = Integer.parseInt(request.getParameter("id"));
        final CommentSaveRequest commentSaveRequest = parToComment(request, discoveryId);
        commentService.addComment(commentSaveRequest);
        response.sendRedirect(request.getContextPath()+"/discovery?id=" + discoveryId);
    }

    private CommentSaveRequest parToComment(HttpServletRequest request, int discoveryId) {
        return new CommentSaveRequest(
                discoveryId,
                request.getUserPrincipal().getName(),
                request.getParameter("comment")
        );
    }
}
