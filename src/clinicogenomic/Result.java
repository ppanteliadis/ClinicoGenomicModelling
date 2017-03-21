package clinicogenomic;

public class Result {
	private final float percentage, minPercentage, maxPercentage;
	
	public Result(float p, float minP, float maxP) {
		this.minPercentage = minP;
		this.maxPercentage = maxP;
		this.percentage = p;
	}

	public float getPercentage() {
		return percentage;
	}

	public float getMinPercentage() {
		return minPercentage;
	}

	public float getMaxPercentage() {
		return maxPercentage;
	}
}
