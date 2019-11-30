package tech.laihz.treex.filter;

import com.auth0.jwt.interfaces.Claim;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import tech.laihz.treex.utils.JWTUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@WebFilter(filterName = "JWTFilter", urlPatterns = "/intro/*")
public class JWTFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final Jedis jedis = new Jedis("127.0.0.1",6379);
        response.setCharacterEncoding("UTF-8");
        //获取 header里的token
        final String token = request.getHeader("authorization");
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
        }
        // Except OPTIONS, other request should be checked by JWT
        else {
            if (token == null) {
                response.getWriter().write("{\"msg\":\"no permission\"}");
                return;
            }

            Map<String, Claim> userData = JWTUtil.verifyToken(token);
            if (userData == null) {
                response.getWriter().write("fail");
                logger.error("fail");
                return;
            }
            String name = userData.get("name").asString();
            //拦截器 拿到用户信息，放到request中
            request.setAttribute("name", name);

            chain.doFilter(req,res);
        }
    }
    @Override
    public void destroy() {

    }
}
