package com.zl.springboot.security.util

import org.springframework.util.StringUtils;

import java.security.MessageDigest;
/**
 * Created by geely
 */
public class MD5Util {

    private fun byteArrayToHexString(b: ByteArray): String {
        val resultSb = StringBuffer()
        for (i in b.indices)
            resultSb.append(byteToHexString(b[i]))

        return resultSb.toString()
    }

    private fun byteToHexString(b: Byte): String {
        var n = b.toInt()
        if (n < 0)
            n += 256
        val d1 = n / 16
        val d2 = n % 16
        return hexDigits[d1] + hexDigits[d2]
    }

    /**
     * 返回大写MD5
     *
     * @param origin
     * @param charsetname
     * @return
     */
    private fun MD5Encode(origin: String, charsetname: String?): String {
        var resultString: String? = null
        try {
            resultString = String(origin)
            val md = MessageDigest.getInstance("MD5")
            if (charsetname == null || "" == charsetname)
                resultString = byteArrayToHexString(md.digest(resultString.toByteArray()))
            else
                resultString = byteArrayToHexString(md.digest(resultString.toByteArray(charset(charsetname))))
        } catch (exception: Exception) {
        }

        return resultString!!.toUpperCase()
    }

    fun MD5EncodeUtf8(origin: String): String {
        var origin = origin
        origin = origin + PropertiesUtil.getProperty("password.salt", "")
        return MD5Encode(origin, "utf-8")
    }
    private val hexDigits = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f")

}
