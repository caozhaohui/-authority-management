package org.lanqiao.controller;

import org.lanqiao.pojo.User;
import org.lanqiao.service.UserService;
import org.lanqiao.util.JavaMailUtil;
import org.lanqiao.util.JsonWriter;
import org.lanqiao.util.RandomUtil;
import org.lanqiao.util.htmlText;
import org.lanqiao.vo.JsonResult;
import org.lanqiao.vo.Menu;
import org.springframework.web.bind.annotation.*;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Properties;

// @CrossOrigin(origins = "http://localhost:8080", maxAge = 3600,allowedHeaders=
// "http://localhost:8080",allowCredentials = "true")

@CrossOrigin
@RequestMapping("/api")
@RestController
public class UserController {

    @RequestMapping(value = "/login")
    public JsonResult login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request) {
        HttpSession session = request.getSession();

        JsonResult jsonResult = null;
        try {
            UserService service = new UserService();
            User user = service.queryByName(username);
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                session.setAttribute("loginUserKey", user);
                User a = (User) session.getAttribute("loginUserKey");
                System.out.println("user" + a.toString());
                jsonResult = new JsonResult("200", "登陆成功", user);
            } else {
                jsonResult = new JsonResult("404", "登陆失败", "");
            }
        } catch (Exception e) {
            jsonResult = new JsonResult("500", "数据错误", "");
        }
        return jsonResult;
    }

    // 退出请求
    @RequestMapping("/logout")
    public JsonResult logout(HttpServletResponse response, HttpSession httpSession) throws Exception {
        JsonResult jsonResult = new JsonResult("200", "退出登录成功", "");
        // 清空session
        httpSession.invalidate();
        JsonWriter.writer(response, jsonResult);
        return jsonResult;
    }


    @RequestMapping("/menu")
    public JsonResult menu(HttpServletRequest request, @RequestParam("id") String id) {
        JsonResult jsonResult = null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUserKey");
        System.out.println("userName：" + user.getName());
        try {
            UserService userService = new UserService();
            List <Menu> menus = userService.menuList(user.getId().toString());
            //      List<Menu> menus = userService.menuList(id);
            if (menus.size() > 0) {
                jsonResult = new JsonResult("200", "查询成功", menus);

            } else {
                jsonResult = new JsonResult("404", "查询失败", "");
            }
        } catch (Exception e) {
            e.getStackTrace();
            jsonResult = new JsonResult("500", "数据错误", "");
        }
        return jsonResult;
    }

    // 查询用户列表
    @RequestMapping("/sys/user/view")
    public JsonResult userList() {
        JsonResult jsonResult = null;
        try {
            UserService userService = new UserService();
            List <User> users = userService.userList();
            if (users.size() > 0) {
                jsonResult = new JsonResult("200", "查询成功", users);
            } else {
                jsonResult = new JsonResult("404", "查询失败", "");
            }
        } catch (Exception e) {
            e.getStackTrace();
            jsonResult = new JsonResult("500", "数据错误", "");
        }
        return jsonResult;
    }

    // 添加用户
    @RequestMapping("/sys/user/add")
    public JsonResult addUser(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("mobile") String mobile,
            @RequestParam("code") String code,
            HttpSession session,
            @RequestParam("role_id") String role_id) {
        User user1 = (User) session.getAttribute("loginUserKey");
        String create_by = user1.getName();
        JsonResult jsonResult = null;
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setCreateBy(create_by);
        // 缺个时间
        try {
            UserService userService = new UserService();
            //      需要给用户添加角色
            int i = userService.addUser(user);
            if (i > 0) {
                jsonResult = new JsonResult("200", "添加成功", "");
            } else {
                jsonResult = new JsonResult("404", "添加失败", "");
            }
        } catch (Exception e) {
            e.getStackTrace();
            jsonResult = new JsonResult("500", "数据错误", "");
        }
        return jsonResult;
    }

    // 更新用户
    @RequestMapping("/sys/user/update")
    public JsonResult updateUser(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("mobile") String mobile,
            HttpSession session,
            @RequestParam("role_id") String role_id) {
        User user1 = (User) session.getAttribute("loginUserKey");
        String update_by = user1.getName();
        JsonResult jsonResult = null;
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setLastUpdateBy(update_by);
        // 缺个时间
        try {
            UserService userService = new UserService();
            //      需要给用户更新角色
            int i = userService.updateUser(user);
            if (i > 0) {
                jsonResult = new JsonResult("200", "更改成功", "");
            } else {
                jsonResult = new JsonResult("404", "更改失败", "");
            }
        } catch (Exception e) {
            e.getStackTrace();
            jsonResult = new JsonResult("500", "数据错误", "");
        }
        return jsonResult;
    }

    @RequestMapping("/sys/user/delete")
    public JsonResult deleteUser(@RequestParam("id") String id, HttpServletResponse response) {
        UserService userService = new UserService();
        JsonResult jsonResult = null;
        try {
            int i = userService.deleteUser(id);
            if (i == 1) {
                jsonResult = new JsonResult("200", "删除用户成功", "");
            } else {
                jsonResult = new JsonResult("400", "删除用户失败", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult("500", "删除用户异常", "");
        }
        return jsonResult;
    }


    @RequestMapping("/sendEmail")
    public JsonResult sendEmail(@RequestParam("email") String email, HttpServletRequest req) {

        System.out.println("邮箱发送功能");
        try {
            JavaMailUtil.receiveMailAccount = email; // 给用户输入的邮箱发送邮件
            // 1、创建参数配置，用于连接邮箱服务器的参数配置
            Properties props = new Properties();
            // 开启debug调试
            props.setProperty("mail.debug", "true");
            // 发送服务器需要身份验证
            props.setProperty("mail.smtp.auth", "true");
            // 设置右键服务器的主机名
            props.setProperty("mail.host", JavaMailUtil.emailSMTPHost);
            // 发送邮件协议名称
            props.setProperty("mail.transport.protocol", "smtp");
            // 2、根据配置创建会话对象，用于和邮件服务器交互
            Session session = Session.getInstance(props);
            // 设置debug，可以查看详细的发送log
            session.setDebug(true);
            // 3、创建一封邮件
            String code = RandomUtil.getRandom();
            System.out.println("邮箱验证码：" + code);
            String html = htmlText.html(code);
            MimeMessage message = JavaMailUtil.creatMimeMessage(session, JavaMailUtil.emailAccount,
                    JavaMailUtil.receiveMailAccount, html);

            // 4、根据session获取邮件传输对象
            Transport transport = session.getTransport();
            // 5、使用邮箱账号和密码连接邮箱服务器emailAccount必须与message中的发件人邮箱一致，否则报错
            transport.connect(JavaMailUtil.emailAccount, JavaMailUtil.emailPassword);
            // 6、发送邮件,发送所有收件人地址
            transport.sendMessage(message, message.getAllRecipients());
            // 7、关闭连接
            transport.close();
            //  写入session
            req.getSession().setAttribute("code", code);
        } catch (Exception e) {
            e.printStackTrace();
            req.getSession().setAttribute("error", "邮件发送失败");
        }

        return null;
    }

}

