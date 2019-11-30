package tech.laihz.treex.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import tech.laihz.treex.entity.LoginUser;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    private static Logger logger = LoggerFactory.getLogger(JWTUtil.class);
    private static  final String SECRET = "treex_secret";
    private static  final long EXPIRATION =1800L;

    public static String createToken(LoginUser user){
        Date expireDate = new Date(System.currentTimeMillis()+EXPIRATION*1000);
        Map<String,Object> map= new HashMap<>();
        map.put("alg","HS256");
        map.put("typ","JWT");
        return JWT.create()
                .withHeader(map)
                .withClaim("name",user.getName())
                .withExpiresAt(expireDate)
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static Map<String, Claim> verifyToken(String token){
        final Jedis jedis = new Jedis("127.0.0.1",6379);
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
            logger.info(jwt.getPayload());

        }catch (Exception e){
            logger.error(e.getMessage());
            logger.error("token decode fail");
            return null;
        }
        try{
            if(jedis.get(token).equals(jwt.getClaim("name").asString())){
                return jwt.getClaims();
            }else return null;
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;

    }
}
