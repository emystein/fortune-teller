package io.spring.cloud.samples.horoscope;

import java.util.Date;

public class Horoscope {

	private Date date = new Date();
    private String prediction;

    public Horoscope(String string) {
        prediction = string;
    }

    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }
    
}
