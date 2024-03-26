package matt.pas.readstack.client.comment;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matt.pas.readstack.domain.api.CommentService;

import java.io.IOException;

@WebServlet("/userpanel/admin/deletecomment")
@ServletSecurity(
        @HttpConstraint(rolesAllowed = "ADMIN")
)

public class DeleteCommentController extends HttpServlet {

    CommentService commentService = new CommentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int commentId = Integer.parseInt(request.getParameter("id"));
        final int discoveryId = Integer.parseInt(request.getParameter("discoveryid"));
        commentService.deleteComment(commentId);
        response.sendRedirect(request.getContextPath() + "/discovery?id=" + discoveryId);;
    }

}
