package matt.pas.readstack.client.comment;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matt.pas.readstack.domain.api.CommentFullInfo;
import matt.pas.readstack.domain.api.CommentService;

import java.io.IOException;

@WebServlet("/userpanel/admin/editcomment")
@ServletSecurity(
        @HttpConstraint(rolesAllowed = "ADMIN")
)

public class EditCommentController extends HttpServlet {
    CommentService commentService = new CommentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int commentId = Integer.parseInt(request.getParameter("id"));
        final CommentFullInfo comment = commentService.findCommentById(commentId);
        request.setAttribute("comment", comment);
        final int discoveryid = Integer.parseInt(request.getParameter("discoveryid"));
        request.setAttribute("discoveryid", discoveryid);
        request.getRequestDispatcher("/WEB-INF/views/comment-edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int commentId = Integer.parseInt(request.getParameter("id"));
        final String commentContent = request.getParameter("commentContent");
        commentService.editComment(commentId, commentContent);
        final int discoveryid = Integer.parseInt(request.getParameter("discoveryid"));
        response.sendRedirect(request.getContextPath() + "/discovery?id=" + discoveryid);
    }
}
