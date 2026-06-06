package com.raffastudioproducoes.minharota

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.system.exitProcess

class CrashHandler(private val context: Context) : Thread.UncaughtExceptionHandler {
    private val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()

    init {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        saveCrashLog(throwable)
        
        // Permite que o sistema trate o crash após salvar o log ou encerra
        if (defaultHandler != null) {
            defaultHandler.uncaughtException(thread, throwable)
        } else {
            exitProcess(1)
        }
    }

    private fun saveCrashLog(throwable: Throwable) {
        try {
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val fileName = "crash_log_$timestamp.txt"
            val logFile = File(context.filesDir, fileName)
            
            val logContent = buildString {
                append("Timestamp: $timestamp\n")
                append("Device: ${android.os.Build.MODEL} (Android ${android.os.Build.VERSION.RELEASE})\n")
                append("Message: ${throwable.message}\n")
                append("Stacktrace:\n")
                append(Log.getStackTraceString(throwable))
            }

            FileOutputStream(logFile).use { it.write(logContent.toByteArray()) }
            Log.e("MinhaRotaCrash", "Crash log saved to: ${logFile.absolutePath}")
        } catch (e: Exception) {
            Log.e("MinhaRotaCrash", "Failed to save crash log", e)
        }
    }
}
