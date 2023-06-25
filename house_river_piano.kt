package com.example.community_engagement_project

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class ArtInstallation : SurfaceView, SurfaceHolder.Callback {
    
    lateinit var artThread: ArtThread
    
    constructor(context: Context) : super(context) {
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)
    }
    
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)
    }
    
    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }
    
    override fun surfaceCreated(holder: SurfaceHolder) {
        artThread = ArtThread(holder)
        artThread.isRunning = true
        artThread.start()
    }
    
    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        artThread.isRunning = false
        while(retry){
            try{
                artThread.join()
                retry = false
            } catch(e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
    
    class ArtThread(val surfaceHolder: SurfaceHolder) : Thread(){
        
        private val lock = Any()
        private var isRunning: Boolean = false
        private var canvas: Canvas? = null
        
        override fun run() {
            while (isRunning){
                canvas = null
                synchronized(lock){
                    
                    try {
                        canvas = surfaceHolder.lockCanvas(null)
                        canvas?.let {
                            // draw
                        }
                    } finally {
                        canvas?.let {
                            surfaceHolder.unlockCanvasAndPost(it)
                        }
                    }
                }
            }
        }
    }
    
    fun startEngagement(){
        // create engagement - using events, initiatives, etc.
    }
    
    fun gatherParticipants(){
        // call for participation - using social media, flyers, etc.
    }
    
    fun createMeaningfulArt(){
        // create art with participants
    }
    
    fun exhibitArt(){
        // exhibit art in public
    }
}