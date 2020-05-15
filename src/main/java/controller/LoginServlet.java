package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @description 登录请求处理类
 * @author tgx
 */
@WebServlet(value="/login.do")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //接收表单信息
        String username = request.getParameter("id");
        String password = request.getParameter("pwd");
        String verifyc  = request.getParameter("verifycode");

        System.out.println("username=" + username);
        System.out.println("password=" + password);
        System.out.println("verifyc=" + verifyc);
        //设置回显
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.setAttribute("verifycode", verifyc);
        //获取验证码
        String svc =(String) request.getSession().getAttribute("sessionverify");


        //自己设置初始账号和密码
        User user =new User();

        user.setId("tgx");
        user.setPwd("123");



        if(!svc.equalsIgnoreCase(verifyc)){
            System.out.println("验证码错误");
            request.setAttribute("loginError", "* 验证码错误");
            //设置编码格式
            response.setContentType("text/html;charset=GB18030");
            //返回html页面
            response.getWriter().println("<html>");
            response.getWriter().println("<head>");
            response.getWriter().println("<title>登录信息</title>");
            response.getWriter().println("</head>");
            response.getWriter().println("<body>");
            response.getWriter().println("验证码错误");
            response.getWriter().println("</body>");
            response.getWriter().println("</html>");
            return;
        }
        if(user!=null){
            if(user.getPwd().equals(password)&&user.getId().equals(username)){
              request.getSession().setAttribute("user", user);

                //设置编码格式
                response.setContentType("text/html;charset=GB18030");
                //返回html页面
                response.getWriter().println("<html>");
                response.getWriter().println("<head>");
                response.getWriter().println("<title>登录信息</title>");
                response.getWriter().println("</head>");
                response.getWriter().println("<body>");
                response.getWriter().println("欢迎【" + username + "】用户登录成功！！！");
                response.getWriter().println("</body>");
                response.getWriter().println("</html>");
            }else {
                System.out.println("密码错误");
                request.setAttribute("loginError", "* 密码错误");
                //设置编码格式
                response.setContentType("text/html;charset=GB18030");
                //返回html页面
                response.getWriter().println("<html>");
                response.getWriter().println("<head>");
                response.getWriter().println("<title>登录信息</title>");
                response.getWriter().println("</head>");
                response.getWriter().println("<body>");
                response.getWriter().println("密码错误");
                response.getWriter().println("</body>");
                response.getWriter().println("</html>");
            }
        }else {
            System.out.println("用户不存在");
            request.setAttribute("loginError", "* 用户不存在");
            //设置编码格式
            response.setContentType("text/html;charset=GB18030");
            //返回html页面
            response.getWriter().println("<html>");
            response.getWriter().println("<head>");
            response.getWriter().println("<title>登录信息</title>");
            response.getWriter().println("</head>");
            response.getWriter().println("<body>");
            response.getWriter().println("用户不存在");
            response.getWriter().println("</body>");
            response.getWriter().println("</html>");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
