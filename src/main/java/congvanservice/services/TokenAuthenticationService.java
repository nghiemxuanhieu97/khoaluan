package congvanservice.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security
        .authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import static java.util.Collections.emptyList;

public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "secret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse res, String username) throws IOException {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
        res.setContentType("application/json");
        StringBuilder body = new StringBuilder().append("{")
                .append("\"token\":\"")
                .append(JWT)
                .append("\",")
                .append("\"maTaiKhoan\": 1,")
                .append("\"tenTaiKhoan\": \"Hieu\",")
                .append("\"hoTen\": \"Nghiêm Xuân Hiếu\",")
                .append("\"email\": \"nghiemxuanhieu97@gmail.com\",")
                .append("\"diaChi\": \"Tô Ngọc Vân, Đà Lạt\",")
                .append("\"phanQuyen\": \"Admin\",")
                .append("\"trangThai\": \"false\",")
                .append("\"sdt\": \"0367896040\",")
                .append("}");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(body.toString());
        res.getWriter().flush();
        res.getWriter().close();
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                    null;
        }
        return null;
    }
}
