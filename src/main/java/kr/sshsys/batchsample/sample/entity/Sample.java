package kr.sshsys.batchsample.sample.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Sample Entity
 *
 * @version 1.0.0
 * @since 2023-07-25
 * @auther sshsys
 */
@Getter
@Entity
public class Sample {

    @Id
    private Long id;

    private String instlPlceNm;

    private String instlPlceDtil;

    private String svcProvdCmpnyNm;

    private String wifiSsid;

    private String instlYm;

    private String lnmAdrs;

    private String rnAdrs;

    private String admdCd;

    private String admdNm;

    private String lgdngCd;

    private String lgdngNm;

    private String mngmtInsttNm;

    private String telno;

    private Double la;

    private Double lo;

    private String dataStdrDe;

}
