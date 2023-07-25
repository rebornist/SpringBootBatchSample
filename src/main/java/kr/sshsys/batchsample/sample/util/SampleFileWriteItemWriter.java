package kr.sshsys.batchsample.sample.util;

import kr.sshsys.batchsample.common.util.ObjectAttributeHandler;
import kr.sshsys.batchsample.sample.entity.Sample;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * SampleFileWriteItemWriter
 * ItemWriter 설정을 담당하는 클래스
 *
 * @version 1.0.0
 * @since 2023-07-25
 * @auther sshsys
 *
 */
@Component
@StepScope
public class SampleFileWriteItemWriter<T> implements ItemWriter<Sample> {

    /** filepath */
    private final String filepath = "data/output_" + LocalDateTime.now().toString().replaceAll("[\\:\\-\\.]", "");

    /**
     * ItemWriter 실제 동작 메소드
     * @param list
     * @throws Exception
     */
    @Override
    public void write(List<? extends Sample> list) throws Exception {

        /** FlatFileItemWriter를 통해 파일에 데이터를 쓰기 */
        FlatFileItemWriter<Sample> writer = new FlatFileItemWriter<>();

        /** 데이터의 필드명을 추출 */
        String[] fieldNames = ObjectAttributeHandler.getFieldNames(list.get(0));

        /** 파일 경로 설정 */
        writer.setResource(new FileSystemResource(filepath + ".txt"));

        /** 파일에 데이터를 추가할 수 있도록 설정 */
        writer.setAppendAllowed(true);

        /** 파일 인코딩 설정 */
        writer.setEncoding("UTF-8");

        /** 파일 라인 구분자 설정 */
        writer.setLineSeparator("\n");

		/** 파일 헤더 설정 */
        writer.setHeaderCallback(writer1 -> writer1.write(String.join("|", fieldNames)));

        /** 파일 필드명 설정 */
        writer.setLineAggregator(new DelimitedLineAggregator<Sample>() {
            {
                setDelimiter("|");
                setFieldExtractor(new BeanWrapperFieldExtractor<Sample>() {
                    {
                        setNames(fieldNames);
                    }
                });
            }
        });

        /** ExecutionContext 설정 */
        writer.open(new ExecutionContext());

        /** 데이터를 파일에 쓰기 */
        writer.write(list);

        /** ExecutionContext 닫기 */
        writer.close();
    }
}