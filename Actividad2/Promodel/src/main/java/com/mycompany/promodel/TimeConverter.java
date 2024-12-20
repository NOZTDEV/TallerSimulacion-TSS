package com.mycompany.promodel;

public class TimeConverter {

    // Convertir segundos a minutos
    public double secondsToMinutes(double seconds) {
        return seconds / 60;
    }

    // Convertir minutos a segundos
    public double minutesToSeconds(double minutes) {
        return minutes * 60;
    }

    // Convertir minutos a horas
    public double minutesToHours(double minutes) {
        return minutes / 60;
    }

    // Convertir horas a minutos
    public double hoursToMinutes(double hours) {
        return hours * 60;
    }

    // Convertir segundos a horas
    public double secondsToHours(double seconds) {
        return seconds / 3600;
    }

    // Convertir horas a segundos
    public double hoursToSeconds(double hours) {
        return hours * 3600;
    }
}

