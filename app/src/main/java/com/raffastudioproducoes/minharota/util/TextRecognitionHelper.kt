package com.raffastudioproducoes.minharota.util

import android.content.Context
import android.net.Uri
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

object TextRecognitionHelper {
    fun extractValueFromImage(context: Context, imageUri: Uri, onValueFound: (Double) -> Unit, onError: (Exception) -> Unit) {
        try {
            val image = InputImage.fromFilePath(context, imageUri)
            val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

            recognizer.process(image)
                .addOnSuccessListener { visionText ->
                    val fullText = visionText.text
                    val value = parseValueFromText(fullText)
                    onValueFound(value)
                }
                .addOnFailureListener { e ->
                    onError(e)
                }
        } catch (e: Exception) {
            onError(e)
        }
    }

    private fun parseValueFromText(text: String): Double {
        // Regex para encontrar valores monetários como R$ 15,50 ou 15.50
        val regex = Regex("""(?:R\$\s?)?(\d+[,.]\d{2})""")
        val matches = regex.findAll(text)
        
        // Retorna o maior valor encontrado (geralmente o total do ganho no print)
        return matches.map { match ->
            match.groupValues[1].replace(",", ".").toDoubleOrNull() ?: 0.0
        }.maxOrNull() ?: 0.0
    }
}
