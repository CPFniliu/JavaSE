package util.validate;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class RequireUtil
{

    /**
     *  Ϊ�����׳��쳣
     *  @param str  
     */
    public static void requireStringNonBlank(String str){
        if (str == null || str.length() == 0){
            ExceptionUtil.throwRuntimeException("�ַ���Ϊ��!");
        }
    }
    
    /**
     *  Ϊ�����׳��쳣
     *  @param strs    
     */
    public static void requireStringNonBlank(String... strs){
        for (String str : strs){
            requireStringNonBlank(str);
        }
    }
    
    /**
     *  Ϊ�����׳��쳣
     *  @param str  
     */
    public static <T> void requireCollectNonBlank(Collection<T> collection){
        Objects.requireNonNull(collection);
        if (collection.isEmpty()){
            ExceptionUtil.throwRuntimeException("����Ϊ��!");
        }
    }
    
    /**
     *  Ϊ�����׳��쳣
     *  @param str  
     */
    public static void requireMapNonBlank(Map<?, ?> map){
        Objects.requireNonNull(map);
        if (map.isEmpty()){
            ExceptionUtil.throwRuntimeException("mapΪ��!");
        }
    }
    
    /**
     *  flag��Ϊtrue���׳��쳣
     *  @param flag    
     */
    public static void requireBooleanTrue(boolean flag){
        if (! flag){
            ExceptionUtil.throwRuntimeException();
        }
    }
    
    public static String concat(String str1, String str2){
        Objects.requireNonNull(str1);
        if (str2 != null && !"".equals(str2)){
            return str1 + str2;
        }
        return str1;
    }
}
