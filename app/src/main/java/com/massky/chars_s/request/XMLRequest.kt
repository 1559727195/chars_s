package com.massky.chars_s.request

import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.*

class XMLRequest(method: Int, url: String?, private val mListener: Response.Listener<XmlPullParser?>,
                 errorListener: Response.ErrorListener?) : Request<XmlPullParser>(method, url, errorListener) {

    // constructor(url: String?, listener: Response.Listener<XmlPullParser?>, errorListener: Response.ErrorListener?) : this(Method.GET, url, listener, errorListener) {}

    constructor(url: String?, listener: Response.Listener<XmlPullParser?>, errorListener: Response.ErrorListener?) : this(Method.GET,
            url, listener, errorListener)

    override fun parseNetworkResponse(response: NetworkResponse): Response<XmlPullParser> {
        return try {
            val xmlString = String(response.data,
                    HttpHeaderParser.parseCharset(response.headers) as Charset)
            val factory = XmlPullParserFactory.newInstance()
            val xmlPullParser = factory.newPullParser()
             xmlPullParser.setInput(StringReader(xmlString))
           //Response.success(xmlPullParser, HttpHeaderParser.parseCacheHeaders(response))
            Response.success(xmlPullParser,HttpHeaderParser.parseCacheHeaders(response))
        } catch (e: UnsupportedEncodingException) {
            Response.error(ParseError(e))
        } catch (e: XmlPullParserException) {
            Response.error(ParseError(e))
        }
    }

    override fun deliverResponse(response: XmlPullParser) {
        mListener.onResponse(response)
    }

}