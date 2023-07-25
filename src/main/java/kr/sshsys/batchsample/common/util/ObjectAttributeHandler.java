package kr.sshsys.batchsample.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * ObjectAttributeHandler
 * Object의 필드명을 가져오는 클래스
 *
 * @version 1.0.0
 * @since 2023-07-25
 * @auther sshsys
 *
 */
public class ObjectAttributeHandler {

    /**
     * Object Key의 필드명을 가져오는 메소드
     * @param object
     * @return String[]
     */
    public static String[] getFieldNames(Object object) {
        
        /** 필드명을 담을 List 생성 */
        List<String> fieldNames = new ArrayList<>();

        /** Object의 필드명을 가져와서 List에 담기 */
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }

         /** List를 String[]로 변환하여 리턴 */
        return fieldNames.toArray(new String[0]);
    }
}
