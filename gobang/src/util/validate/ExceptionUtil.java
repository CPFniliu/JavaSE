package util.validate;

/**
 * @author CPF
 * @version [�汾��, 2018��4��27��]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾] 
 */
public class ExceptionUtil
{
    public static final String ILLEGAL_VALUE = "�����˲��ó��ֵ�ֵ";

    /**
     * �׳��쳣���
     */
    public static final boolean THROW_FLAG = true;
    
    /**
     * @exception/throws new RuntimeException()
     */
    public static void throwRuntimeException(){
        if (THROW_FLAG){
            throw new RuntimeException();
        }
    }
    
    /**
     * @exception/throws new NullPointerException();
     */
    public static void throwNullPointerException() {
        if (THROW_FLAG){
            throw new NullPointerException();
        }
    }
    
    /**
     *  @param message    
     * @exception/throw new RuntimeException(message);
     */
    public static void throwRuntimeException(String message){
        if (THROW_FLAG){
            throw new RuntimeException(message);
        }
    }

    /**
     *  ���ַǷ�ֵ
     *  @exception/throw RuntimeException(ILLEGAL_VALUE);
     */
    public static void throwIllegalValue(){
        throwRuntimeException(ILLEGAL_VALUE);
    }
    
}
