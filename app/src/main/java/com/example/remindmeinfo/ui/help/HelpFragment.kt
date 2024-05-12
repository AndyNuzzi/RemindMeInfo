package com.example.remindmeinfo.ui.help


import android.os.Bundle
import android.view.*
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.remindmeinfo.R

class HelpFragment : Fragment() {

    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_help, container, false)
        webView = view.findViewById(R.id.webview)
        webView.settings.javaScriptEnabled = true

        loadYoutubeVideo()

        return view
    }

    private fun loadYoutubeVideo() {
        val youtubeVideoIdAdmin = "zMj1cBUOv_k?si=8EOkjnDE9v73vKfE"

        val html = """
            <!DOCTYPE html>
            <html>
            <body>
            <!-- Incluir el IFrame Player API de YouTube -->
            <iframe width="100%" height="100%" src="https://www.youtube.com/embed/$youtubeVideoIdAdmin" 
            frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" 
            allowfullscreen></iframe>
            
            </body>
            </html>
            """.trimIndent()

        webView.loadData(html, "text/html", "UTF-8")
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }

    override fun onDestroy() {
        webView.destroy()
        super.onDestroy()
    }

}
