package com.hhly.partner.presentation.utils;

import android.text.Html;
import android.text.Spanned;

/**
 * description :
 * Created by Flynn
 * 2017/4/17
 */

public class HtmlUtil {

    public static Spanned getHtmlString(String content, String color, String fontSize, String content2, String color2, String fontSize2) {
        return Html.fromHtml("<font size=\"30\" height = \"30dp\" color=\"#ff0000\"><strong><u><b>"+ content +"</b></u></strong></font>分  已成交<font color=\"#00ff00\"><b>%2$d</b></font>单");
    }
}
