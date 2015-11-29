package com.net.multiway.background.data;

import com.net.multiway.background.utils.Utils;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Phelipe
 */
@Entity
@Table(name = "DATA0X1000")
@XmlRootElement

public class Data0x1000 implements Data, Serializable {

    private int measureMode;
    private int optimizeMode;
    private float reflectionThreshold;
    private int enabledRefresh;
    private int refreshCycle;
    private int testWaveLength;
    private int measuringRangeOfTest;
    private int testPulseWidth;
    private int measuringTime;
    private float refractiveIndex;
    private float endThreshold;
    private float nonReflactionThreshold;
    private Long ID;

    public Data0x1000() {
    }

    public Data0x1000(int measureMode_4, int optimizeMode_4, float reflectionThreshold_4, int enabledRefresh_4,
            int refreshCycle_4, int testWaveLength_4, int measuringRangeOfTest_4, int testPulseWidth_4,
            int measuringTime_4, float refractiveIndex_4, float endThreshold_4, float nonReflactionThreshold_4) {

        this.measureMode = measureMode_4;
        this.optimizeMode = optimizeMode_4;
        this.reflectionThreshold = reflectionThreshold_4;
        this.enabledRefresh = enabledRefresh_4;
        this.refreshCycle = refreshCycle_4;
        this.testWaveLength = testWaveLength_4;
        this.measuringRangeOfTest = measuringRangeOfTest_4;
        this.testPulseWidth = testPulseWidth_4;
        this.measuringTime = measuringTime_4;
        this.refractiveIndex = refractiveIndex_4;
        this.endThreshold = endThreshold_4;
        this.nonReflactionThreshold = nonReflactionThreshold_4;
    }

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    @Basic(optional = false)
    @Column(name = "MEASURE_MODE")
  
    public int getMeasureMode() {
        return measureMode;
    }

    public void setMeasureMode(int measureMode) {
        this.measureMode = measureMode;
    }

    @Basic(optional = false)
    @Column(name = "OPTIMIZE_MODE")
    public int getOptimizeMode() {
        return optimizeMode;
    }

    public void setOptimizeMode(int optimizeMode) {
        this.optimizeMode = optimizeMode;
    }

    @Basic(optional = false)
    @Column(name = "REFLECTION_THRESHOLD")
    public float getReflectionThreshold() {
        return reflectionThreshold;
    }

    public void setReflectionThreshold(float reflectionThreshold) {
        this.reflectionThreshold = reflectionThreshold;
    }

    @Basic(optional = false)
    @Column(name = "ENABLE_REFRESH")
    public int getEnabledRefresh() {
        return enabledRefresh;
    }

    public void setEnabledRefresh(int enabledRefresh) {
        this.enabledRefresh = enabledRefresh;
    }

    @Basic(optional = false)
    @Column(name = "REFRESH_CYCLE")
    public int getRefreshCycle() {
        return refreshCycle;
    }

    public void setRefreshCycle(int refreshCycle) {
        this.refreshCycle = refreshCycle;
    }

    @Basic(optional = false)
    @Column(name = "TEST_WAVE_LENGHT")
    public int getTestWaveLength() {
        return testWaveLength;
    }

    public void setTestWaveLength(int testWaveLength) {
        this.testWaveLength = testWaveLength;
    }

    @Basic(optional = false)
    @Column(name = "MEASURING_RANGE")
    public int getMeasuringRangeOfTest() {
        return measuringRangeOfTest;
    }

    public void setMeasuringRangeOfTest(int measuringRangeOfTest) {
        this.measuringRangeOfTest = measuringRangeOfTest;
    }

    @Basic(optional = false)
    @Column(name = "TEST_PULSEWIDTH")
    public int getTestPulseWidth() {
        return testPulseWidth;
    }

    public void setTestPulseWidth(int testPulseWidth) {
        this.testPulseWidth = testPulseWidth;
    }

    @Basic(optional = false)
    @Column(name = "MEASURING_TIME")
    public int getMeasuringTime() {
        return measuringTime;
    }

    public void setMeasuringTime(int measuringTime) {
        this.measuringTime = measuringTime;
    }

    @Basic(optional = false)
    @Column(name = "REFRACTIVE_INDEX")
    public float getRefractiveIndex() {
        return refractiveIndex;
    }

    public void setRefractiveIndex(float refractiveIndex) {
        this.refractiveIndex = refractiveIndex;
    }

    @Basic(optional = false)
    @Column(name = "END_THRESHOLD")
    public float getEndThreshold() {
        return endThreshold;
    }

    public void setEndThreshold(float endThreshold) {
        this.endThreshold = endThreshold;
    }

    @Basic(optional = false)
    @Column(name = "NONREFLACTION_THRESHOLD")
    public float getNonReflactionThreshold() {
        return nonReflactionThreshold;
    }

    public void setNonReflactionThreshold(float nonReflactionThreshold) {
        this.nonReflactionThreshold = nonReflactionThreshold;
    }

    public byte[] getData() {
        byte[] b = new byte[48];
        byte[] c = new byte[4];
        int i = 0;

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.intToByteArray(measureMode)[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.intToByteArray(optimizeMode)[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.floatToByteArray(reflectionThreshold)[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.intToByteArray(enabledRefresh)[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.intToByteArray(refreshCycle)[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.intToByteArray(testWaveLength)[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.intToByteArray(measuringRangeOfTest)[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.intToByteArray(testPulseWidth)[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.intToByteArray(measuringTime)[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.floatToByteArray(refractiveIndex)[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.floatToByteArray(endThreshold)[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.floatToByteArray(nonReflactionThreshold)[j];
        }

        return b;

    }
}
