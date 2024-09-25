package com.automacao.acess_control.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_statedata")
public class StateData implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double temperature;
    private Double humidity;
    private Instant timestamp;
    private Double dewPoint;

    public StateData(){
    }

    public StateData(Double temperature, Double humidity) {
        this.id = null;
        this.temperature = temperature;
        this.humidity = humidity;
        this.setDewPoint(temperature, humidity);
        this.timestamp = Instant.now();
    }
    
    public StateData(StateData data) {
    	this.id = null;
        this.temperature = data.temperature;
        this.humidity = data.humidity;
        this.setDewPoint(data.temperature, data.humidity);
        this.timestamp = Instant.now();
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double temperature, Double humidity) {
        Double calc1 = ((17.27 * temperature) / (237.7 + temperature)) + Math.log(humidity / 100);
        Double calc2 = (237.7 * calc1) / (17.27 - calc1);
        this.dewPoint = calc2;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateData stateData = (StateData) o;
        return Objects.equals(id, stateData.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
