/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmreader;

import java.lang.Math;
import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;
import javax.sound.sampled.LineUnavailableException;
/*import be.tarsos.dsp.onsets.ComplexOnsetDetector;
import be.tarsos.dsp.AudioProcessor;
import be.hogent.tarsos.dsp.util.FFT;*/

/**
 *
 * @author Adri
 */
public class BPMReader {

    /**
     * @param args the command line arguments
     * @throws javax.sound.sampled.LineUnavailableException
     */
    
    public static void main(String[] args) throws LineUnavailableException {
        
        final int sampleRate = 44100;        
        final int bufferSize = 4096;
        
        PitchDetectionHandler pitchhandler = new PitchDetectionHandler() {
            private double lastProbability = 0;          
            private double lastTimeStamp = 0;         
            private double lastPitch = 0;                    
            private double lastBpm = 120;                   
            private double adaptedBpm = 0;
            @Override
            public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
                if(pitchDetectionResult.getPitch() != -1){
                    double timeStamp = audioEvent.getTimeStamp();
                    float pitch = pitchDetectionResult.getPitch();
                    float probability = pitchDetectionResult.getProbability();
                    //double rms = audioEvent.getRMS() * 100;
                    //String message = String.format("\nFrecuencia detectada: %.2fs: %.2fHz ( %.2f probabilidad, media cuadratica: %.5f )", timeStamp,pitch,probability,rms);
                    //System.out.println(message);   
                    double difPitch = (pitch - lastPitch);
                    double timeBetween = (timeStamp - lastTimeStamp);
                    if(timeBetween > 0.1){ 
                        if(difPitch > 0.1 || difPitch < -0.1){
                            if (probability > lastProbability + 0.1 || (lastProbability > 0.95 && probability > lastProbability + 0.005)) {
                                System.out.println("\nBeat detectado en: " + timeStamp + " con frecuencia de: " + pitch);
                                //System.out.println(probability + " " + lastProbability);
                                //System.out.println(difPitch + " = " + pitch + " " + lastPitch);
                                System.out.println("Tiempo desde el anterior beat: " + timeBetween);
                                lastTimeStamp = timeStamp;
                                lastPitch = pitch;
                                double bpm = 0;
                                int beat = 4;
                                if(timeBetween != timeStamp){
                                    bpm = 60/timeBetween;
                                    System.out.println("bpm crudo = " + bpm);
                                    while(bpm < 60){
                                        bpm*=2;  
                                        beat/=2;
                                    }
                                    while(bpm > 200){
                                        bpm/=2;
                                        beat*=2;
                                    }
                                }
                                if(adaptedBpm == 0){
                                    adaptedBpm = bpm;
                                }
                                System.out.println("bpm neto = " + bpm);
                                if(bpm > lastBpm){                 
                                    adaptedBpm++;
                                    System.out.println("Bpm en aumento. Bpm adaptados = " + adaptedBpm);  
                                } else{
                                    adaptedBpm--;
                                    System.out.println("Bpm en disminución. Bpm adaptados = " + adaptedBpm);
                                }
                                System.out.println("Duracion del beat = 1/" + beat);
                                lastBpm = bpm;     
                                int note = (int) Math.floor(Math.log(pitch/440.0)/Math.log(2.0) * 12 + 69);
                                System.out.println("Nota = " + note);
                                int octava;
                                int nota;
                                if(note < 12) {
                                    octava = 0;
                                    nota = note;
                                } else if(note < 24){                                    
                                    octava = 1;
                                    nota = note - 12;
                                } else if(note < 36){
                                    octava = 2;
                                    nota = note - 24;
                                } else if(note < 48){
                                    octava = 3;
                                    nota = note - 36;
                                } else if(note < 60){
                                    octava = 4;
                                    nota = note - 48;
                                } else if(note < 72){  
                                    octava = 5;  
                                    nota = note - 60;                                    
                                } else if(note < 84){
                                    octava = 6;
                                    nota = note - 72;
                                } else if(note < 96){
                                    octava = 7;
                                    nota = note - 80;
                                } else if(note < 108){
                                    octava = 8;
                                    nota = note - 96;
                                } else if(note < 120){
                                    octava = 9;
                                    nota = note - 108;
                                } else {
                                    octava = 10;
                                    nota = note - 120;
                                }
                                switch(nota){
                                    case 0: System.out.println("La nota tocada es C o Do"); break;
                                    case 1: System.out.println("La nota tocada es C# o Do#"); break;
                                    case 2: System.out.println("La nota tocada es D o Re"); break;
                                    case 3: System.out.println("La nota tocada es D# o Re#"); break;
                                    case 4: System.out.println("La nota tocada es E o Mi"); break;
                                    case 5: System.out.println("La nota tocada es F o Fa"); break;
                                    case 6: System.out.println("La nota tocada es F# o Fa#"); break;
                                    case 7: System.out.println("La nota tocada es G o Sol"); break;
                                    case 8: System.out.println("La nota tocada es G# o Sol#"); break;
                                    case 9: System.out.println("La nota tocada es A o La"); break;
                                    case 10: System.out.println("La nota tocada es A# o La#"); break;
                                    case 11: System.out.println("La nota tocada es B o Si"); break;                                      
                                }
                                System.out.println("Estas tocando en la octava: " + octava);
                            }
                        }
                    }
                    lastProbability = probability;
                }
            }              
        };
        
        AudioDispatcher adp = AudioDispatcherFactory.fromDefaultMicrophone(sampleRate, bufferSize, 0);
        adp.addAudioProcessor(new PitchProcessor(PitchEstimationAlgorithm.YIN, sampleRate, bufferSize, pitchhandler));
        adp.run();
    }
}
