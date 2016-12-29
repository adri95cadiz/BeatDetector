/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmreader;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;

/**
 *
 * @author Adri
 */
public class BPMReader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PitchDetectionHandler handler = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult pitchDetectionResult,AudioEvent audioEvent) {
                System.out.println(pitchDetectionResult.getPitch() + " " + audioEvent.getTimeStamp());
            }
        };
        AudioDispatcher adp = AudioDispatcherFactory.fromDefaultMicrophone(2048, 0, 0);
        adp.addAudioProcessor(new PitchProcessor(PitchEstimationAlgorithm.YIN, 44100, 2048, handler));
        adp.run();
    }
    
}
