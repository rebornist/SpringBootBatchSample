package kr.sshsys.batchsample.sample.util;

import kr.sshsys.batchsample.sample.entity.Sample;
import kr.sshsys.batchsample.sample.mapper.SampleMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * SampleFileWriteItemReader
 * ItemReader 설정을 담당하는 클래스
 *
 * @version 1.0.0
 * @since 2023-07-25
 * @auther sshsys
 *
 */
@Component
@StepScope
public class SampleFileWriteItemReader<T> implements ItemReader<Sample> {

    /** SqlSessionTemplate */
    private final SqlSessionTemplate sqlSessionTemplate;

    /** idx */
    private int idx = 0;

    /**
     * 생성자
     * @param sqlSessionTemplate
     */
    public SampleFileWriteItemReader(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
	
    /**
     * ItemReader 실제 동작 메소드
     * @return Sample
     */
    @Override
    public Sample read() {
        /** SampleMapper를 통해 DB에서 데이터를 가져옴 */
        List<Sample> list = sqlSessionTemplate.getMapper(SampleMapper.class).searchAll();

        /** 가져온 데이터를 하나씩 리턴 */
        if (idx < list.size()) {
            Sample item = list.get(idx);
            idx++;
            return item;
        }

        return null;
    }

}
