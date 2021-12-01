package starbbs.controller;
/*

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

*/
/**
 * 验证码Controller
 * 主要生成验证码
 * 获取验证码 String code = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
 *//*

@Controller
@RequestMapping("/kaptcha")
public class CaptchaController {

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private static Logger logger = LoggerFactory.getLogger(CaptchaController.class);


    @RequestMapping("getKaptchaImage")
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        logger.debug("******************验证码是: " + code + "******************");


        response.setHeader("Access-Control-Allow-Credentials","true");
        String origin=request.getHeader("login");
        response.setHeader("Access-Control-Allow-Origin",origin);

        response.setHeader("SameSite", "None");
        response.setHeader ("Set-Cookie", response.getHeader ("Set-Cookie") + "; SameSite=None");
        response.setHeader ("Set-Cookie", response.getHeader ("Set-Cookie") + "; Secure=true");
        response.setDateHeader("Expires", 0);

        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = captchaProducer.createText();

        // store the text in the session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();

        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        System.out.println(request.getSession().getId());
        System.out.println("Session 验证码是：" + request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY));
        return null;
    }
}

*/


import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

@Controller
@RequestMapping("/user/kaptcha")
public class CaptchaController {

    @Autowired
    private Producer captchaProducer;

    @RequestMapping
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        /*  System.out.println("******************验证码是: " + code + "******************");*/


        response.setHeader("SameSite", "None");
        response.setHeader("Set-Cookie", response.getHeader("Set-Cookie") + "; SameSite=None");
        response.setHeader("Set-Cookie", response.getHeader("Set-Cookie") + "; Secure=true");
/*        response.setHeader ("Set-Cookie", response.getHeader ("Set-Cookie") + "; SameSite=None");
        response.setHeader ("Set-Cookie", response.getHeader ("Set-Cookie") + "; Secure=true");
        response.setHeader ("Set-Cookie", response.getHeader ("Set-Cookie") + "; HttpOnly=true");*/

        response.setDateHeader("Expires", 0);

        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = captchaProducer.createText();

        // store the text in the session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();

        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
      /*  System.out.println(request.getSession().getId());
        System.out.println("Session 验证码是：" + request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY));*/
        return null;
    }

}
