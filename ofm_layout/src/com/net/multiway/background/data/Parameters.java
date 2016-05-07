package com.net.multiway.background.data;

import com.net.multiway.background.utils.Utils;
import java.io.Serializable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Phelipe
 */
@Entity
@Table(name = "parameters")
@XmlRootElement

public class Parameters implements IData, Serializable {

    private ObjectProperty<Integer> measuringRangeOfTest;
    private ObjectProperty<Integer> testPulseWidth;
    private ObjectProperty<Integer> measuringTime;
    private ObjectProperty<Integer> testWaveLength;
    private ObjectProperty<Integer> measureMode;
    private ObjectProperty<Float> refractiveIndex;
    private ObjectProperty<Float> nonReflactionThreshold;
    private ObjectProperty<Float> endThreshold;
    private ObjectProperty<Float> reflectionThreshold;

    private ObjectProperty<Integer> optimizeMode;
    private ObjectProperty<Integer> enabledRefresh;
    private ObjectProperty<Integer> refreshCycle;
	private ObjectProperty<Integer> cycleTime;

	

    private Long ID;

    public Parameters() {
        this.measuringRangeOfTest = new SimpleObjectProperty<>();
        this.testPulseWidth = new SimpleObjectProperty<>();
        this.measuringTime = new SimpleObjectProperty<>();
        this.testWaveLength = new SimpleObjectProperty<>();
        this.measureMode = new SimpleObjectProperty<>();
        this.refractiveIndex = new SimpleObjectProperty<>();
        this.nonReflactionThreshold = new SimpleObjectProperty<>();
        this.endThreshold = new SimpleObjectProperty<>();
        this.reflectionThreshold = new SimpleObjectProperty<>();

        this.optimizeMode = new SimpleObjectProperty<>();
        this.enabledRefresh = new SimpleObjectProperty<>();
        this.refreshCycle = new SimpleObjectProperty<>();
		this.cycleTime = new SimpleObjectProperty<>();

    }

