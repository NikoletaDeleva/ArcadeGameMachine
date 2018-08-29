package com.egtinteractive.tic_tac_toe.utils;

import java.text.NumberFormat;
import java.text.ParsePosition;

public class Utils {

    public boolean isNumeric(final String str) {
	if (str.equals("")) {
	    return false;
	}
	final NumberFormat formatter = NumberFormat.getInstance();
	final ParsePosition pos = new ParsePosition(0);
	formatter.parse(str, pos);
	return str.length() == pos.getIndex();
    }
}
