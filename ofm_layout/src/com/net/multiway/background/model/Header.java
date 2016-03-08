package com.net.multiway.background.model;

public class Header {

    /**
     *
     */
    private String STPKSTR_s16;
    private int PKLEN_ui4;
    private int REV_ui4;
    private int PKTPY_ui4;
    private int SRC_ui4;
    private int DST_ui4;
    private int PKID_ui4;
    private int RSVD1_ui4;
    private int CMDCODE_ui4;
    private int DATALEN_4;
    private int RSVD2_ui4;

    public Header(int PKLEN_ui4, int CMDCODE_ui4, int DATALEN_4) {

        this.STPKSTR_s16 = "\0NtOTDRModule\0\0\0\0";
        this.PKLEN_ui4 = PKLEN_ui4;
        this.REV_ui4 = 1;
        this.PKTPY_ui4 = 0;
        this.SRC_ui4 = 0;
        this.DST_ui4 = 0;
        this.PKID_ui4 = 0;
        this.RSVD1_ui4 = 0xffffeeee;
        this.CMDCODE_ui4 = CMDCODE_ui4;
        this.DATALEN_4 = DATALEN_4;
        this.RSVD2_ui4 = 0xffffeeee;

    }

    public String getSTPKSTR_s16() {
        return STPKSTR_s16;
    }

    public void setSTPKSTR_s16(String sTPKSTR_s16) {
        STPKSTR_s16 = sTPKSTR_s16;
    }

    public int getPKLEN_ui4() {
        return PKLEN_ui4;
    }

    public void setPKLEN_ui4(int pKLEN_ui4) {
        PKLEN_ui4 = pKLEN_ui4;
    }

    public int getREV_ui4() {
        return REV_ui4;
    }

    public void setREV_ui4(int rEV_ui4) {
        REV_ui4 = rEV_ui4;
    }

    public int getPKTPY_ui4() {
        return PKTPY_ui4;
    }

    public void setPKTPY_ui4(int pKTPY_ui4) {
        PKTPY_ui4 = pKTPY_ui4;
    }

    public int getSRC_ui4() {
        return SRC_ui4;
    }

    public void setSRC_ui4(int sRC_ui4) {
        SRC_ui4 = sRC_ui4;
    }

    public int getDST_ui4() {
        return DST_ui4;
    }

    public void setDST_ui4(int dST_ui4) {
        DST_ui4 = dST_ui4;
    }

    public int getPKID_ui4() {
        return PKID_ui4;
    }

    public void setPKID_ui4(int pKID_ui4) {
        PKID_ui4 = pKID_ui4;
    }

    public int getRSVD1_ui4() {
        return RSVD1_ui4;
    }

    public void setRSVD1_ui4(int rSVD1_ui4) {
        RSVD1_ui4 = rSVD1_ui4;
    }

    public int getCMDCODE_ui4() {
        return CMDCODE_ui4;
    }

    public void setCMDCODE_ui4(int cMDCODE_ui4) {
        CMDCODE_ui4 = cMDCODE_ui4;
    }

    public int getRSVD2_ui4() {
        return RSVD2_ui4;
    }

    public void setRSVD2_ui4(int rSVD_ui4) {
        RSVD2_ui4 = rSVD_ui4;
    }

    public int getDATALEN_4() {
        return DATALEN_4;
    }

    public void setDATALEN_4(int dATALEN_4) {
        DATALEN_4 = dATALEN_4;
    }

}
