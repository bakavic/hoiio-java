package com.hoiio.sdk.objects.voice;

/*
Copyright (C) 2012 Hoiio Pte Ltd (http://www.hoiio.com)

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
*/

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.objects.HoiioResponse;

public class CallHistory extends HoiioResponse {
	
	private static enum Params {
		ENTRIES_COUNT, TOTAL_ENTRIES_COUNT, ENTRIES;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
		
	private int pageCount;
	private int totalCount;
	private List<Call> callList;
	
	/**
	 * Constructs a new {@code CallHistory} object by decoding the {@code JSONObject} as a response from the HTTP Request 
	 * @param output The response of the HTTP Request
	 */
	public CallHistory(JSONObject output) throws HoiioException {
		response = output.toString();
		
		pageCount = output.getInt(Params.ENTRIES_COUNT.toString());
		totalCount = output.getInt(Params.TOTAL_ENTRIES_COUNT.toString());

		callList = new ArrayList<Call>();
		
		JSONArray entries = (JSONArray) output.get(Params.ENTRIES.toString());
		
		for (int i = 0; i < entries.size(); i++) {
			callList.add(new Call(entries.getJSONObject(i)));
		}
	}
	
	/**
	 * Gets the list of {@code Call} returned in this response
	 * @return The list of {@code Call} returned in this response
	 */
	public List<Call> getCallList() {
		return callList;
	}
	
	/**
	 * Gets the number of entries returned in this response
	 * @return The number of call history entries returned in this response.
	 */
	public int getPageCount() {
		return pageCount;
	}
	
	/**
	 * Gets the total number of entries in the period specified in the request
	 * @return The total number of call history entries in the period specified in the request.
	 */
	public int getTotalCount() {
		return totalCount;
	}
}
