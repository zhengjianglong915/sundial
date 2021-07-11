package cn.wegostack.sundial.common.utils;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author zhengjianglong
 * @since 2021-07-11
 */
public class HashCodeUtil {

    public static int hashCode(String key) throws UnsupportedEncodingException {
        return Math.abs(DigestUtils.md5DigestAsHex(key.getBytes("UTF-8")).hashCode());
    }
}
