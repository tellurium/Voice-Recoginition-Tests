package cn.the.moduletest.units;

import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.content.Context;

import java.io.File;

public class Recorder implements OnErrorListener, OnCompletionListener {

    public static final String SAMPLE_PREFIX = "recording";

    long mSampleStart = 0;
    int mSampleLength = 0;
    File mSampleFile = null;

    private MediaPlayer mPlayer;
    private MediaRecorder mRecorder;

    /*
     * STATUS
     */
    public enum RecorderStatus {
        IDLE_STATE, RECORDING_STATE, RECORDING_PAUSED, RECORDING_STOPPED, PLAYING_STATE, PLAYING_PAUSED 
    }

    public enum RecorderError {
        NO_ERROR, SDCARD_ACCESS_ERROR, INTERNAL_ERROR, IN_CALL_RECORD_ERROR
    }

    private RecorderStatus current_status = RecorderStatus.IDLE_STATE;

    public Recorder() {
    }

    /*
     * Start Recording
     */
    public void startRecording(int outputfileformat, String extension, Context context) {
        stop();

        
        if (mSampleFile == null) {
            File sampleDir = Environment.getExternalStorageDirectory();

            //Just for the possibility of broken sdcard support on the device
            if (!sampleDir.canWrite()) {
                sampleDir = new File("/sdcard/sdcard");
            }

            try {
                //mSampleFile = File.createTempFile();

            } catch (Exception e) {
                
            }
        }

    }

    /*
     * Stop Recording
     */
    public void stopRecording() {
        if (mRecorder == null) {
            return;
        }


    }

    /*
     * Start Playing
     */
    public void startPlaying() {
        stop();

    }

    /*
     * Stop Playing
     */
    public void stopPlaying() {
        if (mPlayer == null ) {
            return;
        }


    }

    /*
     * Stop existing tasks
     */
    public void stop() {
        stopPlaying();
        stopRecording();
    }

    /*
     * Clear every status 
     */
    public void clear() {
        
    }

    /*
     * Handle the errors
     */
    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {


        return true;
    }

    /*
     * Handle MediaPlayer play completion event
     */
    @Override
    public void onCompletion(MediaPlayer mp) {
        
    }

}