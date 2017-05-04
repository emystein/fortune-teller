package io.spring.cloud.samples.horoscope;

public class Horoscope {

    private String prediction;

    public Horoscope(String string) {
        prediction = string;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }
    
}
