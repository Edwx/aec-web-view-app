package pro.edwx.aec.webview

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.webkit.*


class CustomWebViewClient: WebViewClient() {

    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
        return super.shouldInterceptRequest(view, request)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val url = request?.url.toString()

        when {
            url.startsWith("http://") || url.startsWith("https://") -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

                if (url.endsWith(".pdf")) {
                    intent.setDataAndType(Uri.parse(url), "application/pdf")
                }

                try {
                    view!!.context.startActivity(intent)
                } catch (e: ActivityNotFoundException) { }
            }

            else -> {
                view?.loadUrl(url)
            }
        }

        return true
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
    }
}