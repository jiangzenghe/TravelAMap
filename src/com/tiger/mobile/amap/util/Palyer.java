package com.tiger.mobile.amap.util;

import android.media.MediaPlayer;
import android.util.Log;
import java.io.IOException;

public class Palyer
    implements MediaPlayer.OnCompletionListener
{
    public static interface OnMediaPlayerStatuListener
    {

        public abstract void onCompletion();

        public abstract void pauseStatu();

        public abstract void playStatu();

        public abstract void stopStatu();
    }


    public MediaPlayer mediaPlayer;
    private OnMediaPlayerStatuListener mediaPlayerStatuListener;
    private String path;
    private int status;

    public Palyer()
    {
        status = -1;
        mediaPlayer = new MediaPlayer();
        try
        {
            mediaPlayer.setAudioStreamType(3);
            mediaPlayer.setOnCompletionListener(this);
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void backwrardFun()
    {
        mediaPlayer.seekTo(-15000 + mediaPlayer.getCurrentPosition());
    }

    public String getPath()
    {
        return path;
    }

    public int getStatus()
    {
        return status;
    }

    public void onCompletion(MediaPlayer mediaplayer)
    {
        if (mediaPlayer != null)
        {
            Log.e("error", "onCompletion");
            setStatus(3);
            mediaPlayerStatuListener.onCompletion();
        }
    }

    public void ondestroy()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void pause()
    {
        if (mediaPlayer != null)
        {
            if (mediaPlayer.isPlaying())
            {
                mediaPlayer.pause();
                mediaPlayerStatuListener.pauseStatu();
            }
            setStatus(1);
        }
    }

    public void play()
    {
        try
        {
            mediaPlayer.start();
            mediaPlayerStatuListener.playStatu();
            setStatus(0);
            return;
        }
        catch (Exception exception)
        {
            mediaPlayerStatuListener.pauseStatu();
            setStatus(1);
            exception.printStackTrace();
            return;
        }
    }

    public void playUrl(String s)
    {
        try
        {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(s);
            setPath(s);
            mediaPlayer.prepare();
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            illegalargumentexception.printStackTrace();
            return;
        }
        catch (SecurityException securityexception)
        {
            securityexception.printStackTrace();
            return;
        }
        catch (IllegalStateException illegalstateexception)
        {
            illegalstateexception.printStackTrace();
            return;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public void setOnMediaPlayerStatuListener(OnMediaPlayerStatuListener onmediaplayerstatulistener)
    {
        mediaPlayerStatuListener = onmediaplayerstatulistener;
    }

    public void setPath(String s)
    {
        path = s;
    }

    public void setStatus(int i)
    {
        status = i;
    }

    public void stop()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.stop();
            mediaPlayerStatuListener.stopStatu();
            setStatus(2);
        }
    }
}
