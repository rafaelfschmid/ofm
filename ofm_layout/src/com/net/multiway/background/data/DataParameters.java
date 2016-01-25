package com.net.multiway.background.data;

import com.net.multiway.background.utils.Utils;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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

public class DataParameters implements Data, Serializable {

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

    public DataParameters() {
    }

    public DataParameters(Long ID,int measureMode, int optimizeMode, float reflectionThreshold, int enabledRefresh,
            int refreshCycle, int testWaveLength, int measuringRangeOfTest, int testPulseWidth,
            int measuringTime, float refractiveIndex, float endThreshold, float nonReflactionThreshold) {
		
		this.ID = ID;
        this.measureMode = measureMode;
        this.optimizeMode = optimizeMode;
        this.reflectionThreshold = reflectionThreshold;
        this.enabledRefresh = enabledRefresh;
        this.refreshCycle = refreshCycle;
        this.testWaveLength = testWaveLength;
        this.measuringRangeOfTest = measuringRangeOfTest;
        this.testPulseWidth = testPulseWidth;
        this.measuringTime = measuringTime;
        this.refractiveIndex = refractiveIndex;
        this.endThreshold = endThreshold;
        this.nonReflactionThreshold = nonReflactionThreshold;
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

    public byte[] takeData() {
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
	
	public void copy(DataParameters data)
	{
		setEnabledRefresh(data.getEnabledRefresh());
		setEndThreshold(data.getEndThreshold());
		setID(data.getID());
		setMeasureMode(data.getMeasureMode());
		setMeasuringRangeOfTest(data.getMeasuringRangeOfTest());
		setMeasuringTime(data.getMeasuringTime());
		setNonReflactionThreshold(data.getNonReflactionThreshold());
		setOptimizeMode(data.getOptimizeMode());
		setReflectionThreshold(data.getReflectionThreshold());
		setRefractiveIndex(data.getRefractiveIndex());
		setRefreshCycle(data.getRefreshCycle());
		setTestPulseWidth(data.getTestPulseWidth());
		setTestWaveLength(data.getTestWaveLength());
	}
}
