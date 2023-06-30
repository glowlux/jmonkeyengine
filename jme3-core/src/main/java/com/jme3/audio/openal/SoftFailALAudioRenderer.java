package com.jme3.audio.openal;

import com.jme3.audio.openal.AL;
import com.jme3.audio.openal.ALAudioRenderer;
import com.jme3.audio.openal.ALC;
import com.jme3.audio.openal.EFX;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * HACK: This audio renderer prevents {@link UnsatisfiedLinkError} from crashing the program.
 *      Unfortunately, this error seems to be occurring from time to time in editor,
 *      The cause is most likely Windows computers going into sleep and pausing audio devices,
 *      We don't want the whole program to crash because of an audio issue.
 */
public class SoftFailALAudioRenderer extends ALAudioRenderer {

    private static final Logger logger = Logger.getLogger(SoftFailALAudioRenderer.class.getName());

    public SoftFailALAudioRenderer(AL al, ALC alc, EFX efx) {
        super(al, alc, efx);
    }

    @Override
    public void run() {
        try {
            super.run();
        } catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            logger.log(Level.WARNING, "Ignoring UnsatisfiedLinkError error...", unsatisfiedLinkError);
        }
    }
}
