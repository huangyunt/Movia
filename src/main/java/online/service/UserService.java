package online.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import online.datamanager.DataManager;
import online.datamanager.User;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserService extends HttpServlet{
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");

            // 获取用户id和用户对象
            String userId = request.getParameter("id");
            User user = DataManager.getInstance().getUserById(Integer.parseInt(userId));

            if(null != user) {
                ObjectMapper mapper = new ObjectMapper();
                String jsonUser = mapper.writeValueAsString(user);
                response.getWriter().println(jsonUser);
            }
            else {
                response.getWriter().println("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("");
        }
    }
}