    public Parameters(int measuringRangeOfTest, int testPulseWidth, int measuringTime,
            int testWaveLength, int measureMode, float refractiveIndex,
            float nonReflactionThreshold, float endThreshold, float reflectionThreshold,
            int optimizeMode, int enabledRefresh, int refreshCycle, int cycleTime) {

        this.measuringRangeOfTest = new SimpleObjectProperty<>(measuringRangeOfTest);
        this.testPulseWidth = new SimpleObjectProperty<>(testPulseWidth);
        this.measuringTime = new SimpleObjectProperty<>(measuringTime);
        this.testWaveLength = new SimpleObjectProperty<>(testWaveLength);
        this.measureMode = new SimpleObjectProperty<>(measureMode);
        this.refractiveIndex = new SimpleObjectProperty<>(refractiveIndex);
        this.nonReflactionThreshold = new SimpleObjectProperty<>(nonReflactionThreshold);
        this.endThreshold = new SimpleObjectProperty<>(endThreshold);
        this.reflectionThreshold = new SimpleObjectProperty<>(reflectionThreshold);

        this.optimizeMode = new SimpleObjectProperty<>(optimizeMode);
        this.enabledRefresh = new SimpleObjectProperty<>(enabledRefresh);
        this.refreshCycle = new SimpleObjectProperty<>(refreshCycle);
		this.cycleTime = new SimpleObjectProperty<>(cycleTime);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    @Basic(optional = false)
    @Column(name = "measure_mode")

    public Integer getMeasureMode() {
        return measureMode.get();
    }

    public void setMeasureMode(int measureMode) {
        this.measureMode.set(measureMode);
    }

    @Basic(optional = false)
    @Column(name = "optimize_mode")
    public Integer getOptimizeMode() {
        return optimizeMode.get();
    }

    public void setOptimizeMode(int optimizeMode) {
        this.optimizeMode.set(optimizeMode);
    }

    @Basic(optional = false)
    @Column(name = "reflection_threshold")
    public Float getReflectionThreshold() {
        return reflectionThreshold.get();
    }

    public void setReflectionThreshold(float reflectionThreshold) {
        this.reflectionThreshold.set(reflectionThreshold);
    }

    @Basic(optional = false)
    @Column(name = "enable_refresh")
    public Integer getEnabledRefresh() {
        return enabledRefresh.get();
    }

    public void setEnabledRefresh(int enabledRefresh) {
        this.enabledRefresh.set(enabledRefresh);
    }

    @Basic(optional = false)
    @Column(name = "refresh_cycle")
    public Integer getRefreshCycle() {
        return refreshCycle.get();
    }

    public void setRefreshCycle(int refreshCycle) {
        this.refreshCycle.set(refreshCycle);
    }

    @Basic(optional = false)
    @Column(name = "test_wave_length")
    public Integer getTestWaveLength() {
        return testWaveLength.get();
    }

    public void setTestWaveLength(int testWaveLength) {
        this.testWaveLength.set(testWaveLength);
    }

    @Basic(optional = false)
    @Column(name = "measuring_range")
    public Integer getMeasuringRangeOfTest() {
        return measuringRangeOfTest.get();
    }

    public void setMeasuringRangeOfTest(int measuringRangeOfTest) {
        this.measuringRangeOfTest.set(measuringRangeOfTest);
    }

    @Basic(optional = false)
    @Column(name = "test_pulse_width")
    public Integer getTestPulseWidth() {
        return testPulseWidth.get();
    }

    public void setTestPulseWidth(int testPulseWidth) {
        this.testPulseWidth.set(testPulseWidth);
    }

    @Basic(optional = false)
    @Column(name = "measuring_time")
    public Integer getMeasuringTime() {
        return measuringTime.get();
    }

    public void setMeasuringTime(int measuringTime) {
        this.measuringTime.set(measuringTime);
    }

    @Basic(optional = false)
    @Column(name = "refractive_index")
    public Float getRefractiveIndex() {
        return refractiveIndex.get();
    }

    public void setRefractiveIndex(float refractiveIndex) {
        this.refractiveIndex.set(refractiveIndex);
    }

    @Basic(optional = false)
    @Column(name = "end_threshold")
    public Float getEndThreshold() {
        return endThreshold.get();
    }

    public void setEndThreshold(float endThreshold) {
        this.endThreshold.set(endThreshold);
    }

    @Basic(optional = false)
    @Column(name = "non_reflaction_threshold")
    public Float getNonReflactionThreshold() {
        return nonReflactionThreshold.get();
    }

    public void setNonReflactionThreshold(float nonReflactionThreshold) {
        this.nonReflactionThreshold.set(nonReflactionThreshold);
    }

	@Basic(optional = false)
    @Column(name = "cycle_time")
	public Integer getCycleTime() {
		return cycleTime.get();
	}

	public void setCycleTime(int cycleTime) {
		this.cycleTime.set(cycleTime);
	}
	
    public byte[] takeData() {
        byte[] b = new byte[48];
        byte[] c = new byte[4];
        int i = 0;

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.intToByteArray(measureMode.get())[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.intToByteArray(optimizeMode.get())[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.floatToByteArray(reflectionThreshold.get())[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.intToByteArray(enabledRefresh.get())[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.intToByteArray(refreshCycle.get())[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.intToByteArray(testWaveLength.get())[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.intToByteArray(measuringRangeOfTest.get())[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.intToByteArray(testPulseWidth.get())[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.intToByteArray(measuringTime.get())[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.floatToByteArray(refractiveIndex.get())[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.floatToByteArray(endThreshold.get())[j];
        }

        for (int j = 0; j < 4; j++, i++) {
            b[i] = Utils.floatToByteArray(nonReflactionThreshold.get())[j];
        }

        return b;

    }

    public void copy(Parameters data) {
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
