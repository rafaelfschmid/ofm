package com.net.multiway.background.data;

import com.net.multiway.background.utils.Utils;

public class Data0x1000 implements Data {
	private int measureMode_4;
	private int optimizeMode_4;
	private float reflectionThreshold_4;
	private int enabledRefresh_4;
	private int refreshCycle_4;
	private int testWaveLength_4;
	private int measuringRangeOfTest_4;
	private int testPulseWidth_4;
	private int measuringTime_4;
	private float refractiveIndex_4;
	private float endThreshold_4;
	private float nonReflactionThreshold_4;

	public Data0x1000(int measureMode_4, int optimizeMode_4, float reflectionThreshold_4, int enabledRefresh_4,
			int refreshCycle_4, int testWaveLength_4, int measuringRangeOfTest_4, int testPulseWidth_4,
			int measuringTime_4, float refractiveIndex_4, float endThreshold_4, float nonReflactionThreshold_4) {

		this.measureMode_4 = measureMode_4;
		this.optimizeMode_4 = optimizeMode_4;
		this.reflectionThreshold_4 = reflectionThreshold_4;
		this.enabledRefresh_4 = enabledRefresh_4;
		this.refreshCycle_4 = refreshCycle_4;
		this.testWaveLength_4 = testWaveLength_4;
		this.measuringRangeOfTest_4 = measuringRangeOfTest_4;
		this.testPulseWidth_4 = testPulseWidth_4;
		this.measuringTime_4 = measuringTime_4;
		this.refractiveIndex_4 = refractiveIndex_4;
		this.endThreshold_4 = endThreshold_4;
		this.nonReflactionThreshold_4 = nonReflactionThreshold_4;
	}

	public byte[] getData() {
		byte[] b = new byte[48];
		byte[] c = new byte[4];
		int i = 0;

		for (int j = 0; j < 4; j++, i++)
			b[i] = Utils.intToByteArray(measureMode_4)[j];

		for (int j = 0; j < 4; j++, i++)
			b[i] = Utils.intToByteArray(optimizeMode_4)[j];

		for (int j = 0; j < 4; j++, i++)
			b[i] = Utils.floatToByteArray(reflectionThreshold_4)[j];

		for (int j = 0; j < 4; j++, i++)
			b[i] = Utils.intToByteArray(enabledRefresh_4)[j];

		for (int j = 0; j < 4; j++, i++)
			b[i] = Utils.intToByteArray(refreshCycle_4)[j];

		for (int j = 0; j < 4; j++, i++)
			b[i] = Utils.intToByteArray(testWaveLength_4)[j];

		for (int j = 0; j < 4; j++, i++)
			b[i] = Utils.intToByteArray(measuringRangeOfTest_4)[j];

		for (int j = 0; j < 4; j++, i++)
			b[i] = Utils.intToByteArray(testPulseWidth_4)[j];

		for (int j = 0; j < 4; j++, i++)
			b[i] = Utils.intToByteArray(measuringTime_4)[j];

		for (int j = 0; j < 4; j++, i++)
			b[i] = Utils.floatToByteArray(refractiveIndex_4)[j];

		for (int j = 0; j < 4; j++, i++)
			b[i] = Utils.floatToByteArray(endThreshold_4)[j];

		for (int j = 0; j < 4; j++, i++)
			b[i] = Utils.floatToByteArray(nonReflactionThreshold_4)[j];

		return b;

	}

	public int getMeasureMode_4() {
		return measureMode_4;
	}

	public void setMeasureMode_4(int measureMode_4) {
		this.measureMode_4 = measureMode_4;
	}

	public int getOptimizeMode_4() {
		return optimizeMode_4;
	}

	public void setOptimizeMode_4(int optimizeMode_4) {
		this.optimizeMode_4 = optimizeMode_4;
	}

	public float getReflectionThreshold_4() {
		return reflectionThreshold_4;
	}

	public void setReflectionThreshold_4(float reflectionThreshold_4) {
		this.reflectionThreshold_4 = reflectionThreshold_4;
	}

	public int getEnabledRefresh_4() {
		return enabledRefresh_4;
	}

	public void setEnabledRefresh_4(int enabledRefresh_4) {
		this.enabledRefresh_4 = enabledRefresh_4;
	}

	public int getRefreshCycle_4() {
		return refreshCycle_4;
	}

	public void setRefreshCycle_4(int refreshCycle_4) {
		this.refreshCycle_4 = refreshCycle_4;
	}

	public int getTestWaveLength_4() {
		return testWaveLength_4;
	}

	public void setTestWaveLength_4(int testWaveLength_4) {
		this.testWaveLength_4 = testWaveLength_4;
	}

	public int getMeasuringRangeOfTest_4() {
		return measuringRangeOfTest_4;
	}

	public void setMeasuringRangeOfTest_4(int measuringRangeOfTest_4) {
		this.measuringRangeOfTest_4 = measuringRangeOfTest_4;
	}

	public int getTestPulseWidth_4() {
		return testPulseWidth_4;
	}

	public void setTestPulseWidth_4(int testPulseWidth_4) {
		this.testPulseWidth_4 = testPulseWidth_4;
	}

	public int getMeasuringTime_4() {
		return measuringTime_4;
	}

	public void setMeasuringTime_4(int measuringTime_4) {
		this.measuringTime_4 = measuringTime_4;
	}

	public float getRefractiveIndex_4() {
		return refractiveIndex_4;
	}

	public void setRefractiveIndex_4(float refractiveIndex_4) {
		this.refractiveIndex_4 = refractiveIndex_4;
	}

	public float getEndThreshold_4() {
		return endThreshold_4;
	}

	public void setEndThreshold_4(float endThreshold_4) {
		this.endThreshold_4 = endThreshold_4;
	}

	public float getNonReflactionThreshold_4() {
		return nonReflactionThreshold_4;
	}

	public void setNonReflactionThreshold_4(float nonReflactionThreshold_4) {
		this.nonReflactionThreshold_4 = nonReflactionThreshold_4;
	}

}
